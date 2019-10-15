@Regression
Feature: OCD-2966 New Admin nav - Announcements Management
  This test checks whether all the functionalities work as expected for announcements

Scenario Outline: User logs in as ROLE and verifies navigation is successful to the Announcements Management page
    Given I'm logged in as "<ROLE>"
    When I navigate to the Announcements Management page
    Then I see "Announcements" as the page title for the Announcements management page
    Examples:
      |ROLE       |
      |ROLE_ADMIN |
      |ROLE_ONC   |

Scenario Outline: Verify columns are displayed correct in the Announcements Management Page
    Given I'm logged in as "<ROLE>"
    When I navigate to the Announcements Management page
    Then I see "Title Announcement Start Date End Date Public" columns in the Announcements page
    Examples:
      |ROLE       |
      |ROLE_ADMIN |
      |ROLE_ONC   |

Scenario Outline: Verify error message is displayed when the title field is left blank
    Given I'm logged in as "<ROLE>"
    When I navigate to the Announcements Management page
    And I add an Announcement
    And I set the "title" field to ""
    Then I see "Title is required" error message 
    Examples:
      |ROLE       |
      |ROLE_ADMIN |
      |ROLE_ONC   |

Scenario Outline: Verify error message is displayed when the start-date field is left blank
    Given I'm logged in as "<ROLE>"
    When I navigate to the Announcements Management page
    And I add an Announcement
    And I set the "start-date" field to ""
    Then I see "Start Date is required" error message 
    Examples:
      |ROLE       |
      |ROLE_ADMIN |
      |ROLE_ONC   |

Scenario Outline: Verify error message is displayed when the end-date field is left blank
    Given I'm logged in as "<ROLE>"
    When I navigate to the Announcements Management page
    And I add an Announcement
    And I set the "end-date" field to ""
    Then I see "End Date is required" error message 
    Examples:
      |ROLE       |
      |ROLE_ADMIN |
      |ROLE_ONC   |

Scenario Outline: Verify clicking cancel button in the Announcements section navigates back to the Announcement page
    Given I'm logged in as "<ROLE>"
    When I navigate to the Announcements Management page
    And I add an Announcement
    And I click cancel button in the Announcement section
    Then I see "Announcements" as the page title for the Announcements management page
    Examples:
      |ROLE       |
      |ROLE_ADMIN |
      |ROLE_ONC   |

Scenario Outline: Verify adding and deleting announcement is saved and recorded on Announcements reports page
    Given I'm logged in as "<ROLE>"
    When I navigate to the Announcements Management page
    And I add an Announcement
    And I set the "title" field to today's date by "<ROLE>"
    And I set the "announcement-text" field to "Test Announcement feature"
    And I set the "start-date" field to "07/29/2019"
    And I set the "end-date" field to "12/31/2050"
    And I click save button in the Announcement section
    Then I see the Announcement information is saved and recorded on Announcements page with "<ROLE>"
    And I navigate to the Announcements Reports page
    Then I see "Created announcement: " activity is recorded in Announcements Reports
    And I navigate to the Announcements Management page
    And I edit an existing Announcement by "<ROLE>"
    And I delete an Announcement
    And I confirm delete
    And I navigate to the Announcements Reports page
    Then I see "Deleted announcement: " activity is recorded in Announcements Reports
    Examples:
      |ROLE       |
      |ROLE_ADMIN |
      |ROLE_ONC   |
