package gov.healthit.chpl.aqa.asserts;

import static org.testng.Assert.assertTrue;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import cucumber.api.java.en.Then;
import gov.healthit.chpl.aqa.pageObjects.BasePage;
import gov.healthit.chpl.aqa.pageObjects.DpManagementPage;
import gov.healthit.chpl.aqa.pageObjects.SurveillanceManagePage;
import gov.healthit.chpl.aqa.stepDefinitions.Base;
import gov.healthit.chpl.aqa.stepDefinitions.Hooks;

/**
 * Class DpmanagementUploadPageAsserts definition.
 */
public class DpmanagementUploadPageAsserts extends Base {
    /**
     * Assert upload success message.
     * @throws Exception if screenshot can't be taken
     */
    @Then("^I see upload successful message$")
    public void testUploadSuccessText() throws Exception {
        String successText = DpManagementPage.uploadSuccessfulText(getDriver()).getText();
        try {
            assertTrue(successText.contains("was uploaded successfully"));
        } catch (AssertionError ae) {
            Hooks.takeScreenshot();
            throw (ae);
        }
    }

    /**
     * Assert upload success message for surveillance upload.
     * @throws Exception if screenshot can't be taken
     */
    @Then("^I see upload successful message for surveillance upload$")
    public void testUploadSuccessTextForSurveillanceUpload() throws Exception {
        String successText = DpManagementPage.uploadSuccessfulTextSurveillanceUpload(getDriver()).getText();
        try {
            assertTrue(successText.contains("was uploaded successfully"));
        } catch (AssertionError ae) {
            Hooks.takeScreenshot();
            throw (ae);
        }
    }

    /**
     * Assert errors in upload failure message.
     * @param errorMessage expected error message for upload failure
     */
    @Then("^I see upload failure with appropriate error message \"([^\"]*)\" to indicate failure due to invalid data$")
    public void verifyLongTestTaskIdAndParticipantIdErrorsOnUpload(final String errorMessage) {
        String actualString = DpManagementPage.uploadFailureErrorMessage(getDriver()).getText();
        assertTrue(actualString.contains(errorMessage), "Expect \"" + errorMessage + "\" to be found in \"" + actualString + "\"");
    }

    /**
     * Load listing details to verify surveillance was uploaded successfully.
     * @param chplId is CHPL ID input on Surveillance Manage page to load surveillance activity for given listing
     */
    @Then("^I see that surveillance was uploaded successfully for listing with CHPL ID \"([^\"]*)\" and shows in surveillance activities list of the listing$")
    public void verifySurveillanceConfirmWasSuccessful(final String chplId) {
        getDriver().get(getUrl() + "/#/surveillance/manage");
        SurveillanceManagePage.generalFilterInput(getDriver()).sendKeys(chplId);
        getWait().until(ExpectedConditions.visibilityOf(BasePage.mainContent(getDriver())));
        List<WebElement> productName = getDriver().findElements(By.xpath("//*[@id=\"surveillance-manage\"]/div[2]/div/div/div/div[1]/div[2]/div/div/div/table/tbody/tr/td[3]"));
        List<WebElement> allChplIdButtons = getDriver().
                findElements(By.xpath("//*[@id=\"surveillance-manage\"]/div[2]/div/div/div/div[1]/div[2]/div/div/div/table/tbody/tr/td[1]/button"));

        for (int i = 0; i < productName.size(); i++) {

            if (productName.get(i).getText().equals("Clinical Document Exchange")) {
                ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", allChplIdButtons.get(i));
                break;
            }
        }

        SurveillanceManagePage.chplIdTab(getDriver()).click();
        String surveillanceText = SurveillanceManagePage.surveillanceList(getDriver()).getText();

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MMM d" + ", " + "yyyy");
        LocalDate newDate = LocalDate.now();

        assertTrue(surveillanceText.contains("Open Surveillance, Began " + dtf.format(newDate) + ": 1 Open and 2 Closed Non-Conformities Were Found"));
    }
}
