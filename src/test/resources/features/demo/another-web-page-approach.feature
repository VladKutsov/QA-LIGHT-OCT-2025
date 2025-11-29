Feature: showcase enum stuff

  # Re-write allo.ua tests using this approach
  @severity=critical
  Scenario: work with google
    Given I load google page
    Given I accept google cookies
    Given I click SEARCH_INPUT
    Given I set element SEARCH_INPUT value to "Java"
    When I send ENTER to SEARCH_INPUT
