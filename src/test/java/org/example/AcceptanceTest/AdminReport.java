package org.example.AcceptanceTest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.assertEquals;
public class AdminReport {
    @When("I click on report and flag is {string}")
    public void iClickOnReportAndFlagIs(String string) {
        assertEquals(true,string.equals("true"));
    }
    @Then("show message {string}")
    public void showMassegeF(String string) {
        assertEquals(true,string.equals("should be a report created"));
    }
}
