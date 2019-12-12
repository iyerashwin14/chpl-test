package gov.healthit.chpl.aqa.stepDefinitions;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import gov.healthit.chpl.aqa.pageObjects.UserManagementPage;

/**
 * Class UserManagementSteps definition.
 */
public class UserManagementSteps extends Base {

    @Given("^I'm on User Management page$")
    public void userManagementPage() {
        getDriver().get(getUrl() + "#/users");
    }

    @And("^I \"([^\"]*)\" role$")
    public void impersonateRole(final String impersonateRoleUsername) {
        WebElement button = UserManagementPage.impersonateRole(getDriver(), impersonateRoleUsername);
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", button);
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", button);
    }
}
