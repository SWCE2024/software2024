Feature: a user sign up
  I want to sign up on YOUR EVENT
  Scenario: a successful signup
    When I click on sign up and flag is 'true'
    And he fills in 'ID' with '704123456'
    And he fills in 'Phone Number' with '0594408608'
    And he fills in 'BackUp Number' with '0599585810'
    And he fills in 'Adress' with 'nablus-rafidia'
    And he fills in 'Gmail' with 'noor17@gmail.com'
    And he fills in 'user name' with 'noor17'
    And he fills in 'Password' with '1234**Aa'
    And he presses 'sign up' and flag is 'true'
    Then there should be a user 'noor17'

  Scenario Outline: errors with input
    When I click on sign up and flag is 'true'
    And he fills in 'ID' with '<ID>'
    And he fills in 'Phone Number' with '<Phone Number>'
    And he fills in 'BackUp Number' with '<BackUp Number>'
    And he fills in 'Adress' with '<Adress>'
    And he fills in 'Gmail' with '<Gmail>'
    And he fills in 'user name' with '<user name>'
    And he fills in 'Password' with '<Password>'
    And he presses 'sign up' and flag is 'true'
    Then the user should see '<massage>'
    Examples:
      | ID       |Phone Number | BackUp Number |Adress        | Gmail         | user name | Password | massage |
      |70412345  |0594408608   |0599585810     |nablus-rafidia|noor17@gmail.com|noor17    |1234**Aa  |ID must be 9 number not less |
      |7041234567|0594408608   |0599585810     |nablus-rafidia|noor17@gmail.com|noor17    |1234**Aa  |ID must be 9 number not more|
      |70412345a |0594408608   |0599585810     |nablus-rafidia|noor17@gmail.com|noor17    |1234**Aa  |ID must not contain character |
      |704123456 |0594408608   |0599585810     |nablus-rafidia|noor17@gmail.com|noor17    |1234**Aa  |ID must be 9 number |
      |704123456 |059440860    |0599585810     |nablus-rafidia|noor17@gmail.com|noor17    |1234**Aa  |Phone Number must be 10 number not less |
      |704123456 |05944086088  |0599585810     |nablus-rafidia|noor17@gmail.com|noor17    |1234**Aa  |Phone Number must be 10 number not more |
      |704123456 |059440860a   |0599585810     |nablus-rafidia|noor17@gmail.com|noor17    |1234**Aa  |Phone Number must not contain character |
      |704123456 |0594408608   |059958581      |nablus-rafidia|noor17@gmail.com|noor17    |1234**Aa  |Phone Number must be 10 number not less |
      |704123456 |0594408608   |05995858100    |nablus-rafidia|noor17@gmail.com|noor17    |1234**Aa  |Phone Number must be 10 number not more |
      |704123456 |0594408608   |059958581a     |nablus-rafidia|noor17@gmail.com|noor17    |1234**Aa  |Phone Number must not contain character |
      |704123456 |0594408608   |0599585810     |nablus-rafidia|noor17.com      |noor17    |1234**Aa  |email must be at least 5 characters long |
      |704123456 |0594408608   |0599585810     |nablus-rafidia|noor17@gmail.com|noor17    |1234**Aa  |email must be at least 5 characters long |
      |704123456 |0594408608   |0599585810     |nablus-rafidia|noor17@gmail.com|noor17    |1234      |Password must be at least 5 characters long |
      |704123456 |0594408608   |0599585810     |nablus-rafidia|noor17@gmail.com|noor17    |12345678  |Password must contain character |
      |704123456 |0594408608   |0599585810     |nablus-rafidia|noor17@gmail.com|noor17    |1234567a  |Password must contain small and capital character |
      |704123456 |0594408608   |0599585810     |nablus-rafidia|noor17@gmail.com|noor17    |1234567A  |Password must contain small and capital character |
      |704123456 |0594408608   |0599585810     |nablus-rafidia|noor17@gmail.com|noor17    |1234567*  |Password must contain small and capital character |
      |704123456 |0594408608   |0599585810     |nablus-rafidia|noor17@gmail.com|noor17    |1234567aA |Password must contain spetial character |