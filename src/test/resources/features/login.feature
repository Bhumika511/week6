@smoke
Feature: Login

  Scenario: Alice logs into ShopKart successfully

    Given "alice" opens the ShopKart login page
    When "alice" logs in
    Then she should be redirected to the home page