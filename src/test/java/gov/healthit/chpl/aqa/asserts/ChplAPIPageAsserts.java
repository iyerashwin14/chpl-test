package gov.healthit.chpl.aqa.asserts;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import cucumber.api.java.en.Then;
import gov.healthit.chpl.aqa.pageObjects.ChplAPIPage;
import gov.healthit.chpl.aqa.stepDefinitions.Base;


/**
 * Class ChplAPIPageAsserts definition.
 */
public class ChplAPIPageAsserts extends Base {
    private JSONArray apiImplNoteList;
    private final String  jsonFile = "implementationNotes.json";

    /**
     * Assert that text is updated.
     * @param imptext expected text
     */
    @Then("^endpoint details should show updated text \"([^\"]*)\"$")
    public void verifyUpdatedTextInCertifiedProductEndpointDetails(final String imptext) {
        String impNotes = ChplAPIPage.certifiedProductsImplementationNotes(getDriver()).getText();
        assertTrue(impNotes.contains(imptext), "Expect " + imptext + " to be found in " + impNotes);
    }

    /**
     * Assert that APIs from webpage is present in the example API Endpoints.
     * @param apiEndpoints as the details of controller
     */
    @Then("^list of \"(.*)\" operations should be displayed without deprecated calls$")
    public void listOfOperationsShouldBeDisplayed(final String apiEndpoints) {
        WebDriver driver = getDriver();
        List<WebElement> apiElements = ChplAPIPage.controllerElementList(driver);
        List<String> apiListFromPage = new ArrayList<>();
        List<String> apiListFromEndpoints = Arrays.asList(apiEndpoints.split("--"));
        // Checking if the API from the webpage is present in the API Endpoints
        for (WebElement apiLink : apiElements) {
            String apiName = apiLink.getText();
            apiListFromPage.add(apiName);
            assertTrue(apiListFromEndpoints.contains(apiName), "Unknown API found: " + apiName);
        }
        // Checking if the API from the API Endpoints is present in the webpage
        for (String apiData : apiListFromEndpoints) {
            assertTrue(apiListFromPage.contains(apiData), "API not found in the webpage :-" + apiData);
        }
    }

    /**
     * Assert that link is correct.
     * @param urlLink expected as Link
     * @param text expected as Content
     */
    @Then("^Verify \"(.*)\" under Certified Health IT Product Listing is followed by \"(.*)\"$")
    public void verifyContentUnderCertifiedHealthITProductListingAndURL(final String text, final String urlLink) {
        WebDriver driver = getDriver();
        String actualText = ChplAPIPage.chplContentText(driver).getText();
        String actualURL = ChplAPIPage.healthITFeedBackFormURL(driver).getAttribute("href");
        assertEquals(actualText, text);
        assertEquals(actualURL, urlLink);
    }

    /**
     * Assert that implementation notes for the endpoint link is updated.
     * @throws Exception if File not found
     */
    @Then("^API endpoints should show updated implementation notes$")
    public void apiEndpointsImplementationNotesDisplaysUpdatedText() throws Exception {
        JSONParser jsonParser = new JSONParser();
        Boolean failedCase = false;
        File file = new File(getClass().getClassLoader().getResource(jsonFile).getFile());
        FileReader reader = new FileReader(file.getPath());
        Object notesObj = jsonParser.parse(reader);
        reader.close();
        apiImplNoteList = (JSONArray) notesObj;
        for (Object object : apiImplNoteList) {
            failedCase = false;
            JSONObject noteObject = (JSONObject) object;
            JSONArray apiimplnotesArray  = (JSONArray) noteObject.get("apiimplnote");
            for (Object endPointLink:apiimplnotesArray) {
                JSONArray endPointLinkArray = (JSONArray) ((JSONObject) endPointLink).get("endPointLink");
                String controller = (String) ((JSONObject) endPointLink).get("controllerName");
                WebElement cLink = ChplAPIPage.controllerLink(getDriver(), (String) ((JSONObject) endPointLink).get("controllerName"));
                ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", cLink);
                for (Object links:endPointLinkArray) {
                    String endPoint = (String) (((JSONObject) links).get("link"));
                    WebElement epLink = ChplAPIPage.endpointLink(getDriver(), endPoint);
                    ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", epLink);
                    String implNote = (String) ((JSONObject) links).get("implementationNote");
                    String actImpNotes = ChplAPIPage.certifiedProductsImplementationNotes(getDriver()).getText();
                    try {
                        assertTrue(actImpNotes.contains(implNote), "Expected [ " + implNote + " ]not found for:[" + controller + "] where endpoint is:[" + endPoint + "]");
                    } catch (AssertionError ex) {
                        System.out.println(ex.getMessage());
                        failedCase = true;
                    }
                }
            }
            if (failedCase) {
                throw new AssertionError("Scenario failed cases:-");
            }
        }
    }
}

