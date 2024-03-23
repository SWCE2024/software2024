Feature: Ability to search and filter vendors

  Scenario: Search and filter vendors based on category, location, availability, and pricing
    Given the user is on the vendor search page
    When the user selects "category"
    And the user selects "searchBy"
    And the user enters "searchByValue"
    And the user clicks the search button
    Then the system should display vendors that match the criteria
    And each vendor displayed should have the selected category,searchBy,searchByValue within the specified range
