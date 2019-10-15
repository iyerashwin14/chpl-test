package gov.healthit.chpl.aqa.stepDefinitions;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import gov.healthit.chpl.aqa.pageObjects.SurveillanceReportPage;

/**
 * Class SurveillanceSteps definition.
 */
public class SurveillanceReportSteps extends Base {

    /**
     * Get user to the Surveillance Reports Page.
     **/
    @Given("^I navigate to the surveillance reports page$")
    public void navigateToSurveillanceReportsPage() {
        getDriver().get(getUrl() + "#/surveillance/reporting");
    }

    /**
     * Expand ACBs.
     * @param acb - Drummond Group, ICSA Labs, SLI Compliance and UL LLC
     **/
    @And("I expand \"([^\"]*)\"$")
    public void iExpandACB(final String acb) {
        WebElement link = SurveillanceReportPage.oncAcbs(getDriver(), acb);
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView();", link);
        SurveillanceReportPage.oncAcbs(getDriver(), acb).click();
    }

    /**
     * Initiate a quarterly surveillance report.
     * @param acbNameYearQuarter is the name of ACB, Year and Quarter for which a quarterly surveillance report will be initiated
     **/
    @And("I initate a quarterly surveillance report for \"([^\"]*)\"$")
    public void iInitiateQuarterlySurveillanceReportForACB(final String acbNameYearQuarter) {
        WebElement link = SurveillanceReportPage.initiateSurveillanceReport(getDriver(), acbNameYearQuarter);
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView();", link);
        SurveillanceReportPage.initiateSurveillanceReport(getDriver(), acbNameYearQuarter).click();
    }

    /**
     * Click yes to confirm initiating a quarterly surveillance report.
     **/
    @When("^I confirm initiaing a quarterly surveillance report$")
    public void confirmInitiatingQuarterlySurveillanceReport() {
        WebElement button = SurveillanceReportPage.yesToConfirm(getDriver());
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", button);
    }

    /**
     * Click yes to confirm deleting a quarterly surveillance report.
     **/
    @When("^I confirm deleting a quarterly surveillance report$")
    public void confirmDeletingQuarterlySurveillanceReport() {
        WebElement link = SurveillanceReportPage.yesToConfirm(getDriver());
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView();", link);
        SurveillanceReportPage.yesToConfirm(getDriver()).click();
    }

    /**
     * Click delete button to delete a quarterly surveillance report.
     **/
    @And("I delete the quarterly surveillance report$")
    public void iDeleteQuarterlySurveillanceReport() {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("arguments[0].scrollIntoView();", SurveillanceReportPage.transparencyDisclosureField(getDriver()));
        SurveillanceReportPage.deleteReportButton(getDriver()).click();
    }

    /**
     * Clear the "Surveillance Activities/Reactive Summary/Prioritized Elements/Transparency Disclosure" field and enter a value.
     * @param value to be entered
     * @param inputId is the id of the field in the Quarterly Report section
     **/
    @And("I set \"([^\"]*)\" field to \"([^\"]*)\"$")
    public void setQuarterlySurveillanceReportField(final String inputId, final String value) {
        SurveillanceReportPage.quarterlyReportFieldInput(getDriver(), inputId).clear();
        SurveillanceReportPage.quarterlyReportFieldInput(getDriver(), inputId).sendKeys(value);
        SurveillanceReportPage.quarterlyReportFieldInput(getDriver(), inputId).sendKeys(Keys.TAB);
    }

    /**
     * Click save button to save the quarterly surveillance report.
     **/
    @And("I save the quarterly surveillance report$")
    public void iSaveQuarterlySurveillanceReport() {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("arguments[0].scrollIntoView();", SurveillanceReportPage.transparencyDisclosureField(getDriver()));
        SurveillanceReportPage.saveQuarterlyReport(getDriver()).click();
    }

    /**
     * Edit a quarterly surveillance report.
     * @param acbNameYearQuarter is the name of ACB, Year and Quarter for which a quarterly surveillance report will be edited
     **/
    @And("I edit a quarterly surveillance report for \"([^\"]*)\"$")
    public void iEditQuarterlySurveillanceReport(final String acbNameYearQuarter) {
        SurveillanceReportPage.editSurveillanceReport(getDriver(), acbNameYearQuarter).click();
    }

    /**
     * Get anonymous user to the Surveillance Reports Page.
     **/
    @Given("^I navigate to the surveillance reports page as anonymous user$")
    public void navigateToSurveillanceReportsPageAsAnonymousUser() {
        getDriver().get(getUrl() + "#/surveillance/reporting");
    }

    @And("^I click open Listings with relevant surveillance accordion$")
    public void openListingsWithRelevantSurveillanceAccordion() {
        WebElement button = SurveillanceReportPage.listingsWithRelevantSurveillanceAccordion(getDriver());
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", button);
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", button);
    }

    @And("^I click View listing surveillance data button for CHPL ID \"([^\"]*)\"$")
    public void clickViewListingSurveillanceDataButton(final String chplID) {
        SurveillanceReportPage.viewListingSurveillanceDataButton(getDriver(), chplID).click();
    }

    @And("^I click Edit surveillance data button for Surveillance Id \"([^\"]*)\"$")
    public void clickEditSurveillanceDataButton(final String friendlySurvId) {
        WebElement button =  SurveillanceReportPage.editSurveillanceDataButton(getDriver(), friendlySurvId);
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", button);
    }

    @And("^I enter surveillance data in fields on form: \"([^\"]*)\" \"([^\"]*)\"$")
    public void enterSurveillanceData(final String outcome, final String processType) {
        getDriver().manage().window().maximize();
        SurveillanceReportPage.surveillanceOutcomeDropdown(getDriver()).sendKeys(outcome);
        SurveillanceReportPage.surveillanceProcessTypeDropdown(getDriver()).sendKeys(processType);
        SurveillanceReportPage.groundsForInitiatingInput(getDriver()).sendKeys(getCurrentDate());
        SurveillanceReportPage.surveillancePotentialCauses(getDriver()).sendKeys(getCurrentDate());
        SurveillanceReportPage.surveillanceadditionalCostEvaluation(getDriver()).sendKeys(getCurrentDate());
        SurveillanceReportPage.surveillanceLimitationsEvaluation(getDriver()).sendKeys(getCurrentDate());
        SurveillanceReportPage.surveillanceCompletedCapVerification(getDriver()).sendKeys(getCurrentDate());
    }

    @When("^I click Save Surveillance Data button$")
    public void clickSaveSurveillanceDataButton() {
        WebElement button =  SurveillanceReportPage.saveSurveillanceDataButton(getDriver());
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", button);
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", button);
    }

}
