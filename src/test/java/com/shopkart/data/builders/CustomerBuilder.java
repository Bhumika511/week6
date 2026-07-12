package com.shopkart.data.builders;

public class CustomerBuilder {

        private String email;
        private String password;

        public static CustomerBuilder create() {
            return new CustomerBuilder();
        }

        public CustomerBuilder withEmail(String email) {
            this.email = email;
            return this;
        }

        public CustomerBuilder withPassword(String password) {
            this.password = password;
            return this;
        }

        public String getEmail() {
            return email;
        }

        public String getPassword() {
            return password;
        }
    }
