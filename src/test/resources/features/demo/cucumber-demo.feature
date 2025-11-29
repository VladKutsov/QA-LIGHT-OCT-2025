Feature: other cucumber stuff

  @severity=minor
  Scenario Outline: Parametrized scenarios
    Given Print this number <number_to_print>
    Given Print this string <string_to_print>
    Examples:
      | number_to_print | string_to_print |
      | 1               | "string 1"      |
      | 2               | "string 2"      |
      | 3               | "string 3"      |
      | 4               | "string 4"      |

  @severity=trivial
  Scenario: Enum params 1
    Given Support agent executes REGISTER_NEW_USER_DATA
    Given Support agent executes DELETE_USER_DATA

  @severity=trivial
  Scenario: Enum params 2
    Given Support agent executes REGISTER_NEW_USER_DATA
    Given Support agent executes DELETE_USER_DATA

  @severity=trivial
  Scenario: Enum params3
    Given Support agent executes REGISTER_NEW_USER_DATA
    Given Support agent executes DELETE_USER_DATA

  @severity=blocker
  Scenario: Enum params4
    Given Support agent executes REGISTER_NEW_USER_DATA
    Given Support agent executes DELETE_USER_DATA