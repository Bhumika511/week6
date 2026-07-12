@smoke
Feature: Order

  Scenario: Customer places an order

    Given User opens ShopKart
    And User logs in
    When User searches for "Bag"
    And User adds the product to cart
    And User opens cart
    And User proceeds to checkout
    Then Order should be placed successfully