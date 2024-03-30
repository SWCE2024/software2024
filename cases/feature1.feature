Feature: Login
  I want to login on YOUR EVENT
  Background:
    Given I go to 'loginchoise'
    And the field 'email' is empty
    And the field 'password' is empty

  Scenario:empty fields
    When I click on login and flag is 'true'
    Then field 'email' should be with error
    And field 'password' should be with error

  Scenario: Wrong password
    When he fills in 'gmail' with 'noor@gmail.com'
    And he fills in 'password' with '123456'
    And I click on login and flag is 'true'
    Then I should see 'E-mail or password is incorrect'

  Scenario: Login successfully
    When he fills in 'gmail' with 'noor@gmail.com'
    And he fills in 'password' with '123456'
    And I click on login and flag is 'true'
    Then I shouldnt see 'Access your account'