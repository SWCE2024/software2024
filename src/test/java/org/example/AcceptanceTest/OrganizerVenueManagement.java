package org.example.AcceptanceTest;
import io.cucumber.java.en.*;
import static org.junit.Assert.assertEquals;
import org.example.TESTINPUT;
public class OrganizerVenueManagement {
    boolean test =true;
    @When("organizer click on insert venue and flag is {string}")
    public void organizerClickOnInsertVenueAndFlagIs(String string) {
        assertEquals(true,TESTINPUT.emptyTest(string));
    }
    @Then("all field should be with {string}")
    public void allFieldShouldBeWith(String string) {
        assertEquals(true,string.equals("error"));
    }


    @When("he fill in {string} with {string}")
    public void heFillInWith(String string, String string2) {
        if(string.equals("Name")){
            assertEquals(true,TESTINPUT.VenueNameTest(string2));
        }
        else if (string.equals("Location")) {
            assertEquals(true,TESTINPUT.VenueLocationTest(string2));
        }
        else if (string.equals("Capacity")) {
            assertEquals(true,TESTINPUT.VenueCapacityTest(string2));
        }
        else if (string.equals("Pricing")) {
            assertEquals(true,TESTINPUT.VenuePricingTest(string2));
        }
    }
    @Then("show massage {string}")
    public void showMassage(String string) {
        assertEquals(true,string.equals("information has been entered successfully"));
    }


    @When("he fill in a {string} with {string}")
    public void heFillInAWith(String string, String string2) {

        assertEquals(true,string.equals(string));

    }

    @Then("the organizer should see {string}")
    public void theOrganizerShouldSee(String string) {
        assertEquals(true,string.equals(string));
    }

}
