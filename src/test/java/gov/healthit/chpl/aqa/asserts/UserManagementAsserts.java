package gov.healthit.chpl.aqa.asserts;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.java.en.Then;
import gov.healthit.chpl.aqa.pageObjects.UserManagementPage;
import gov.healthit.chpl.aqa.stepDefinitions.Base;

/**
 * Class UserManagementAsserts definition.
 */
public class UserManagementAsserts extends Base {

    @Then("^I see \"([^\"]*)\" as UserName$")
    public void impersonatingRoleName(final String expectedRoleUsername) throws Exception {
        WebDriverWait wait = new WebDriverWait(getDriver(), LONG_TIMEOUT);
        wait.until(ExpectedConditions.visibilityOf(UserManagementPage.mainContent(getDriver())));
        String actualRoleUsername = UserManagementPage.impersonateRoleUsername(getDriver()).getText();
        assertTrue(actualRoleUsername.contains(expectedRoleUsername));
    }
}
