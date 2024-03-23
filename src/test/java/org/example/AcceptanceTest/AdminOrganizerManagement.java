package org.example.AcceptanceTest;
import io.cucumber.java.en.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.example.TESTINPUT;
public class AdminOrganizerManagement {

    @When("admin click on insert organizer and flag is {string}")
    public void admin_click_on_insert_organizer_and_flag_is(String string) {
        // Write code here that turns the phrase above into concrete actions
        assertEquals(true,string);
    }
    @When("admin fill in {string} with {string}")
    public void admin_fill_in_with(String string, String string2) {
        // Write code here that turns the phrase above into concrete actions

        if(string.equals("username")){
            assertEquals(true,true);
        }
        else if (string.equals("email")) {
            assertEquals(true,true);
        }
        else if (string.equals("phone")) {
            assertEquals(true,true);
        }
        else if (string.equals("OID")) {
            assertEquals(true,true);
        }

         else if (string.equals("address")) {
            assertEquals(true,true);
        }
        else if (string.equals("password")) {
            assertEquals(true,true);
        }

    }
    @When("admin presses {string} and flag is {string}")
    public void admin_presses_and_flag_is(String string, String string2) {
        // Write code here that turns the phrase above into concrete actions

        assertEquals(true,true);


    }


    @When("adimn click on insert organizer and flag is {string}")
    public void adimnClickOnInsertOrganizerAndFlagIsTrue(String string) {
        assertEquals(true,true);

    }
}
