Feature: User Management

  Scenario: Add User
    Given an admin wants to add a new user
    When he provides user details like "username","phonenumber", "email", "address" and "password"
    And they click the "Add" button
    Then the user should be successfully added

  Scenario: Update User
    Given an admin wants to update an existing user
    When he provides the "user ID"
    And they click the "Get" button
    And he update details like "username", "phonenumber", "email", "address" or "password"
    And they click the "Update" button
    Then the user information should be successfully updated

  Scenario: Delete User
    Given an admin wants to delete an existing user
    When he provides the "user ID" of the user to be deleted
    And the user exists in the database
    And they click the "Delete" button
    Then the user should be successfully deleted
