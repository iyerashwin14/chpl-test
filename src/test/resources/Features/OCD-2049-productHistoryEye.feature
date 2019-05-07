Feature: OCD-2049 - Verify 'Eye' on listing details page works and displays Product History 
         OCD-2822 - Add Developer and Product Name Change to the Eye 

@Regression
Scenario Outline: Verify 'Eye' opens on click and displays Product History
	Given I am on listing details page of listing with ID "<DB_ID>"
	When I click the 'Eye'
	Then the 'Eye' pop-up opens to show Product History "<Text>"
Examples:
	|DB_ID|Text|
	|437|Certification Status became "Active"|
	|3727|Certification Status became "Active"|
	|4024|Certification Status became "Active"|
	|5138|Certification Status became "Active"|
	|5243|Certification Status became "Active"|
	
@ignore
Scenario Outline: Verify 'Eye' opens on click and displays appropriate message when there is no history to display
Given I am on listing details page of listing with ID "<DB_ID>"
	When I click the 'Eye'
	Then the 'Eye' pop-up opens to show appropriate text "<Text>"
Examples:
	|DB_ID|Text|
	|33|No changes have been made to this Certified Product|
	|41|No changes have been made to this Certified Product|
	|49|No changes have been made to this Certified Product|
	|45|No changes have been made to this Certified Product|
	|158|No changes have been made to this Certified Product|

Scenario Outline: Product history eye shows developer edit activity
    Given I'm logged in as "<ROLE>"
    When I navigate to the developer page for developer "<developer id>"
    And I edit for developer "<developer id>"
    And I see the edited developer information is recorded and updated on View Developer page
    And I am on listing details page of listing with ID "<listing id>"
    And I click the 'Eye'
    Then I see the product history of developer edit is recorded as "Developer changed from"
    Examples:
      |ROLE       |developer id|listing id|
      |ROLE_ADMIN |1675        |4229      |
      |ROLE_ONC   |1298        |5737      |
      |ROLE_ACB   |1675        |4229      |

Scenario Outline: Product history eye shows version edit activity
    Given I'm logged in as "<ROLE>"
    When I navigate to the version edit page for version "<version id>" of product "<product id>" of developer "<developer id>"
    And I edit for version "<version id>"
    And I am on listing details page of listing with ID "<listing id>"
    And I click the 'Eye'
    Then I see the product history of version edit is recorded as "Version changed from"
    Examples:
      |ROLE       |version id|product id|developer id|listing id|
      |ROLE_ACB   |3623      |1057      |1770        |1799      |
      |ROLE_ADMIN |48        |145       |1675        |4229      |
      |ROLE_ONC   |3230      |2102      |1298        |5737      |

Scenario Outline: Product history eye shows product edit activity
    Given I'm logged in as "<ROLE>"
    When I navigate to the product edit page for product "<product id>" of developer "<developer id>"
    And I edit for product "<product id>"
    And I see the edited product information is recorded and updated on View Product page for product "<product id>"
    And I am on listing details page of listing with ID "<listing id>"
    And I click the 'Eye'
    Then I see the product history of product edit is recorded as "Product changed from"
    Examples:
      |ROLE       |product id|developer id|listing id|
      |ROLE_ACB   |1031      |673         |3783      |
      |ROLE_ADMIN |145       |1675        |4229      |
      |ROLE_ONC   |2102      |1298        |5737      |


