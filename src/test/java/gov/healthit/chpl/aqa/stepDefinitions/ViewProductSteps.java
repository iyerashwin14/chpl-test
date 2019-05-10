package gov.healthit.chpl.aqa.stepDefinitions;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import gov.healthit.chpl.aqa.pageObjects.DpManagementPage;
import gov.healthit.chpl.aqa.pageObjects.ViewProductPage;

/**
 * Class ViewVersionSteps definition.
 */
public class ViewProductSteps extends Base {

    /**
     * Get user to the View Version Page.
     * @param developerId is Developer ID
     * @param productId is Product ID
     **/
    @Given("^I navigate to the product edit page for product \"([^\"]*)\" of developer \"([^\"]*)\"$")
    public void editVersionPage(final String productId, final String developerId) {
        getDriver().get(getUrl() + "#/organizations/developers/" + developerId + "/products/" + productId);
        WebDriverWait wait = new WebDriverWait(getDriver(), TIMEOUT);
        wait.until(ExpectedConditions.visibilityOf(ViewProductPage.viewProductPageTitle(getDriver())));
    }

    /**
     * Click Edit link of a product.
     * @param productId is the product version id passed in page objects
     **/
    @And("^I click the edit link for product \"(.*)\"$")
    public void clickEditLinkOfProduct(final String productId) {
        ViewProductPage.editProductLink(getDriver(), productId).click();
    }

    /**
     * Clear the product name field and enter a value.
     * @param value to be entered
     **/
    @And("^I set the product name field to \"(.*)\"$")
    public void setProductNameField(final String value) {
        ViewProductPage.productNameField(getDriver()).clear();
        ViewProductPage.productNameField(getDriver()).sendKeys(value);
        ViewProductPage.productNameField(getDriver()).sendKeys(Keys.TAB);
    }

    /**
     * Edit a product.
     * @param productId is the product version id passed in page objects
     **/
    @And("^I edit for product \"(.*)\"$")
    public void editProduct(final String productId) {
        ViewProductPage.editProductLink(getDriver(), productId).click();
        ViewProductPage.productNameField(getDriver()).clear();
        ViewProductPage.productNameField(getDriver()).sendKeys(getCurrentDate());
        WebElement link = ViewProductPage.editProductSaveButton(getDriver(), productId);
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", link);
        getWait().withTimeout(LONG_TIMEOUT, TimeUnit.SECONDS).until(ExpectedConditions.visibilityOf(DpManagementPage.updateSuccessfulToastContainer(getDriver())));
    }
}

