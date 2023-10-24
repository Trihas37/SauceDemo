Feature: Login
  @Positive
  Scenario: User login with valid username and password
    Given Saucedemo login page
    When Input valid username
    And Input valid password
    And Click login button
    Then User in dashboard page

  @Negative
  Scenario: User login with invalid username or password
    Given Saucedemo login page
    When Input valid username
    And Input invalid password
    And Click login button
    Then User get error message