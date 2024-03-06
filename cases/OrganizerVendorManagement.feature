Feature: Vendor Management

  Scenario: Creating a Database of Vendors

    Given the user is an admin
    And the user is logged in
    And the user navigates to the Vendor Management section

    When the admin adds a new vendor
    And specifies vendor details such as name, contact, type (catering, decorations, entertainment, etc.), and services offered

    Then the vendor is added to the database
    And the vendor is categorized by type and service

  Scenario: Searching and Filtering Vendors

    Given the user is an organizer or participant
    And the user is logged in
    And the user navigates to the Vendor Search page

    When the user specifies search criteria
    And applies filters for location, availability, pricing, and reviews

    Then the search results display vendors matching the criteria
    And vendors are sorted by relevance, considering the specified filters

  Scenario: Requesting Packages and Negotiating Contracts

    Given the user is an organizer
    And the user is logged in
    And the user has selected a vendor

    When the organizer navigates to the selected vendor's profile
    And chooses to request a package

    Then the organizer is prompted to specify event details and preferences
    And the request is sent to the vendor for review

    When the vendor responds to the package request
    And negotiation takes place

    Then the organizer and vendor can finalize the contract terms

  Scenario: Managing Vendor Bookings

    Given the user is an organizer
    And the user is logged in
    And the user has selected a vendor

    When the organizer navigates to the vendor's profile
    And chooses to manage bookings

    Then the organizer sees a list of current and past bookings with details
    And can modify or cancel existing bookings as needed

    When a new event is scheduled with the vendor

    Then the organizer can add a new booking for the event
    And the vendor is notified of the new booking

