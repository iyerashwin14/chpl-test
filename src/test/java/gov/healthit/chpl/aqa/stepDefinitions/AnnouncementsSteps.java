package gov.healthit.chpl.aqa.stepDefinitions;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import gov.healthit.chpl.aqa.pageObjects.AnnouncementsPage;

/**
 * Class AnnouncementsSteps definition.
 */
public class AnnouncementsSteps extends Base {

    /**
     * Get user to the Announcements Page.
     **/
    @Given("^I navigate to the Announcements Management page$")
    public void navigateToAnnouncementsPage() {
        getDriver().get(getUrl() + "#/administration/announcements");
    }

    /**
     * Click Add Announcements on the Announcements page.
     **/
    @And("^I add an Announcement$")
    public void addAnnouncement() {
        JavascriptExecutor jse = (JavascriptExecutor) getDriver();
        jse.executeScript("arguments[0].scrollIntoView()", AnnouncementsPage.addAnnouncementsButton(getDriver()));
        AnnouncementsPage.addAnnouncementsButton(getDriver()).click();
    }

    /**
     * Clear the "Title/Announcement-Text/StartDate/EndDate" field and enter a value.
     * @param value to be entered
     * @param inputId is the id of the field in the Add Announcements section
     **/
    @And("^I set the \"(.*)\" field to \"(.*)\"$")
    public void setField(final String inputId, final String value) {
        AnnouncementsPage.announcementFieldInput(getDriver(), inputId).clear();
        AnnouncementsPage.announcementFieldInput(getDriver(), inputId).sendKeys(value);
        AnnouncementsPage.announcementFieldInput(getDriver(), inputId).sendKeys(Keys.TAB);
    }

    /**
     * Click Cancel button in the Announcement section.
     **/
    @And("^I click cancel button in the Announcement section$")
    public void clickCancelButtonInAnnouncementSection() {
        WebElement link = AnnouncementsPage.cancelButton(getDriver());
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView();", link);
    }

    /**
     * Click Save button in the Announcement section.
     **/
    @And("^I click save button in the Announcement section$")
    public void clickSaveButtonInAnnouncementSection() {
        WebElement link = AnnouncementsPage.saveButton(getDriver());
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView();", link);
        AnnouncementsPage.saveButton(getDriver()).click();
        this.navigateToAnnouncementsPage();
        WebDriverWait wait = new WebDriverWait(getDriver(), LONG_TIMEOUT);
        wait.until(ExpectedConditions.visibilityOfAllElements(AnnouncementsPage.allAnnouncements(getDriver())));
    }

    /**
     * Set the Announcement Text field.
     * @param fieldName is the name of the field in the Add Announcements section
     * @param role is the ROLE of the actor
     **/
    @And("^I set the \"(.*)\" field to today's date by \"(.*)\"$")
    public void setAnnouncementTitleField(final String fieldName, final String role) {
        AnnouncementsPage.announcementFieldInput(getDriver(), fieldName).sendKeys(getCurrentDate() + "-" + role);
    }

    /**
     * Get user to the Announcements Reports Page.
     **/
    @Given("^I navigate to the Announcements Reports page$")
    public void announcementsReportsPage() {
        getDriver().get(getUrl() + "/#/reports/announcements");
    }

    /**
     * Edit an existing announcement.
     * @param role the user role
     **/
    @And("^I edit an existing Announcement by \"(.*)\"$")
    public void editExistingAnnouncement(final String role) {
        List<WebElement> allAnnouncements = AnnouncementsPage.allAnnouncements(getDriver());

        for (WebElement row : allAnnouncements) {
            if (row.findElement(By.xpath(".//td[1]")).getText().equals(getCurrentDate() + "-" + role)) {
                WebElement button = row.findElement(By.xpath(".//button[contains(text(), 'Edit')]"));
                ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", button);
                break;
            }
        }
    }

    /**
     * Delete an announcement.
     **/
    @And("^I delete an Announcement$")
    public void deleteAnnouncement() {
        WebElement link = AnnouncementsPage.deleteAnnouncement(getDriver());
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView();", link);
        AnnouncementsPage.deleteAnnouncement(getDriver()).click();
    }
}
