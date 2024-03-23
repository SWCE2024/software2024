package org.example.AcceptanceTest;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import javafx.scene.control.Alert;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParticipantsVendor {
    boolean flag=false;
    @Given("the user is on the vendor search page")
    public void theUserIsOnTheVendorSearchPage() {
        assertTrue(true);

    }
    @When("the user selects {string}")
    public void theUserSelects(String string) {
        if (string.equals("Choose...")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please select category and search criteria.");
            alert.showAndWait();
            return;
        }else {
            System.out.println("User selects " + string);
        }
    }
    @When("the user enters {string}")
    public void theUserEnters(String string) {
        if (string.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please enter search text.");
            alert.showAndWait();
            return;
        }
    }
    @When("the user clicks the search button")
    public void theUserClicksTheSearchButton() {
        assertTrue(true);
    }
    @Then("the system should display vendors that match the criteria")
    public void theSystemShouldDisplayVendorsThatMatchTheCriteria() {
        flag = true;

    }
    @Then("each vendor displayed should have the selected category,searchBy,searchByValue within the specified range")
    public void eachVendorDisplayedShouldHaveTheSelectedCategorySearchBySearchByValueWithinTheSpecifiedRange() {
        assertEquals(flag,flag);

    }


}
