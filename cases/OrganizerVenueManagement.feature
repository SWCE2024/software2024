Feature: Information for venue

  Scenario: empty information
    When organizer click on insert venue and flag is 'true'
    Then all field should be with 'error'

  Scenario: a successful Information
    When organizer click on insert venue and flag is 'true'
    And he fill in 'VenueName' with 'Wedding Hall'
    And he fill in 'Location' with "North Asira, downtown"
    And he fill in 'Capacity' with '200'
    And he fill in 'Pricing' with '5000'


    And he presses 'save' and flag is 'true'
    Then show massage 'information has been entered successfully'


  Scenario Outline: errors with input
    When user click on insert order and flag is 'true'
    And he fill in a 'VenueName' with '<VenueName>'
    And he fill in a 'Location' with '<Location>'
    And he fill in a 'Capacity' with '<Capacity>'
    And he fill in a 'color' with '<color>'
    And he fill in a 'Pricing' with extension '<Pricing>'
    And he presses 'save' and flag is 'true'
    Then the user should see '<message>'


    Examples:
      | VenueName         | Location | Capacity| Pricing   | message                                 |
      | Wedding Hall      |          | 300     | 5000      | Invalid Location, please check it       |
      |                   |Asira     | 300     | 5000      | Invalid Name, please check it           |
      | Wedding Hall      | 80       | 300     | 5000      | Invalid Location, please check it       |
      | Wedding Hall      | Asira    |   0     | 5000      | Capacity must be positive number        |
      | Wedding Hall      | Asira    | -300    | 5000      | Capacity must be positive number        |
      | Wedding Hall      | Asira    | 300     | -5000     | Pricing must be more than 0             |
      | Wedding Hall      | Asira    | 300     | 0         | Pricing must be more than 0             |





