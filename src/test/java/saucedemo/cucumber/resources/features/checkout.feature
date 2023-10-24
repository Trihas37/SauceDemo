Feature: Checkout
  Scenario: Checkout product
    Given Chart page
    When User click checkout button
    And Input firstname
    And Input lastname
    And Input postal code
    And Click continue button
    Then Checkout overview page displayed