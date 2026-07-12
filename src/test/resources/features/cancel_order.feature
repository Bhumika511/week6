@negative
@api
Feature: Cancel order

  Scenario: A user cancels an order
    Given Alice has placed an order
    When Alice cancels the order
    Then the order status should become "CANCELLED"
    When Alice cancels the same order again
    Then the response status should be 409

