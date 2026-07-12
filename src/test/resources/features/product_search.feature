@productSearch
@smoke
@ui
@api
Feature: Product search

  Scenario: Searching products returns matching items
    Given "alice" opens the ShopKart login page
        When "alice" logs in
        Then she should be redirected to the home page
        When "alice" searches for "Metro Carryall"
        Then the API should return "Metro Carryall"
