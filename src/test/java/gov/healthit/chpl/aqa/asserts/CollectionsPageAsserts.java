package gov.healthit.chpl.aqa.asserts;

import static org.testng.Assert.assertTrue;

import cucumber.api.java.en.Then;
import gov.healthit.chpl.aqa.pageObjects.CollectionsPages;
import gov.healthit.chpl.aqa.stepDefinitions.Base;

/**
 * Class CollectionsPageAsserts definition.
 */
public class CollectionsPageAsserts extends Base {

    /**
     * Assert updated CMS FAQ link is as expected.
     * @param cmsfaqLink link to FAQs on cms.gov site
     */
    @Then("^the CMS FAQ link should point to updated link: \"([^\"]*)\"$")
    public void verifyCMSFAQLink(final String cmsfaqLink) {
        String link = CollectionsPages.cmsFaqLink(getDriver()).getAttribute("href");
        assertTrue(link.contains(cmsfaqLink), "Expect " + cmsfaqLink + " to be found in " + link);
    }

    /**
     * Assert text on given collection page.
     * @param text is info text displayed on collections pages
     */
    @Then("^I see updated note text: \"([^\"]*)\"$")
    public void verifyTextOnChartsPage(final String text) {
        String actualString = CollectionsPages.topInfoTextOnCollectionsPages(getDriver()).getText();
        assertTrue(actualString.contains(text), "Expect " + text + " to be found in " + actualString);
    }

}
