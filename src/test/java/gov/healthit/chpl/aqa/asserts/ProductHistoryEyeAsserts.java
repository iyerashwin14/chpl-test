package gov.healthit.chpl.aqa.asserts;

import static org.testng.Assert.assertTrue;

import cucumber.api.java.en.Then;
import gov.healthit.chpl.aqa.pageObjects.ProductHistoryEyePage;
import gov.healthit.chpl.aqa.stepDefinitions.Base;

/**
 * Class ProductHistoryEyeAsserts definition.
 */
public class ProductHistoryEyeAsserts extends Base {

    /**
     * Assert that expected developer edit details is displayed on product history eye.
     * @param expectedDeveloperEditDetails is "Developer changed from"
     */
    @Then("^I see the product history of developer edit is recorded as \"([^\"]*)\"$")
    public void iSeeProductHistoryOfDeveloper(final String expectedDeveloperEditDetails) {
        String actualDeveloperEditDetails = ProductHistoryEyePage.productHistoryDeveloper(getDriver()).getText();
        assertTrue((actualDeveloperEditDetails.startsWith(expectedDeveloperEditDetails)));
    }

    /**
     * Assert that expected version edit details is displayed on product history eye.
     * @param expectedVersionEditDetails is "Version changed from"
     */
    @Then("^I see the product history of version edit is recorded as \"([^\"]*)\"$")
    public void iSeeProductHistoryOfVersion(final String expectedVersionEditDetails) {
        String actualVersionEditDetails = ProductHistoryEyePage.productHistoryVersion(getDriver()).getText();
        assertTrue((actualVersionEditDetails.startsWith(expectedVersionEditDetails)));
    }

    /**
     * Assert that expected product edit details is displayed on product history eye.
     * @param expectedProductEditDetails is "Product changed from"
     */
    @Then("^I see the product history of product edit is recorded as \"([^\"]*)\"$")
    public void iSeeProductHistoryOfProduct(final String expectedProductEditDetails) {
        String actualProductEditDetails = ProductHistoryEyePage.productHistoryProduct(getDriver()).getText();
        assertTrue((actualProductEditDetails.startsWith(expectedProductEditDetails)));
    }

}

