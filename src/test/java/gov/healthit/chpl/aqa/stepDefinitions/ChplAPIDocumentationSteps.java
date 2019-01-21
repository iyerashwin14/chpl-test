package gov.healthit.chpl.aqa.stepDefinitions;

import static org.testng.Assert.assertTrue;

import java.io.FileReader;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import gov.healthit.chpl.aqa.pageObjects.ChplAPIPage;

/**
 * Class ChplAPIDocumentationSteps definition.
 */
public class ChplAPIDocumentationSteps extends Base {

    private JSONArray apiImplNoteList;
    /**
     * Constructor creates new driver.
     */
    public ChplAPIDocumentationSteps() {
        super();
    }

    /**
     * Get user to the API Documentation page.
     */
    @Given("^I am on CHPL API page$")
    public void userLoadsAPIPage() {
        getDriver().get(getUrl() + "#/resources/chpl_api");
        WebDriverWait wait = new WebDriverWait(getDriver(), TIMEOUT);
        wait.until(ExpectedConditions.visibilityOf(ChplAPIPage.mainContent(getDriver())));
    }

    /**
     * Assert that implementation notes for the endpoints link is updated.
     * @throws Exception if File not found
     */
    @Then("^API endpoints should show updated implementation notes$")
    public void apiEndpointsImplementationNotesDisplaysUpdatedText() throws Exception {
        JSONParser jsonParser = new JSONParser();
        Boolean failedCase = false;
        Path resourceDirectory = Paths.get("src", "test", "resources", "implementationNotes.json");
        FileReader reader = new FileReader(resourceDirectory.toString());
        Object notesObj = jsonParser.parse(reader);
        apiImplNoteList = (JSONArray) notesObj;
        for (Object object : apiImplNoteList) {
            failedCase = false;
            JSONObject noteObject = (JSONObject) object;
            JSONArray apiimplnotesArray  = (JSONArray) noteObject.get("apiimplnote");
            for (Object endPointLink:apiimplnotesArray) {
                JSONArray endPointLinkArrays = (JSONArray) ((JSONObject) endPointLink).get("endPointLink");
                String controller = (String) ((JSONObject) endPointLink).get("controllerName");
                WebElement cLink = ChplAPIPage.controllerLink(getDriver(), (String) ((JSONObject) endPointLink).get("controllerName"));
                ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", cLink);
                for (Object links:endPointLinkArrays) {
                    String endPointLink1 = (String) (((JSONObject) links).get("link"));
                    WebElement epLink = ChplAPIPage.endpointLink(getDriver(), endPointLink1);
                    ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", epLink);
                    String implNote = (String) ((JSONObject) links).get("implementationNote");
                    String actImpNotes = ChplAPIPage.certifiedProductsImplementationNotes(getDriver()).getText();
                    try {
                        assertTrue(actImpNotes.contains(implNote), "Expected [ " + implNote + " ]not found for:[" + controller + "] where endpoint is:[" + endPointLink + "]");
                    } catch (AssertionError ex) {
                        System.out.println(ex.getMessage());
                        failedCase = true;
                    }
                }
            }
            if (failedCase) {
                throw new AssertionError("Scenario failed cases");
            }
        }
    }
}
