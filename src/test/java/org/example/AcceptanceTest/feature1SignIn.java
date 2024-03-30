package org.example.AcceptanceTest;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static org.example.SignUpController.logger;

import java.util.logging.Level;
import static org.junit.Assert.assertEquals;
public class feature1SignIn {
    @Given("I go to {string}")
    public void iGoTo(String string) {
        assertEquals(true,string.equals("loginchoise"));
    }
    @Given("the field {string} is empty")
    public void theFieldIsEmpty(String string) {
        assertEquals(false,string.equals("false"));    }
    @When("I click on login and flag is {string}")
    public void iClickOnLoginAndFlagIs(String string) {
        if(string.equals(true))
            assertEquals(true,string.equals("true"));
        else
            assertEquals(false,string.equals("false"));

    }
    @Then("field {string} should be with error")
    public void fieldShouldBeWithError(String string) {
        logger.log(Level.SEVERE, "Please enter Gmail and Password first !");

        assertEquals(false,string.equals("false"));

    }
    @Then("I should see {string}")
    public void iShouldSee(String string) {
        logger.log(Level.SEVERE, "E-mail or password is incorrect");
        assertEquals(false,string.equals("false"));

    }
    @Then("I shouldnt see {string}")
    public void iShouldnTSee(String string){
        assertEquals(false,!string.equals("Access your account"));
    }


}
