@Regression
Feature: Impersonate Roles
  This test checks whether impersonating different user roles works as expected

Scenario: Verify that impersonating ROLE_DEVELOPER is successful
    Given I'm logged in as "ROLE_ADMIN"
    When I'm on User Management page
    And I "Impersonate AQA Developers" role
    Then I see "Impersonating AQA Developers" as UserName

Scenario: Verify that impersonating ROLE_ACB is successful
    Given I'm logged in as "ROLE_ADMIN"
    When I'm on User Management page
    And I "Impersonate AQA ACB" role
    Then I see "Impersonating AQA ACB" as UserName

Scenario: Verify that impersonating ROLE_ATL is successful
    Given I'm logged in as "ROLE_ADMIN"
    When I'm on User Management page
    And I "Impersonate Chris Crescioli" role
    Then I see "Impersonating Chris Crescioli" as UserName

Scenario: Verify that impersonating ROLE_ONC is successful
    Given I'm logged in as "ROLE_ADMIN"
    When I'm on User Management page
    And I "Impersonate AQA ONC" role
    Then I see "Impersonating AQA ONC" as UserName

Scenario: Verify that impersonating ROLE_CMS is successful
    Given I'm logged in as "ROLE_ADMIN"
    When I'm on User Management page
    And I "Impersonate Jennifer CMS" role
    Then I see "Impersonating Jennifer CMS" as UserName