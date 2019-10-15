package gov.healthit.chpl.aqa.asserts;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.java.en.Then;
import gov.healthit.chpl.aqa.pageObjects.AnnouncementsPage;
import gov.healthit.chpl.aqa.stepDefinitions.Base;

/**
 * Class AnnouncementsPageAsserts definition.
 */
public class AnnouncementsPageAsserts extends Base {

    /**
     * Assert that Announcements is expected text.
     * @param expectedPageTitle is Announcements
     */
    @Then("^I see \"([^\"]*)\" as the page title for the Announcements management page$")
    public void iSeePageTitle(final String expectedPageTitle) {
        String actualPageTitle = AnnouncementsPage.announcementsPageTitle(getDriver()).getText();
        assertEquals(expectedPageTitle, actualPageTitle);
    }

    /**
     * Assert that columns are displayed in the Announcements Page.
     * @param expectedColumnNames are "Title, Announcement, StartDate and EndDate"
     **/
    @Then("^I see \"([^\"]*)\" columns in the Announcements page$")
    public void seeColumnsInAnnouncementsPage(final String expectedColumnNames) {
        String actualColumnNames = AnnouncementsPage.announcementsColumnName(getDriver()).getText();
        assertTrue(actualColumnNames.contains(expectedColumnNames));
    }

    /**
     * Assert that error message is displayed for Title/StartDate/EndDate.
     * @param expectedErrorMessage is Title/StartDate/EndDate is required
     */
    @Then("^I see \"(.*)\" error message$")
    public void iSeeErrorMessage(final String expectedErrorMessage) {
        String actualErrorMessage = AnnouncementsPage.errorMessage(getDriver(), expectedErrorMessage).getText();
        assertEquals(actualErrorMessage, expectedErrorMessage);
    }

    /**
     * Assert that Announcement information is recorded on Announcements page.
     * @param role the user role
     */
    @Then("^I see the Announcement information is saved and recorded on Announcements page with \"(.*)\"$")
    public void announcementInfoRecordedOnAnnouncementsPage(final String role) {
        WebDriverWait wait = new WebDriverWait(getDriver(), LONG_TIMEOUT);
        Boolean announcementFound = false;
        wait.until(ExpectedConditions.visibilityOfAllElements(AnnouncementsPage.allAnnouncements(getDriver())));
        List<WebElement> rows = AnnouncementsPage.allAnnouncements(getDriver());
        for (WebElement row : rows) {
            if (row.findElement(By.xpath(".//td[1]")).getText().equals(getCurrentDate() + "-" + role)) {
                announcementFound = true;
            }
        }
        assertTrue(announcementFound, ("Announcement " + getCurrentDate() + " not found"));
    }

    /**
     * Assert that Announcement information is correct in Announcements Reports.
     * @param announcementActivity is Created/Deleted announcement
     */
    @Then("^I see \"(.*)\" activity is recorded in Announcements Reports$")
    public void announcementInfoRecordedInAnnouncementsReports(final String announcementActivity) {
        String actualAnnouncementInformation = AnnouncementsPage.announcementReportDetails(getDriver()).getText();
        assertTrue(actualAnnouncementInformation.contains(announcementActivity + getCurrentDate()));

    }
}
