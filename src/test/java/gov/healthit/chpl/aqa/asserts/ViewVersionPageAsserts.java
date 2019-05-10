package gov.healthit.chpl.aqa.asserts;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.java.en.Then;
import gov.healthit.chpl.aqa.pageObjects.DpManagementPage;
import gov.healthit.chpl.aqa.pageObjects.ViewVersionPage;
import gov.healthit.chpl.aqa.stepDefinitions.Base;

/**
 * Class ViewVersionPageAsserts definition.
 */
public class ViewVersionPageAsserts extends Base {
    /**
     * Assert that version is correct.
     * @param versionId is the product version id passed in page objects
     */
    @Then("^I see the version is recorded and updated on View Version page of \"(.*)\"$")
    public void versionIsUpdatedOnViewVersionPage(final String versionId) {
        getWait().withTimeout(LONG_TIMEOUT, TimeUnit.SECONDS).until(ExpectedConditions.visibilityOf(DpManagementPage.updateSuccessfulToastContainer(getDriver())));
        String actualProductVersion = ViewVersionPage.viewSavedVersion(getDriver(), versionId).getText();
        assertTrue(actualProductVersion.contains(getCurrentDate()));
    }

    /**
     * Assert that edit version link exists on view version page.
     * @param versionId is the product version id passed in page objects
     */
    @Then("^I see edit link for version \"(.*)\"$")
    public void iSeeEditLinkToEditTheVersion(final String versionId) {
        assertTrue(ViewVersionPage.editVersionLink(getDriver(), versionId).isDisplayed());
    }

    /**
     * Assert that merge version link exists on view version page.
     * @param versionId is the product version id passed in page objects
     */
    @Then("^I see merge link for version \"(.*)\"$")
    public void iSeeMergeLinkToMergeTheVersion(final String versionId) {
        assertTrue(ViewVersionPage.mergeVersionLink(getDriver(), versionId).isDisplayed());
    }

    /**
     * Assert that expected text is displayed in versions to merge section.
     * @param expectedText is "At least one other Version must be selected to merge"
     */
    @Then("^I see \"([^\"]*)\" in versions to merge section$")
    public void iSeeMessageInVersionsToMergeSection(final String expectedText) {
        String actualText = ViewVersionPage.versionsToMergeMessage(getDriver()).getText();
        assertEquals(expectedText, actualText);
    }

    /**
     * Assert that "Field is required" error message is displayed.
     * @param expectedErrorMessage is the expected error message
     * @param versionId is the product version id passed in page objects
     */
    @Then("^I see error message \"(.*)\" for version \"(.*)\"$")
    public void iSeeFieldIsRequiredError(final String expectedErrorMessage, final String versionId) {
        WebDriverWait wait = new WebDriverWait(getDriver(), TIMEOUT);
        wait.until(ExpectedConditions.visibilityOf(ViewVersionPage.versionMissingErrorMessage(getDriver(), versionId)));
        String actualErrorMessage = ViewVersionPage.versionMissingErrorMessage(getDriver(), versionId).getText();
        assertEquals(actualErrorMessage, expectedErrorMessage);
    }
}

