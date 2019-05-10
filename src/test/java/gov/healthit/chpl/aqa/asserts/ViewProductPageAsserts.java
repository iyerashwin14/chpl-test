package gov.healthit.chpl.aqa.asserts;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

import org.openqa.selenium.NoSuchElementException;

import cucumber.api.java.en.Then;
import gov.healthit.chpl.aqa.pageObjects.ViewProductPage;
import gov.healthit.chpl.aqa.stepDefinitions.Base;

/**
 * Class ViewProductPageAsserts definition.
 */
public class ViewProductPageAsserts extends Base {

    /**
     * Assert that product edit link exists on view version page.
     * @param productId is the product id passed in page objects
     */
    @Then("^I see product edit link for product \"([^\"]*)\"$")
    public void iSeeProductEditLink(final String productId) {
        try {
            assertTrue(ViewProductPage.editProductLink(getDriver(), productId).isDisplayed());
        } catch (NoSuchElementException e) {
            fail("Unable to find product edit link");
        }
    }

    /**
     * Assert that product split link exists on view version page.
     * @param productId is the product id passed in page objects
     */
    @Then("^I see product split link for product \"([^\"]*)\"$")
    public void iSeeProductSplitLink(final String productId) {
        try {
            assertTrue(ViewProductPage.splitProductLink(getDriver(), productId).isDisplayed());
        } catch (NoSuchElementException e) {
            fail("Unable to find product split link");
        }
    }

    /**
     * Assert that product merge link exists on view version page.
     * @param productId is the product id passed in page objects
     */
    @Then("^I see product merge link for product \"([^\"]*)\"$")
    public void iSeeProductMergeLink(final String productId) {
        try {
            assertTrue(ViewProductPage.mergeProductLink(getDriver(), productId).isDisplayed());
        } catch (NoSuchElementException e) {
            fail("Unable to find product merge link");
        }
    }

    /**
     * Assert that "Field is required" error message is displayed for Product Name.
     * @param expectedErrorMessage is the expected error message
     * @param productId is the product id passed in page objects
     */
    @Then("^I see error message \"(.*)\" for product \"(.*)\"$")
    public void iSeeFieldIsRequiredError(final String expectedErrorMessage, final String productId) {
        String actualErrorMessage = ViewProductPage.productNameMissingErrorMessage(getDriver(), productId).getText();
        assertEquals(actualErrorMessage, expectedErrorMessage);
    }

    /**
     * Assert that product name is correct.
     * @param productId is the product id passed in page objects
     */
    @Then("^I see the edited product information is recorded and updated on View Product page for product \"(.*)\"$")
    public void editedInformationRecordedAndUpdated(final String productId) {
        String actualProductVersion = ViewProductPage.viewProductName(getDriver(), productId).getText();
        assertTrue(actualProductVersion.contains(getCurrentDate()));
    }
}


