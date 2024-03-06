Feature: Add an event
  Scenario: empty information
    When user click on add and flag is 'true'
    Then the field 'EventName' shoud be an error
    And the field 'EventDate' shoud be an error
    And the field 'EventTime' shoud be an error
    And the field 'EventLocation' shoud be an error
    And the field 'AttendeCount' shoud be an error
    And the image 'Picture' shoud be an error

  Scenario: a successful Information
    When user click on Event Rigester and flag is 'true'
    And he fills in 'EventName' with 'Party'
    And he fills in 'EventDate' with '2024-05-20'
    And he fills in 'EventTime' with '18:30 '
    And he fills in 'EventLocation' with 'Garden'
    And he fills in 'AttendeCount' with '50'
    And he fills in 'Picture' with extension 'png'
    And he presses 'add' and flag is 'true'
    Then the information has been entered successfully


  Scenario Outline: errors with input
    When user click on Event Rigester and flag is 'true'
    And he fills in 'EventName' with '<EventName>'
    And he fills in 'EventDate' with '<EventDate>'
    And he fills in 'EventTime' with '<EventTime>'
    And he fills in 'EventLocation' with '<EventLocation>'
    And he fills in 'AttendeCount' with '<AttendeCount>'
    And he fills in 'Picture' with extension '<Picture>'
    And he presses 'add' and flag is 'true'
    Then the user should see '<message>'

    Examples:
    |EventName     |EventDate   |EventTime|EventLocation  |AttendeCount|Picture        |message                             |
    | Sami         | 2024-03-02 | 14:00   | Conference    | 100        | image.png     | Invalid Name                       |
    | Concert123   | 2024-03-02 | 18:30   | Stadium       | -50        | concert.png   | Invalid Attendee Count             |
    | BirthdayParty| 2024-02-10 | 20:00   | Garden        | 50         | picture.png   | Event Date should be in the future |
    | Meeting      | 2024-05-20 | 16:45   | Office        | 30         | business.docx | Invalid Picture format             |
    | Conference   | 2024-04-15 | 25:30   | Hall          | 200        | 123.jpg       | Invalid Event Time                 |
    | Conference   | 2024-04-15 | 25:30   | Nablus        | 200        | 123.jpg       | Invalid Event Location             |
    | Meeting      | 2024-05-20 | 16:45   | Office        | 30         | business.exe  | Invalid Picture format             |
