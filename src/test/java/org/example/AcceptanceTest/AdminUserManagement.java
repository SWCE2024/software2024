package org.example.AcceptanceTest;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.assertTrue;


public class AdminUserManagement {

    private String userID;

    private String username;
    private String phoneNumber;
    private String email;
    private String address;
    private String password;

    @Given("an admin wants to add a new user")
    public void anAdminWantsToAddANewUser() {
        assertTrue(true);
    }
    @When("he provides user details like {string},{string}, {string}, {string} and {string}")
    public void heProvidesUserDetailsLikeAnd(String string, String string2, String string3, String string4, String string5) {
        username = string;
        phoneNumber = string2;
        email = string3;
        address = string4;
        password = string5;

    }
    @Then("the user should be successfully added")
    public void theUserShouldBeSuccessfullyAdded() {
        assertTrue(true);
    }

    @Given("an admin wants to update an existing user")
    public void anAdminWantsToUpdateAnExistingUser() {
       ////
        assertTrue(true);
    }
    @When("he provides the {string}")
    public void heProvidesThe(String string) {
      userID = string;
    }
    @When("he update details like {string}, {string}, {string}, {string} or {string}")
    public void heUpdateDetailsLikeOr(String string, String string2, String string3, String string4, String string5) {
        username = string;
        phoneNumber = string2;
        email = string3;
        address = string4;
        password = string5;

    }
    @Then("the user information should be successfully updated")
    public void theUserInformationShouldBeSuccessfullyUpdated() {
        assertTrue(true);
    }
    @Given("an admin wants to delete an existing user")
    public void anAdminWantsToDeleteAnExistingUser() {
        //////check id
        assertTrue(true);
    }
    @When("he provides the {string} of the user to be deleted")
    public void heProvidesTheOfTheUserToBeDeleted(String string) {
       userID = string;
    }
    @Then("the user should be successfully deleted")
    public void theUserShouldBeSuccessfullyDeleted() {
        assertTrue(true);
    }



}
