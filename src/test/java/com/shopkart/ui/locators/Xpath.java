package com.shopkart.ui.locators;

public class Xpath {
        private Xpath() {
        }


        public static final String SEARCH = "//*[@id='catalog-search']";
        public static final String ADDTOCART = "//button[text()='Add to cart']";
        public static final String Cart = "//button[normalize-space()='Cart']";
        public static final String CART_TOTAL = "//*[@data-role='cart-total']";
        public static final String CHECKOUT = "//button[text()='Checkout']";
        public static final String PLACE_ORDER = "//button[text()='Place order']";
        public static final String ADDRESS = "//textarea[@id='address']";
    public static final String EMAIL =
            "//input[@id='email']";

    public static final String PASSWORD =
            "//input[@id='password']";

    public static final String LOGIN_BUTTON =
            "//button[@type='submit' and normalize-space()='Sign in']";

    public static final String ERROR_MESSAGE =
            "//div[@role='alert']";
}
