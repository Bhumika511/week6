@api
@negative
@security
Feature: Order access

  Scenario: A user cannot access another user's order
    Given Alice has placed an order
    When Bob requests that order through the API
    Then the response status should be 403
