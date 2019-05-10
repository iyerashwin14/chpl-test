@Regression
Feature: OCD-2873 Simplified Editing of Developer
  This test checks whether edit, split and merge developer functionality work as expected

Scenario Outline: User logs in as ROLE and sees developer edit link exists on new developer page
    Given I'm logged in as "<ROLE>"
    When I navigate to the developer page for developer "1854"
    Then I see developer edit link for developer "1854"
    Examples:
      |ROLE       |
      |ROLE_ACB   |
      |ROLE_ADMIN |
      |ROLE_ONC   |

Scenario Outline: User logs in as ROLE and sees developer split link exists on new developer page
    Given I'm logged in as "<ROLE>"
    When I navigate to the developer page for developer "1854"
    Then I see developer split link for developer "1854"
    Examples:
      |ROLE       |
      |ROLE_ACB   |
      |ROLE_ADMIN |
      |ROLE_ONC   | 

Scenario Outline: User logs in as ROLE and sees developer merge link exists on new developer page
    Given I'm logged in as "<ROLE>"
    When I navigate to the developer page for developer "1854"
    Then I see developer merge link for developer "1854"
    Examples:
      |ROLE       |
      |ROLE_ADMIN |
      |ROLE_ONC   |

Scenario Outline: User logs in as ROLE and edits a developer
    Given I'm logged in as "<ROLE>"
    When I navigate to the developer page for developer "<developer id>"
    And I edit for developer "<developer id>"
    Then I see the edited developer information is recorded and updated on View Developer page
    Examples:
      |ROLE       |developer id|
      |ROLE_ACB   |1903        |
      |ROLE_ADMIN |1854        |
      |ROLE_ONC   |1903        |

Scenario: Verify error message is displayed when the developer name field is left blank
    Given I'm logged in as "ROLE_ONC"
    When I navigate to the developer page for developer "1854"
    And I click the edit link for developer "1854"
    And I set the developer name field to ""
    Then I see "Field is required" error message for developer name field

Scenario: Verify error message is displayed when the email field is left blank
    Given I'm logged in as "ROLE_ONC"
    When I navigate to the developer page for developer "1854"
    And I click the edit link for developer "1854"
    And I set the email field to ""
    Then I see "Field is required" error message for email field

Scenario: Verify message is displayed in developers to merge section
    Given I'm logged in as "ROLE_ONC"
    When I navigate to the developer page for developer "1854"
    And I click the merge developer link for developer "1854"
    Then I see "At least one other Developer must be selected to merge" in developers to merge section

Scenario: Verify message is displayed in products moving to new developer section
    Given I'm logged in as "ROLE_ADMIN"
    When I navigate to the developer page for developer "1854"
    And I click the split developer link for developer "1854"
    Then I see "At least one Product must be selected to move" in products moving to new developer section

Scenario: Developer edit option is not avaialble for a user who is not logged in
    When I navigate to the developer page for developer "1854"
    Then I do not see developer edit link for developer "1854"

Scenario: Developer split option is not avaialble for a user who is not logged in
    When I navigate to the developer page for developer "1854"
    Then I do not see developer split link for developer "1854"

Scenario: Developer merge option is not avaialble for a user who is not logged in
    When I navigate to the developer page for developer "1854"
    Then I do not see developer merge link for developer "1854"


