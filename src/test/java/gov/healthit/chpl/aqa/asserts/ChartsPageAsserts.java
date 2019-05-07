package gov.healthit.chpl.aqa.asserts;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;

import cucumber.api.java.en.Then;
import gov.healthit.chpl.aqa.pageObjects.ChartsPage;
import gov.healthit.chpl.aqa.stepDefinitions.Base;

/**
 * Class ChartsPageAsserts definition.
 */
public class ChartsPageAsserts extends Base {
    /**
     * Assert that urlLink and text is correct.
     * Feature: Replace email with feedback form url.
     * @param textName expected Health IT Feedback Form
     * @param urlLink expected 'https://www.healthit.gov/form/healthit-feedback-form'
     */
    @Then("^\"([^\"]*)\" points to \"([^\"]*)\"$")
    public void healthITFeedbackFormPointsToURL(final String textName, final String urlLink) {
        WebDriver driver = getDriver();
        String actualText = ChartsPage.chartsHealthITFeedback(driver).getText();
        String actualURL = ChartsPage.chartsHealthITFeedback(driver).getAttribute("href");
        assertEquals(actualText, textName);
        assertEquals(actualURL, urlLink);
    }

    /**
     * Assert text on charts page.
     * @param chartsText is info text displayed on charts page
     */
    @Then("^I see text \"([^\"]*)\" on charts page$")
    public void verifyTextOnChartsPage(final String chartsText) {
        String actualString = ChartsPage.chartsInfoText(getDriver()).getText();
        assertTrue(actualString.contains(chartsText), "Expect " + chartsText + " to be found in " + actualString);
    }
}
