Feature: Login

  Scenario: Successful login

    Given user opens ShopKart application
    When user enters valid email and password
    And user clicks Login button
    Then Home page should be displayed