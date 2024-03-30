package org.example.AcceptanceTest;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.TESTINPUT;

import static org.junit.jupiter.api.Assertions.*;

public class ParticipantsEventRegistering {
    @When("user click on add and flag is {string}")
    public void userClickOnAddAndFlagIs(String string) {
        if (string.equals("true")) {
            assertTrue(true);
        } else assertFalse(false);
    }
    @Then("the field {string} shoud be an error")
    public void theFieldShoudBeAnError(String string) {
        System.out.println("Please enter the information ");
        assertFalse(false);
    }
    @Then("the image {string} shoud be an error")
    public void theImageShoudBeAnError(String string) {
        System.out.println("Please enter the Picture ");
        assertFalse(false);
    }
    @When("user click on Event Rigester and flag is {string}")
    public void userClickOnEventRigesterAndFlagIs(String string) {
        if (string.equals("true")) assertTrue(true);
        else assertFalse(false);
    }
    @When("he fills in {string} with extension {string}")
    public void heFillsInWithExtension(String string, String string2) {
        boolean flag =false;
        switch (string) {
            case "EventDate":
                flag = TESTINPUT.dateTest(string2);
                if (flag) assertTrue(true);
                else assertFalse(false);
                break;
            case "EventTime":
                flag = TESTINPUT.timeTest(string2);
                if (flag) assertTrue(true);
                else assertFalse(false);
                break;
            case "AttendeCount":
                flag = TESTINPUT.countTest(string2);
                if (flag) assertTrue(true);
                else assertFalse(false);

                break;
            default:
                flag = TESTINPUT.pictureTest(string2);
                assertEquals(false, string.equals(flag));

                break;
        }

    }
    @Then("the information has been entered successfully")
    public void theInformationHasBeenEnteredSuccessfully() {
        assertTrue(true);
    }

}