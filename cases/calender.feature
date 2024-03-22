Feature: Calendar

  Scenario: a successful organizer search
    When the organizer click the search calendar and flag is 'true'
    And organizer fill in 'search date' with '2023-12-30'
    And organizer presses 'search' and flag is 'true'
    Then show massage for organizer 'searched successfully'

  Scenario: Organizer views upcoming events
    When the organizer accesses the calendar view  and flag is 'true'
    Then the organizer should see upcoming events





