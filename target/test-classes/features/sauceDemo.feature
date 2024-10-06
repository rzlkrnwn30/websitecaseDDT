Feature: Sauce Demo Login


Scenario: Unsuccessful login with locked out user
    Given I am on the Sauce Demo login page
    When I enter a locked out user and password
    And I click the login button
    Then I should see an error message 'Epic sadface: Sorry, this user has been locked out.'

Scenario: Unsuccessful login with invalid credentials
    Given I am on the Sauce Demo login page
    When I enter a invalid username and password
    And I click the login button
    Then I should see an error message 'Epic sadface: Username and password do not match any user in this service'

Scenario: Successful login with valid credentials
    Given I am on the Sauce Demo login page
    When I enter a valid username and password
    And I click the login button
    Then I should be redirected to the inventory page

