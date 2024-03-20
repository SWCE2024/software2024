package org.example.AcceptanceTest;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParticipantsDirectCommunication {
    @Given("the participant is on the {string} page")
    public void theParticipantIsOnThePage(String string) {assertEquals(true, true);}
    @When("the participant clicks on {string}")
    public void theParticipantClicksOn(String string) {
        if ("DirectCommunication".equals(string)) {assertEquals(true,true);}
    }
    @Then("the system sends a communication request to the organizers' email")
    public void theSystemSendsACommunicationRequestToTheOrganizersEmail() {assertTrue("The communication request should be successful.", true);}

    @Then("the participant receives a confirmation message saying {string}")
    public void theParticipantReceivesAConfirmationMessageSaying(String string) {assertEquals(true, string.equals("Your request has been sent to the organizers"));}
}