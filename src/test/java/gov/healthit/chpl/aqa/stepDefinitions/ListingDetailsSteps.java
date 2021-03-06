package gov.healthit.chpl.aqa.stepDefinitions;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import gov.healthit.chpl.aqa.pageObjects.ListingDetailsPage;

/**
 * Class ListingDetailsSteps definition.
 */

public class ListingDetailsSteps extends Base {

    /**
     * Default constructor.
     */
    public ListingDetailsSteps() {
        super();
    }

    /**
     * Loads a listing for given Database ID.
     * @param id the Database or CHPL ID of a listing to load
     */
    @Given("^I am on listing details page of listing with ID \"(.*)\"$")
    public void loadListingWithDbId(final String id) {
        getDriver().get(getUrl() + "#/product/" + id);
        getWait().until(ExpectedConditions.visibilityOf(ListingDetailsPage.mainContent(getDriver())));
    }

    /**
     * Open the details for designated certification criteria.
     * @param number criteria to open
     */
    @When("^I open details for criteria \"([^\"]*)\"$")
    public void iOpenDetailsForACriteria(final String number) {
        WebElement link = ListingDetailsPage.certificationCriteriaDetailsLink(getDriver(), number);
        //        Actions actions = new Actions(getDriver());
        //        actions.moveToElement(link);
        //        actions.perform();
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true); window.scrollBy(0, -100);", link);
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", link);
    }

    /**
     * Open the SED panel.
     */
    @When("^I look at SED details$")
    public void iLookAtSEDDetails() {
        WebElement link = ListingDetailsPage.sedAccordion(getDriver());
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", link);
    }

    /**
     * Open Additional Information Accordion.
     */
    @When("^I look at Additional Information$")
    public void iLookAtAdditionalInformation() {
        WebElement link = ListingDetailsPage.additionalInfoAccordion(getDriver());
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", link);
        getWait().until(ExpectedConditions.visibilityOf(ListingDetailsPage.meaningfulUseUsers(getDriver())));
    }

    /**
     * Open the G1/G2 panel.
     */
    @When("^I look at Successfully Tested G1G2 Measures$")
    public void iLookAtG1G2Measures() {
        WebElement link = ListingDetailsPage.g1g2Accordion(getDriver());
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", link);
    }

    /**
     * Select view all CQMs and open CQM accordion.
     */
    @When("^I look at CQM details on listing details page$")
    public void opencqmPanel() {
        ListingDetailsPage.viewAllCertificationCriteriaCQMs(getDriver()).click();
        WebElement link = ListingDetailsPage.cqmAccordion(getDriver());
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", link);
    }
}
