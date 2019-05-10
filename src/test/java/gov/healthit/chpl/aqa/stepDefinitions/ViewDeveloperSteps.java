package gov.healthit.chpl.aqa.stepDefinitions;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import gov.healthit.chpl.aqa.pageObjects.ViewDeveloperPage;

/**
 * Class ViewDeveloperSteps definition.
 */
public class ViewDeveloperSteps extends Base {

    /**
     * Get user to the View Developer Page.
     * @param developerId is Developer ID
     **/
    @Given("^I navigate to the developer page for developer \"([^\"]*)\"$")
    public void editDeveloperPage(final String developerId) {
        getDriver().get(getUrl() + "#/organizations/developers/" + developerId);
        WebDriverWait wait = new WebDriverWait(getDriver(), TIMEOUT);
        wait.until(ExpectedConditions.visibilityOf(ViewDeveloperPage.viewDeveloperPageTitle(getDriver())));
    }

    /**
     * Edit a developer.
     * @param developerId is the developer id passed in page objects
     **/
    @And("^I edit for developer \"(.*)\"$")
    public void editDeveloper(final String developerId) {
        clickEditLinkOfDeveloper(developerId);
        setDeveloperNameField(getCurrentDate());
        WebElement link = ViewDeveloperPage.editDeveloperSaveButton(getDriver(), developerId);
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", link);
    }

    /**
     * Click Edit link of a product.
     * @param developerId is the developer id passed in page objects
     **/
    @And("^I click the edit link for developer \"(.*)\"$")
    public void clickEditLinkOfDeveloper(final String developerId) {
        ViewDeveloperPage.editDeveloperLink(getDriver(), developerId).click();
    }

    /**
     * Clear the developer name field and enter a value.
     * @param value to be entered
     **/
    @And("^I set the developer name field to \"(.*)\"$")
    public void setDeveloperNameField(final String value) {
        ViewDeveloperPage.developerNameField(getDriver()).clear();
        ViewDeveloperPage.developerNameField(getDriver()).sendKeys(value);
    }

    /**
     * Clear the email field and enter a value.
     * @param value to be entered
     **/
    @And("^I set the email field to \"(.*)\"$")
    public void setEmailField(final String value) {
        ViewDeveloperPage.emailField(getDriver()).clear();
        ViewDeveloperPage.emailField(getDriver()).sendKeys(value);
    }

    /**
     * Click merge developer link of a developer.
     * @param developerId is the developer id passed in page objects
     **/
    @And("^I click the merge developer link for developer \"([^\"]*)\"$")
    public void iClickMergeVersionLink(final String developerId) {
        ViewDeveloperPage.mergeDeveloperLink(getDriver(), developerId).click();
    }

    /**
     * Click split developer link of a developer.
     * @param developerId is the developer id passed in page objects
     **/
    @And("^I click the split developer link for developer \"([^\"]*)\"$")
    public void iSplitDeveloperLink(final String developerId) {
        ViewDeveloperPage.splitDeveloperLink(getDriver(), developerId).click();
    }

}

