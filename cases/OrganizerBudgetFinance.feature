Feature: Budget and Finance Management
  I want to be able to view and search for event financial details
  Scenario: empty information
    When user click on search and flag is 'true'
    Then the field 'event id' should be with error

  Scenario: a successful Information
    When user click on search and flag is 'true'
    And he fill in x 'event id' with '9'
    And he presses 'search' and flag is 'true'
    Then show massage x 'the information has been entered successfully'

  Scenario: errors with input
    When user click on search and flag is a 'true'
    And he fill in 'Name' with a '-1'
    And he presses 'search' and flag is a 'true'
    Then the user should see a 'event id must be positive integer'