Feature: Event Management

  Scenario Outline: Add a new event
    Given the event organizer is on the Event Operation page
    When they input "<event_name>", "<event_date>", "<event_location>", "<event_time>", "<event_description>" into the respective fields
    And they click the "Add" button
    Then the new event with the name "<event_name>" should be added to the database

    Examples:
      | event_name | event_date  | event_location | event_time | event_description     |
      | Birthday   | 2024-07-10  | Nablus         | 18:00      | Noor's 30th birthday  |
      | Wedding    | 2024-08-15  | Qalqilya       | 15:00      | Deema wedding         |

  Scenario Outline: Update an existing event
    Given the event organizer is on the Event Operation page
    And the event "<existing_event_name>" exists in the database
    When they input "<existing_event_name>" into the Event Name field
    And they click the "Get Information" button
    And they update the event details with "<new_event_name>", "<new_event_date>", "<new_event_location>", "<new_event_time>", "<new_event_description>"
    And they click the "Update" button
    Then the event "<existing_event_name>" should be updated in the database with the new details

    Examples:
      | existing_event_name | new_event_name | new_event_date | new_event_location | new_event_time | new_event_description |
      | Birthday            | Graduation      | 2024-07-12    | jenin              | 17:00          | Noor's graduation     |

  Scenario Outline: Delete an existing event
    Given the event organizer is on the Event Operation page
    And the event "<event_name>" exists in the database
    When they input "<event_name>" into the Event Name field
    And they click the "Delete" button
    Then the event "<event_name>" should be removed from the database

    Examples:
      | event_name |
      | Wedding    |
