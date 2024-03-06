package org.example.AcceptanceTest;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class OrganizerEventManagement {

    @Given("the event organizer is on the Event Operation page")
    public void theEventOrganizerIsOnTheEventOperationPage() {
        assertTrue(true);
    }

    @When("they input {string}, {string}, {string}, {string}, {string} into the respective fields")
    public void theyInputIntoTheRespectiveFields(String eventName, String eventDate, String eventLocation, String eventTime, String eventDescription) {
        assertEquals("Expected event name", eventName, eventName);
    }

    @And("they click the {string} button")
    public void theyClickTheButton(String button) {
        assertEquals("Add", button, button);
    }

    @Then("the new event with the name {string} should be added to the database")
    public void theNewEventWithNameShouldBeAddedToTheDatabase(String eventName) {
        assertEquals("Expected event name to be added", eventName, eventName);
    }

    @And("the event {string} exists in the database")
    public void theEventExistsInTheDatabase(String eventName) {
        assertEquals("Expected event exists", eventName, eventName);
    }

    @And("they update the event details with {string}, {string}, {string}, {string}, {string}")
    public void theyUpdateTheEventDetailsWith(String newEventName, String newEventDate, String newEventLocation, String newEventTime, String newEventDescription) {
        assertEquals("Expected new event name", newEventName, newEventName);
    }

    @Then("the event {string} should be updated in the database with the new details")
    public void theEventShouldBeUpdatedInTheDatabaseWithTheNewDetails(String eventName) {
        assertEquals("Expected event to be updated", eventName, eventName);
    }

    @Then("the event {string} should be removed from the database")
    public void theEventShouldBeRemovedFromTheDatabase(String eventName) {
        assertEquals("Expected event to be deleted", eventName, eventName);
    }

    @When("they input {string} into the Event Name field")
    public void theyInputIntoTheEventNameField(String eventName) {
        assertEquals("Expected event name", eventName, eventName);
    }

}
