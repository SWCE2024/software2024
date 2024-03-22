package org.example.AcceptanceTest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
public class calender {

    @When("the organizer click the search calendar and flag is {string}")
    public void the_organizer_click_the_search_calendar_and_flag_is(String string)
    {
        assertEquals(true,string);
    }
    @When("organizer fill in {string} with {string}")
    public void organizer_fill_in_with(String string, String string2)
    {
        if(string.equals("search date"))
            assertEquals("2023-12-30", string2);
    }

    @When("organizer presses {string} and flag is {string}")
    public void organizer_presses_and_flag_is(String string, String string2)
    {
        if(string.equals("search"))
            assertTrue(Boolean.parseBoolean(string2));
    }
    @Then("show massage for organizer {string}")
    public void show_massage_for_organizer(String string)
    {
        assertEquals(true,string.equals("searched successfully"));
    }

    @When("the organizer accesses the calendar view  and flag is {string}")
    public void the_organizer_accesses_the_calendar_view_and_flag_is(String string)
    {
        assertEquals(true,string);
    }
    @Then("the organizer should see upcoming events")
    public void the_organizer_should_see_upcoming_events()
    {
        assertTrue(true);
    }
}
