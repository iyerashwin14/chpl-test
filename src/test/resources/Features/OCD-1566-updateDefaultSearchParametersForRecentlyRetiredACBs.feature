@Regression
Feature: OCD-1566 - Update default search parameters for recently retired ACBs
  This test checks whether CCHIT is unchecked on Search page and ACBs can be retired and unretired

  Scenario: Verify CCHIT checkbox is unchecked on Search page
    Given I am on CHPL search page
    And I look at ACB filter options selected for default search
    Then I see that "CCHIT" checkbox is unchecked

  Scenario: Verify ACBs can be retired and unretired
    Given I'm logged in as "ROLE_ADMIN"
    And I navigate to ONC ACB Management page
    And I access "Surescripts LLC" ACB details
    And I open the ACB edit form
    When I unretire an existing retired ACB
    Then ONC ACB status should show as Retired: "No"
    And I open the ACB edit form
    When I mark it as retired on "04/04/2013"
    Then ONC ACB status should show as Retired: "Yes"

  Scenario: Verify ACBs display an error message if any future date of retirement is selected
    Given I'm logged in as "ROLE_ADMIN"
    When I navigate to ONC ACB Management page
    And I access "ICSA Labs" ACB details
    And I open the ACB edit form
    And I mark it as retired on "08/01/2119"
    Then I see "Retirement date is required and must be before "now"."
