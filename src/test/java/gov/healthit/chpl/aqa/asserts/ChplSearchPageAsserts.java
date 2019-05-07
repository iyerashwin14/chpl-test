package gov.healthit.chpl.aqa.asserts;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import cucumber.api.java.en.Then;
import gov.healthit.chpl.aqa.pageObjects.SearchPage;
import gov.healthit.chpl.aqa.stepDefinitions.Base;
import gov.healthit.chpl.aqa.stepDefinitions.Hooks;

/**
 * Class ChplSearchPageAsserts definition.
 */
public class ChplSearchPageAsserts extends Base {

    /**
     * Assert filter option checkbox is checked.
     * @param selectfilter is filter option selected
     */
    @Then("^I see that \"([^\"]*)\" checkbox is checked$")
    public void verifySLIOptionChecked(final String selectfilter) {
        assertTrue(SearchPage.filterOption(getDriver(), selectfilter).isSelected());
    }

    /**
     * Assert message when no results found.
     */
    @Then("^the search page shows 'No results found' message$")
    public void verifyMessage() {
        assertTrue(SearchPage.noResultsFound(getDriver()).getText().contains("No results found"));
    }

    /**
     * Asserts that expected listing is returned in result.
     * @param chplId id for listing to expect in search results
     */
    @Then("^I should see listing \"([^\"]*)\" in CHPL search results$")
    public void searchResultsShowSliListing(final String chplId) {
        String actualText = SearchPage.searchResultsChplId(getDriver()).getText();
        assertTrue(actualText.contains(chplId), "Expect " + chplId + " to be found in " + actualText);
    }

    /**
     * Asserts that given listing shows in search results.
     * @param chplId of listing to expect in search results
     */
    @Then("^the only listing with CHPL ID \"([^\"]*)\" appears in search results$")
    public void searchResultsShowListing(final String chplId) {
        String listing = SearchPage.searchResultsChplId(getDriver()).getText();
        assertTrue(listing.contains(chplId), "Expect " + chplId + " found as " + listing);
        String itemcount = SearchPage.resultCount(getDriver()).getText();
        assertTrue(itemcount.contains("1 - 1 of 1 Result"), "Expect" + itemcount + " count found as " + itemcount);
    }

    /**
     * Assert CCHIT filter option checkbox is unchecked.
     * @param selectfilter is filter option unselected
     */
    @Then("^I see that \"([^\"]*)\" checkbox is unchecked$")
    public void verifyCCHITOptionUnchecked(final String selectfilter) {
        WebElement link = SearchPage.viewONCACBList(getDriver());
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", link);
        assertTrue(!(SearchPage.filterOption(getDriver(), selectfilter).isSelected()));
    }

    /**
     * Asserts that given listing shows expected status.
     * @param status of listing to expect in search results
     */
    @Then("^the certification status of the listing shows as \"([^\"]*)\"$")
    public void searchResultsShowNewStatus(final String status) {
        String currentStatus = SearchPage.resultsStatus(getDriver()).getAttribute("uib-tooltip");
        assertTrue(currentStatus.contains(status), "Expect " + status + " status found as " + currentStatus);
    }

    /**
     * Read the data from downloaded CSV file.
     * Asserts that expected filter options and CHPL ID consists in the downloaded search-results.csv file
     * @param headers expected name of each column header in the CSV file
     * @param chplId expected CHPL ID
     * @throws FileNotFoundException if the expected file not found
     */

    @Then("^the file is downloaded and contains selected filter options as \"([^\"]*)\" where CHPL ID is \"([^\"]*)\"$")
    public void readDownloadedCSVFile(final String headers, final String chplId) throws FileNotFoundException {
        String[] headerArray = headers.split(",");
        List<String> headerList = Arrays.asList(headerArray);
        List<String> csvHeaderList = new ArrayList<>();
        File[] files = Hooks.getDownloadDirectory().listFiles();
        for (File file : files) {
            Scanner scanner = new Scanner(file);
            Scanner dataScanner = null;
            boolean headersChecked = false;
            boolean foundChpId = false;
            while (!foundChpId && scanner.hasNextLine()) {
                dataScanner = new Scanner(scanner.nextLine());
                dataScanner.useDelimiter(",");
                while (dataScanner.hasNext()) {
                    String data = dataScanner.next();
                    if (headersChecked) {
                        if (data.equalsIgnoreCase(chplId)) {
                            foundChpId = true;
                            break;
                        }
                    } else {
                        csvHeaderList.add(data);
                    }
                }
                if (!headersChecked) {
                    for (String  header : headerList) {
                        assertTrue(csvHeaderList.contains(header), "The search option [ " + header + " ] is missing in the CSV file");
                        headersChecked = true;
                    }
                }
            }
            assertTrue(foundChpId, "chpl id [ " + chplId + " ] not found");
            dataScanner.close();
            scanner.close();
        }
    }

    /**
     * Asserts that expected text is correct.
     * @param text expected Please reduce results to less than 50 to download them
     */
    @Then("^I see that download for >50 count is not allowed and alert \"([^\"]*)\" is displayed$")
    public void resultText(final String text) {
        WebElement link = SearchPage.searchResultText(getDriver());
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView();", link);
        String actualText = SearchPage.searchResultText(getDriver()).getText();
        assertEquals(actualText, text);
    }

    /**
     * Assert that certification status filter dropdown shows correct number of certification statuses.
     * @param expectedStatusCount is expected certification status count in dropdown
     */
    @Then("^I see that Certification Status filter shows \"([^\"]*)\" statuses$")
    public void certificationStatusFilterOptionCount(final String expectedStatusCount) {

        WebElement allElements = getDriver().findElement(By.cssSelector("#filters > div.col-sm-8 > div.btn-group.dropdown.open > ul"));
        List<WebElement> statusElements = allElements.findElements(By.tagName("input"));
        String statusCount = String.valueOf(statusElements.size());
        assertEquals(statusCount, expectedStatusCount);
    }

    /**
     * Assert that certification status filter dropdown shows valid status options.
     * @param expectedStatus is the status expected to be found in status list
     */
    @Then("^the displayed status options should include all valid statuses: \"([^\"]*)\"$")
    public void certificationStatusFilterOptions(final String expectedStatus) {

        WebElement allElements = getDriver().findElement(By.cssSelector("#filters > div.col-sm-8 > div.btn-group.dropdown.open > ul"));
        List<WebElement> statusElements = allElements.findElements(By.tagName("li"));
        List<String> listOfStatuses = new ArrayList<>();

            for (int i = 1; i < statusElements.size(); i++) {
            String statusValue = statusElements.get(i).getText();
            listOfStatuses.add(statusValue);
            assertTrue(listOfStatuses.contains(statusValue), "Status not found" + statusValue);
        }
    }

    /**
     * Assert that given certification status filter option checkbox is checked.
     * @param certStatusFilterOption is filter option
     */
    @Then("^I see that Certification Status \"([^\"]*)\" checkbox is checked by default$")
    public void verifyCertStatusOptionChecked(final String certStatusFilterOption) {
        WebElement link = SearchPage.certStatusFilterOptions(getDriver());
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", link);
        assertTrue((SearchPage.filterOption(getDriver(), certStatusFilterOption).isSelected()));
    }

    /**
     * Assert that given certification status filter option checkbox is unchecked.
     * @param certStatusFilterOption is filter option
     */
    @Then("^I see that Certification Status \"([^\"]*)\" checkbox is unchecked$")
    public void verifyCertStatusOptionUnchecked(final String certStatusFilterOption) {
        WebElement link = SearchPage.certStatusFilterOptions(getDriver());
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", link);
        assertTrue(!(SearchPage.filterOption(getDriver(), certStatusFilterOption).isSelected()));
    }
}
