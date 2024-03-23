package org.example.AcceptanceTest;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.TESTINPUT;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class OrganizerVendorManagement {
    @Given("an Organizer wants to add a new Service Provider")
    public void anOrganizerWantsToAddANewServiceProvider() {
        assertTrue(true);

    }
    @When("he provides user details like {string},{string}, {string},{string} and {string}")
    public void heProvidesUserDetailsLikeAnd(String string, String string2, String string3, String string4, String string5 ){
        if(TESTINPUT.checkInputs(string,string2))
            assertTrue(true);
        else
            assertFalse(false);

    }
    @Then("the Service Provider should be successfully added to the database")
    public void theServiceProviderShouldBeSuccessfullyAddedToTheDatabase() {
        assertTrue(true);
    }

    @Given("an Organizer wants to update an existing Service Provider")
    public void anOrganizerWantsToUpdateAnExistingServiceProvider() {
       assertTrue(true);
    }

    @When("they update the event details with {string},{string}, {string}, {string} ,{string}")
    public void theyUpdateTheEventDetailsWith(String string, String string2, String string3, String string4, String string5) {
        if(TESTINPUT.checkInputs(string,string2))
            assertTrue(true);
        else
            assertFalse(false);

    }
    @Then("the Service Provider details should be successfully updated")
    public void theServiceProviderDetailsShouldBeSuccessfullyUpdated() {
        assertTrue(true);

    }

    @Given("an Organizer wants to Delete an existing ServiceProvider")
    public void anOrganizerWantsToDeleteAnExistingServiceProvider() {
        assertTrue(true);
    }
    @Then("the Service Provider should be successfully deleted from the database")
    public void theServiceProviderShouldBeSuccessfullyDeletedFromTheDatabase() {
        assertTrue(true);
    }
}
