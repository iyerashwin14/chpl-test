package gov.healthit.chpl.aqa.stepDefinitions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import gov.healthit.chpl.aqa.pageObjects.BasePage;
/**
 * Class BaseSteps definition.
 */
public class BaseSteps extends Base {
    /**
     * Click CHPL Resources Top Navigation.
     */
    @When("^I click CHPL Resources Top Navigation Link$")
    public void iclickCHPLResourcesTopNavigationLink() {
        WebDriver driver =  getDriver();
        WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);
        wait.until(ExpectedConditions.visibilityOf(BasePage.chplResourcesDropdown(driver))).click();
    }

    /**
     * Assert that title should be CHPL API.
     * @param title expected as CHPL API
     */
    @Then("^the page title should be \"([^\"]*)\"$")
    public void pageTitleShouldBe(final String title) {
        String pageTitle = getDriver().getTitle();
        Assert.assertEquals(pageTitle, title);
    }
}
