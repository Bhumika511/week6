@negative
Feature: Cancel order

  Scenario: A user cancels an order
    Given Alice has placed an order
    When Bob requests that order through the API
    Then the response status should be 403

