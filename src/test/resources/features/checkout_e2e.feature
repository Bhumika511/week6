@e2e @smoke
Feature: Checkout places an order

Scenario: Customer checks out successfully

Given "alice" is logged in
And she adds 2 x "SKU-BAG" to her cart
When she checks out with a valid address
Then the order confirmation shows status "PLACED"
And GET order should return status "PLACED"
And database should contain one placed order