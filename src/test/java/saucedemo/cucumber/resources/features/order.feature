Feature: Order product
  Scenario: Check product details using product name link
    Given Dashboard page
    When User click a product name link
    Then User in product detail page

  Scenario: Add product to chart
    Given Dashboard page
    When User click add to chart button
    Then Product added to chart