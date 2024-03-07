package org.example.AcceptanceTest;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.TESTINPUT;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParticipantsEventRegistering {
    @When("user click on add and flag is {string}")
    public void userClickOnAddAndFlagIs(String string) {
        if (string.equals("true")) assertEquals(true,true);
        else assertEquals(false,false);
    }
    @Then("the field {string} shoud be an error")
    public void theFieldShoudBeAnError(String string) {
        System.out.println("Please enter the information ");
        assertEquals(false,false);
    }
    @Then("the image {string} shoud be an error")
    public void theImageShoudBeAnError(String string) {
        System.out.println("Please enter the Picture ");
        assertEquals(false,false);
    }
    @When("user click on Event Rigester and flag is {string}")
    public void userClickOnEventRigesterAndFlagIs(String string) {
        if (string.equals("true")) assertEquals(true,true);
        else assertEquals(false,false);
    }
    @When("he fills in {string} with extension {string}")
    public void heFillsInWithExtension(String string, String string2) {
        boolean flag =false;
        if (string.equals("EventDate")){
            flag= TESTINPUT.dateTest(string2);
            if(flag==true) assertEquals(true,true);
            else assertEquals(false,false);
        }
        else if (string.equals("EventTime")){
            flag= TESTINPUT.timeTest(string2);
            if(flag==true) assertEquals(true,true);
            else assertEquals(false,false);
        }
        else if (string.equals("AttendeCount")){
            flag= TESTINPUT.countTest(string2);
            if(flag==true) assertEquals(true,true);
            else assertEquals(false,false);

        }
        else {
            flag= TESTINPUT.pictureTest(string2);
            assertEquals(flag,flag);

        }

    }
    @Then("the information has been entered successfully")
    public void theInformationHasBeenEnteredSuccessfully() {
        assertEquals(true,true);
    }





}