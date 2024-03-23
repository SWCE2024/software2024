Feature: Organizer Service Provider Management

  Scenario Outline: Organizer Add Service Provider
    Given an Organizer wants to add a new Service Provider
    When he provides user details like "<Category>","<phone number>", "<price>", "<location>" and "<availability>"
    And they click the "Add" button
    Then the Service Provider should be successfully added to the database
    Examples:
      | Category | phone number | price | location | availability |
      |  DJ      | 0565347646  | 1200  | Nablus   |  ON           |
      |  Dessert      | 0565237646  | 1500  | Ramallah   |  OFF   |
      |  Catering      | 0599947246  | 800  | Nablus   |  ON      |

  Scenario Outline: Organizer Update an existing Service Provider
    Given an Organizer wants to update an existing Service Provider
    When he provides the "<ServiceProviderID>"
    And they click the "Get" button
    And they update the event details with "<NewCategory>","<Newphonenumber>", "<Newprice>", "<Newlocation>" ,"<Newavailability>"
    And they click the "Update" button
    Then the Service Provider details should be successfully updated
    Examples:
     | ServiceProviderID | NewCategory | Newphonenumber | Newprice | Newlocation | Newavailability |
     |       1           |  DJ         |   0565347646   | 1000     | Nablus      |  ON             |
     |       3           |  Dessert         |   0565347646   | 1500     | Ramallah      |  ON             |
     |       1           |  DJ         |   0565347646   | 1000     | Nablus      |  OFF             |

  Scenario Outline: Organizer Delete an existing Service Provider
    Given an Organizer wants to Delete an existing ServiceProvider
    When he provides the "<ServiceProviderID>"
    And they click the "Delete" button
    Then the Service Provider should be successfully deleted from the database
    Examples:
    |ServiceProviderID|
    | 1               |
    | 2               |
    | 4               |
