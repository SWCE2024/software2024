Feature: Information for organizer

  Scenario: empty information
    When adimn click on insert organizer and flag is 'true'
    Then all field should be with 'error'

  Scenario: a successful Information
    When admin click on insert organizer and flag is 'true'
    And admin fill in 'OID' with '12'
    And admin fill in 'phone' with "0593947471"
    And admin fill in 'email' with 'shahd222@gamil.com'
    And admin fill in 'address' with 'Asira'
    And admin fill in 'username' with 'shahd'
    And admin fill in 'password' with 'i5ra'
    And admin presses 'save' and flag is 'true'
    Then show massage 'information has been entered successfully'
