package gov.healthit.chpl.aqa.asserts;

import static org.testng.Assert.assertEquals;

import cucumber.api.java.en.Then;
import gov.healthit.chpl.aqa.pageObjects.OncAcbManagementPage;
import gov.healthit.chpl.aqa.stepDefinitions.Base;

/**
 * Class OncAcbManagementPageAsserts definition.
 */

public class OncAcbManagementPageAsserts extends Base {

    /**
     * Assert Error message when future retirement date is selected.
     * @param errorMessage expected to be found
     */
    @Then("^I see \"(.*)\"$")
    public void iSeeErrorMessage(final String errorMessage) {
        String actualErrorMessage = OncAcbManagementPage.errorMessage(getDriver()).getText();
        assertEquals(actualErrorMessage, errorMessage);
    }

    /**
     * Assert Retired status.
     * @param expectedRetirementStatus is the status of retirement: yes or no
     */
    @Then("^ONC ACB status should show as Retired: \"([^\"]*)\"$")
    public void retiredStatus(final String expectedRetirementStatus) {
        String actualRetirementStatus = OncAcbManagementPage.retirementStatus(getDriver(), expectedRetirementStatus).getText().split(",")[0];
        assertEquals(actualRetirementStatus, expectedRetirementStatus);
    }
}
