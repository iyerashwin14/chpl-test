package gov.healthit.chpl.aqa.stepDefinitions;

import java.io.FileNotFoundException;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import gov.healthit.chpl.aqa.pageObjects.CollectionsPages;

/**
 * Class CollectionsPagesSteps definition.
 */
public class CollectionsPagesSteps extends Base {
    /**
     * Open ACB filter options.
     */
    @When("^I look at ACB filter options$")
    public void openAcbFilterOptions() {
        WebElement button = CollectionsPages.acbFilterButton(getDriver());
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", button);
    }

    /**
     * Loads collections page in test environment.
     * @param pageName to get page name in url
     * @param tEnv environment in which tests will be run
     */
    @Given("^I am on collections page: \"([^\"]*)\" on \"([^\"]*)\"$")
    public void loadCollectionsPage(final String pageName, final String tEnv) {
        getDriver().get(getEnvUrl(tEnv) + "#/collections/" + pageName);
        WebDriverWait wait = new WebDriverWait(getDriver(), TIMEOUT);
        wait.until(ExpectedConditions.visibilityOf(CollectionsPages.mainContent(getDriver())));
    }

    /**
     * Download SED Details.csv file.
     * @param fileName expected file name
     * @throws FileNotFoundException if the SED Details File not found
     */
    @When("^I download the \"([^\"]*)\" file$")
    public void downloadSEDDetailsFile(final String fileName) throws FileNotFoundException {
        WebElement link = CollectionsPages.sedDetailsFileButton(getDriver());
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", link);
        super.checkIfFileIsDownloaded(fileName);
    }
}

