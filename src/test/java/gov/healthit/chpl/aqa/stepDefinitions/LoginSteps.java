package gov.healthit.chpl.aqa.stepDefinitions;

import static org.testng.Assert.assertTrue;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.support.ui.ExpectedConditions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import gov.healthit.chpl.aqa.pageObjects.LoginPage;

/**
 * Class LoginSteps definition.
 */
public class LoginSteps extends Base {

    private String roleAdminUsername = System.getProperty("roleAdminUsername");
    private String roleAdminPassword = System.getProperty("roleAdminPassword");
    private String roleOncUsername = System.getProperty("roleOncUsername");
    private String roleOncPassword = System.getProperty("roleOncPassword");
    private String roleAcbUsername = System.getProperty("roleAcbUsername");
    private String roleAcbPassword = System.getProperty("roleAcbPassword");

    /**
     * Verify login attempt was successful.
     * @param name as AQA Admin, AQA ONC or AQA ACB
     */
    @Then("^I should be logged in to CHPL as \"([^\"]*)\"$")
    public void verifyLoginWasSuccessful(final String name) {
        String actualString = LoginPage.welcomeText(getDriver()).getText();
        assertTrue(actualString.contains(name));
    }

    /**
     * Log the user in as given ROLE.
     * @param role as ROLE_ADMIN, ROLE_ONC or ROLE_ACB
     */
    @Given("^I'm logged in as \"([^\"]*)\"$")
    public void logInAsRole(final String role) {
        String username = null;
        String password = null;
        if (role.equalsIgnoreCase("ROLE_ADMIN")) {
            if (StringUtils.isEmpty(roleAdminUsername)) {
                throw new IllegalArgumentException("Missing value for roleAdminUsername!");
            }
            if (StringUtils.isEmpty(roleAdminPassword)) {
                throw new IllegalArgumentException("Missing value for roleAdminPassword!");
            }
            username = roleAdminUsername;
            password = roleAdminPassword;
        } else if (role.equalsIgnoreCase("ROLE_ONC")) {
            if (StringUtils.isEmpty(roleOncUsername)) {
                throw new IllegalArgumentException("Missing value for roleOncUsername!");
            }
            if (StringUtils.isEmpty(roleOncPassword)) {
                throw new IllegalArgumentException("Missing value for roleOncPassword!");
            }
            username = roleOncUsername;
            password = roleOncPassword;
        } else if (role.equalsIgnoreCase("ROLE_ACB")) {
            if (StringUtils.isEmpty(roleAcbUsername)) {
                throw new IllegalArgumentException("Missing value for roleAcbUsername!");
            }
            if (StringUtils.isEmpty(roleAcbPassword)) {
                throw new IllegalArgumentException("Missing value for roleAcbPassword!");
            }
            username = roleAcbUsername;
            password = roleAcbPassword;
        }
        getDriver().get(getUrl() + "#/search");
        //pop up the login/out section
        getWait().until(ExpectedConditions.visibilityOf(LoginPage.loginLogoutPopUp(getDriver())));
        LoginPage.loginLogoutPopUp(getDriver()).click();
        //when it's popped up we can see the username
        getWait().until(ExpectedConditions.visibilityOf(LoginPage.username(getDriver())));
        LoginPage.username(getDriver()).sendKeys(username);
        LoginPage.password(getDriver()).sendKeys(password);
        LoginPage.loginButton(getDriver()).click();
        getWait().until(ExpectedConditions.visibilityOf(LoginPage.logoutButton(getDriver())));
        //minimize the pop up of login/out section
        getWait().until(ExpectedConditions.visibilityOf(LoginPage.loginLogoutPopUp(getDriver())));
        LoginPage.loginLogoutPopUp(getDriver()).click();
    }

    /**
     * Log the user in as given ROLE in a given environment.
     * @param role as ROLE_ADMIN, ROLE_ONC or ROLE_ACB
     * @param tEnv test environment in which tests will be run
     */
    @Given("^I'm logged in as \"([^\"]*)\" on \"([^\"]*)\"$")
    public void logInAsRoleOnGivenEnv(final String role, final String tEnv) {

        String username = roleAdminUsername;
        String password = roleAdminPassword;

        this.getEnvUrl(tEnv);
        getDriver().get(getEnvUrl(tEnv) + "#/search");

        getWait().until(ExpectedConditions.visibilityOf(LoginPage.loginLogoutPopUp(getDriver())));
        LoginPage.loginLogoutPopUp(getDriver()).click();

        getWait().until(ExpectedConditions.visibilityOf(LoginPage.username(getDriver())));
        LoginPage.username(getDriver()).sendKeys(username);
        LoginPage.password(getDriver()).sendKeys(password);
        LoginPage.loginButton(getDriver()).click();
        getWait().until(ExpectedConditions.visibilityOf(LoginPage.logoutButton(getDriver())));
    }
}
