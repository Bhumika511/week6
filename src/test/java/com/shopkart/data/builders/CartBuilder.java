package com.shopkart.data.builders;

public class CartBuilder {

    private String sku;
        private int quantity;

        public static CartBuilder create() {
            return new CartBuilder();
        }

        public CartBuilder withSku(String sku) {
            this.sku = sku;
            return this;
        }

        public CartBuilder withQuantity(int quantity) {
            this.quantity = quantity;
            return this;
        }

        public String getSku() {
            return sku;
        }

        public int getQuantity() {
            return quantity;
        }
    }
