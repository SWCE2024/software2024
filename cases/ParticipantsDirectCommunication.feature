Feature:  Participant Initiated Direct Communication via Email.

  Scenario: Participant initiates a direct communication request with the event organizers
    Given the participant is on the "Menu Participants" page
    When the participant clicks on "DirectCommunication"
    Then the system sends a communication request to the organizers' email
    And the participant receives a confirmation message saying "Your request has been sent to the organizers"

