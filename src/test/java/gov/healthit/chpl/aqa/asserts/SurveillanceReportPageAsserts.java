package gov.healthit.chpl.aqa.asserts;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import cucumber.api.java.en.Then;
import gov.healthit.chpl.aqa.pageObjects.SurveillanceReportPage;
import gov.healthit.chpl.aqa.stepDefinitions.Base;
import gov.healthit.chpl.aqa.stepDefinitions.SurveillanceReportSteps;

/**
 * Class SurveillanceReportPageAsserts definition.
 */
public class SurveillanceReportPageAsserts extends Base {

    /**
     * Assert that CHPL Surveillance is the expected text.
     * @param expectedPageTitle is CHPL Surveillance
     */
    @Then("^I see \"([^\"]*)\" as the page title for the surveillance report page$")
    public void iSeePageTitle(final String expectedPageTitle) {
        WebElement link = SurveillanceReportPage.surveillancePageSubtitle(getDriver());
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView();", link);
        String actualPageTitle = SurveillanceReportPage.surveillancePageTitle(getDriver()).getText();
        assertEquals(expectedPageTitle, actualPageTitle);
    }

    /**
     * Assert that ACBs are expected to be displayed under available reports.
     * @param expectedACBs are Drummond Group, ICSA Labs, SLI Compliance and UL LLC
     */
    @Then("^I see available reports for \"([^\"]*)\"$")
    public void iSeeReportsAvailableFor(final String expectedACBs) {
        String actualACBs = SurveillanceReportPage.oncAcbs(getDriver(), expectedACBs).getText();
        assertTrue(actualACBs.contains(expectedACBs));
    }

    /**
     * Assert that page title is correct is for the Quarterly Reports page.
     * @param expectedPageTitle is Quarterly Report
     */
    @Then("^I see \"([^\"]*)\" for the quarterly surveillance report page$")
    public void iSeePageTitleForQuarterlySurveillanceReport(final String expectedPageTitle) {
        String actualPageTitle = SurveillanceReportPage.quarterlySurveillanceReportingTitle(getDriver(), expectedPageTitle).getText();
        assertTrue(actualPageTitle.contains(expectedPageTitle));
    }

    /**
     * Assert that subtitle is correct is for the Quarterly Reports page.
     * @param expectedSubtitle - UL LLC/Drummond Group/SLI Compliance/ICSA Labs Quarterly Surveillance Reporting
     */
    @Then("^\"([^\"]*)\" for the quarterly surveillance report$")
    public void iSeeSubtitle(final String expectedSubtitle) {
        String actualSubtitle = SurveillanceReportPage.quarterlySurveillanceReportingSubtitle(getDriver(), expectedSubtitle).getText();
        assertTrue(actualSubtitle.contains(expectedSubtitle));
    }

    /**
     * Assert that confirmation message is correct on initiating a surveillance report.
     * @param expectedConfirmationMessage - Are you sure you wish to initiate quarterly surveillance reporting for quarter Q1/Q2/Q3/Q4 of year 2019/2020?
     */
    @Then("^I see \"([^\"]*)\" on clicking initiate$")
    public void iSeeConfirmationMessageOnClickingInitiate(final String expectedConfirmationMessage) {
        String actualConfirmationMessage = SurveillanceReportPage.confirmMessage(getDriver()).getText();
        assertTrue(actualConfirmationMessage.contains(expectedConfirmationMessage));
    }

    /**
     * Assert that confirmation message is correct on deleting a surveillance report.
     * @param expectedConfirmationMessage - Are you sure you wish to delete this Quarterly Surveillance Report?
     */
    @Then("^I see \"([^\"]*)\" on clicking delete$")
    public void iSeeConfirmationMessageOnClickingDelete(final String expectedConfirmationMessage) {
        String actualConfirmationMessage = SurveillanceReportPage.confirmMessage(getDriver()).getText();
        assertTrue(actualConfirmationMessage.contains(expectedConfirmationMessage));
    }

    /**
     * Assert that page title is not found for the surveillance report page.
     */
    @Then("^I do not see the page title for the surveillance report page$")
    public void iDoNotSeePageTitleForSurveillanceRport() {
            boolean chplSurveillancePageFound = false;
            try {
                chplSurveillancePageFound = SurveillanceReportPage.surveillancePageSubtitle(getDriver()).isDisplayed();
                fail("Navigated to CHPL Surveillance Reports page when shouldn't have");
            } catch (NoSuchElementException e) {
                assertFalse(chplSurveillancePageFound, "Navigation to CHPL Surveillance Reports page is successful");
            }
        }

    /**
     * Assert that button name is correct on Available reports page.
     * @param expectedButtonName - Initiate
     * @param acbYearQuarter - name of the ACB, Year and Quarter
     */
    @Then("^I see the label of Q# button is changed to \"([^\"]*)\" for \"([^\"]*)\"$")
    public void iSeeLabelOfQuarterButton(final String expectedButtonName, final String acbYearQuarter) {
        WebElement link = SurveillanceReportPage.initiateSurveillanceReport(getDriver(), acbYearQuarter);
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView();", link);
        String actualButtonName = SurveillanceReportPage.initiateSurveillanceReport(getDriver(), acbYearQuarter).getText();
        assertTrue(actualButtonName.contains(expectedButtonName));
    }

    /**
     * Assert that added surveillance data was saved.
     * @param friendlySurvId is ID of surveillance to which data was added
     */
    @Then("^I see that all entered data was saved for Surveillance Id \"([^\"]*)\"$")
    public void verifyThatSurveillanceDataIsSavedInReport(final String friendlySurvId) {
        SurveillanceReportSteps steps = new SurveillanceReportSteps();
        /* Workaround to resolve Stale Element Reference Exception-
         * reload the element after form update so it's attached to DOM: https://www.seleniumhq.org/exceptions/stale_element_reference.jsp*/
        getDriver().get(getUrl() + "#/surveillance/reporting");
        steps.iExpandACB("UL LLC");
        SurveillanceReportPage.editSurveillanceReport(getDriver(), "UL LLC-2019-Q1").click();
        WebElement button = SurveillanceReportPage.listingsWithRelevantSurveillanceAccordion(getDriver());
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", button);
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", button);
        SurveillanceReportPage.viewListingSurveillanceDataButton(getDriver(), "15.02.02.1703.A057.01.00.1.180301").click();

        WebElement editButton =  getDriver().findElement(By.id("edit-surveillance-data-SURV01"));
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", editButton);

        String groundsForInitiatingSurv = SurveillanceReportPage.surveillanceCompletedCapVerification(getDriver()).getAttribute("value");
        assertEquals((groundsForInitiatingSurv.split(" ")[0]).trim(), getCurrentDate());

    }
}
