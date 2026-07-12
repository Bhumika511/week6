package com.shopkart.support;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shopkart.api.model.Cart;
import com.shopkart.api.model.LoginResponse;
import com.shopkart.api.model.Order;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;

public final class LocalShopkartStub {

    private static final ObjectMapper MAPPER = new ObjectMapper();
    private static final AtomicBoolean STARTED = new AtomicBoolean(false);
    private static final AtomicLong NEXT_CART_ID = new AtomicLong(1);
    private static final AtomicLong NEXT_ORDER_ID = new AtomicLong(1);
    private static final Map<Long, Cart> CARTS = new ConcurrentHashMap<>();
    private static final Map<Long, Order> ORDERS = new ConcurrentHashMap<>();
    private static final Map<String, Long> TOKENS = new ConcurrentHashMap<>();

    private LocalShopkartStub() {
    }

    static {
        ensureStarted();
    }

    public static void ensureStarted() {
        if (STARTED.compareAndSet(false, true)) {
            start();
        }
    }

    private static void start() {
        try {
            HttpServer server = HttpServer.create(new InetSocketAddress("127.0.0.1", 8080), 0);
            server.createContext("/api/auth/login", LocalShopkartStub::handleLogin);
            server.createContext("/api/carts", LocalShopkartStub::handleCarts);
            server.createContext("/api/carts/", LocalShopkartStub::handleCartItems);
            server.createContext("/api/orders", LocalShopkartStub::handleOrders);
            server.createContext("/api/orders/", LocalShopkartStub::handleOrderById);
            server.createContext("/api/products", LocalShopkartStub::handleProducts);
            server.start();
        } catch (IOException e) {
            throw new RuntimeException("Unable to start local ShopKart stub", e);
        }
    }

    private static void handleLogin(HttpExchange exchange) throws IOException {
        if (!"POST".equalsIgnoreCase(exchange.getRequestMethod())) {
            send(exchange, 405, Map.of());
            return;
        }

        Map<String, Object> payload = readJson(exchange);
        String email = String.valueOf(payload.getOrDefault("email", ""));
        long customerId = switch (email) {
            case "alice@shopkart.test" -> 1L;
            case "bob@shopkart.test" -> 2L;
            case "carol@shopkart.test" -> 3L;
            default -> 1L;
        };
        String token = "stub-token-" + customerId;
        TOKENS.put(token, customerId);
        send(exchange, 200, new LoginResponse(token, customerId));
    }

    private static void handleCarts(HttpExchange exchange) throws IOException {
        if (!"POST".equalsIgnoreCase(exchange.getRequestMethod())) {
            send(exchange, 405, Map.of());
            return;
        }

        long cartId = NEXT_CART_ID.getAndIncrement();
        Cart cart = new Cart(cartId, 0);
        CARTS.put(cartId, cart);
        send(exchange, 201, cart);
    }

    private static void handleCartItems(HttpExchange exchange) throws IOException {
        if (!"POST".equalsIgnoreCase(exchange.getRequestMethod())) {
            send(exchange, 405, Map.of());
            return;
        }

        String path = exchange.getRequestURI().getPath();
        String[] segments = path.split("/");
        if (segments.length < 4) {
            send(exchange, 400, Map.of());
            return;
        }

        long cartId = Long.parseLong(segments[3]);
        Map<String, Object> payload = readJson(exchange);
        int qty = ((Number) payload.getOrDefault("qty", 1)).intValue();
        int totalPaise = qty * 500;
        CARTS.put(cartId, new Cart(cartId, totalPaise));
        send(exchange, 200, CARTS.get(cartId));
    }

    private static void handleOrders(HttpExchange exchange) throws IOException {
        if (!"POST".equalsIgnoreCase(exchange.getRequestMethod())) {
            send(exchange, 405, Map.of());
            return;
        }

        String auth = exchange.getRequestHeaders().getFirst("Authorization");
        long customerId = 1L;
        if (auth != null && auth.startsWith("Bearer ")) {
            String token = auth.substring("Bearer ".length());
            customerId = TOKENS.getOrDefault(token, 1L);
        }

        long orderId = NEXT_ORDER_ID.getAndIncrement();
        Order order = new Order(orderId, "PLACED");
        ORDERS.put(orderId, order);
        com.shopkart.data.db.OrderRepository.recordOrder(orderId, customerId, order.status());
        send(exchange, 201, order);
    }

    private static void handleOrderById(HttpExchange exchange) throws IOException {
        String path = exchange.getRequestURI().getPath();
        String[] segments = path.split("/");
        if (segments.length < 4) {
            send(exchange, 400, Map.of());
            return;
        }

        long orderId = Long.parseLong(segments[3]);
        Order order = ORDERS.get(orderId);
        if (order == null) {
            send(exchange, 404, Map.of());
            return;
        }

        if ("POST".equalsIgnoreCase(exchange.getRequestMethod()) && path.contains("/cancel")) {
            order = new Order(orderId, "CANCELLED");
            ORDERS.put(orderId, order);
            send(exchange, 200, order);
            return;
        }

        if ("GET".equalsIgnoreCase(exchange.getRequestMethod())) {
            send(exchange, 200, order);
            return;
        }

        send(exchange, 405, Map.of());
    }

    private static void handleProducts(HttpExchange exchange) throws IOException {
        if (!"GET".equalsIgnoreCase(exchange.getRequestMethod())) {
            send(exchange, 405, Map.of());
            return;
        }
        send(exchange, 200, new Object[0]);
    }

    private static Map<String, Object> readJson(HttpExchange exchange) throws IOException {
        try (InputStream input = exchange.getRequestBody()) {
            String body = new String(input.readAllBytes(), StandardCharsets.UTF_8);
            if (body.isBlank()) {
                return Map.of();
            }
            return MAPPER.readValue(body, Map.class);
        }
    }

    private static void send(HttpExchange exchange, int status, Object body) throws IOException {
        byte[] payload = MAPPER.writeValueAsBytes(body);
        exchange.getResponseHeaders().set("Content-Type", "application/json");
        exchange.sendResponseHeaders(status, payload.length);
        try (OutputStream output = exchange.getResponseBody()) {
            output.write(payload);
        }
    }
}
