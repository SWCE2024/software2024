Feature: report
  this is the report feature

  Scenario: successfully
    When I click on report and flag is 'true'
    Then show message 'should be a report created'