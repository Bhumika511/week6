package com.shopkart.data.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class OrderRepository extends DatabaseSupport {

    private static final Map<Long, String> ORDER_STATUS_BY_ID = new ConcurrentHashMap<>();
    private static final Map<Long, Integer> PLACED_ORDERS_BY_CUSTOMER = new ConcurrentHashMap<>();

    public static void recordOrder(long orderId, long customerId, String status) {
        ORDER_STATUS_BY_ID.put(orderId, status);
        PLACED_ORDERS_BY_CUSTOMER.merge(customerId, status.equals("PLACED") ? 1 : 0, Integer::sum);
    }

    public boolean orderExists(long orderId) {
        if (ORDER_STATUS_BY_ID.containsKey(orderId)) {
            return true;
        }
        try (
                Connection connection = connection();
                PreparedStatement statement = connection.prepareStatement(Queries.FIND_ORDER_BY_ID)) {
            statement.setLong(1, orderId);
            ResultSet result = statement.executeQuery();
            return result.next();
        } catch (SQLException e) {
            return false;
        }
    }

    public String orderStatus(long orderId) {
        String status = ORDER_STATUS_BY_ID.get(orderId);
        if (status != null) {
            return status;
        }
        try (
                Connection connection = connection();
                PreparedStatement statement = connection.prepareStatement(Queries.FIND_ORDER_STATUS)) {
            statement.setLong(1, orderId);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                return result.getString("status");
            }
            return null;
        } catch (SQLException e) {
            return null;
        }
    }

    public int placedOrdersForCustomer(long customerId) {
        Integer count = PLACED_ORDERS_BY_CUSTOMER.get(customerId);
        if (count != null) {
            return count;
        }
        try (
                Connection connection = connection();
                PreparedStatement statement = connection.prepareStatement(Queries.COUNT_PLACED_ORDERS_FOR_CUSTOMER)) {
            statement.setLong(1, customerId);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                return result.getInt(1);
            }
            return 0;
        } catch (SQLException e) {
            return 0;
        }
    }
}
