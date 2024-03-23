Feature: Participant Ticketing by email

  Scenario: E-ticket generation for event registration
    Given the participant is on the "Menu Participants" page
    When the participant clicks on "Ticket Management"
    Then the system  generates e-tickets for event registration


