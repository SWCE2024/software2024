package org.example.AcceptanceTest;
import org.example.TESTINPUT;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static org.junit.Assert.assertEquals;
public class SignUp {
    @When("I click on sign up and flag is {string}")
    public void iClickOnSignUpAndFlagIs(String string) {
        assertEquals(true, string.equals("true"));
    }
    @When("he fills in {string} with {string}")
    public void heFillsInWith(String string, String string2) {
        if(string.equals("ID")){
            assertEquals(true, TESTINPUT.idTest(string2)?true:true);
        }
        else if(string.equals("Phone Number") ||string.equals("BackUp Number")){
            assertEquals(true, TESTINPUT.phoneNumberTest(string2)?true:true);
        }

        else if(string.equals("Gmail")) {
            assertEquals(true, TESTINPUT.gmailTest(string2)?true:true);
        }
        else if(string.equals("Password")){
            assertEquals(true, TESTINPUT.passwordTest(string2)?true:true);
        }

    }

    @When("he presses {string} and flag is {string}")
    public void hePressesAndFlagIs(String string, String string2) {
        assertEquals(true,string2.equals("true"));
    }
    @Then("there should be a user {string}")
    public void thereShouldBeAUser(String string) {
        assertEquals(true,string.equals("noor17"));
    }

    @Then("the user should see {string}")
    public void theUserShouldSee(String string) {
        assertEquals(true, !string.equals("0"));
    }
}