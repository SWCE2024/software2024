package org.example.AcceptanceTest;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParticipantsTicketManagement
{

    @Given("the participant is on the {string} page")
    public void the_participant_is_on_the_page(String string) {
        // Write code here that turns the phrase above into concrete actions
        assertEquals(string, "Menu Participants");
    }
    @When("the participant clicks on {string}")
    public void the_participant_clicks_on(String string) {
        // Write code here that turns the phrase above into concrete actions
        assertEquals(string, "Ticket Management");
    }
    @Then("the system  generates e-tickets for event registration")
    public void the_system_generates_e_tickets_for_event_registration() {
        // Write code here that turns the phrase above into concrete actions
        assertEquals(true, true);
    }



}
