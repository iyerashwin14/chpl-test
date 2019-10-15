package gov.healthit.chpl.aqa.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Class OncAcbManagementPage definition.
 */
public final class OncAcbManagementPage extends BasePage {
    private OncAcbManagementPage() {}

    public static WebElement oncACBManagementPageTitle(final WebDriver driver) {
        return driver.findElement(By.xpath("//h2[contains(text(),'ONC-ACB Management')]"));
    }

    public static WebElement oncACBName(final WebDriver driver, final String oncAcbName) {
        List<WebElement> navItems = driver.findElements(By.xpath("//*[@id='main-content']/div/div/ui-view/chpl-onc-organizations/div[2]/div/div"));
        for (WebElement item : navItems) {
            if (item.findElement(By.xpath(".//button")).getText().equalsIgnoreCase(oncAcbName)) {
                return item.findElement(By.xpath(".//button"));
            }
        }
        return null;
    }

    public static WebElement editONCACB(final WebDriver driver) {
        return driver.findElement(By.id("edit-organization"));
    }

    public static WebElement markRetirementStatus(final WebDriver driver) {
        return driver.findElement(By.id("organization-retired"));
    }

    public static WebElement retirementDate(final WebDriver driver) {
        return driver.findElement(By.id("retirement-date"));
    }

    public static WebElement saveONCACB(final WebDriver driver) {
        return driver.findElement(By.id("chpl-organization-save"));
    }

    public static WebElement retirementStatus(final WebDriver driver, final String retirementStatus) {
        WebElement status = driver.findElement(By.xpath("//p[contains(text(),'Retired: ')]"));
        return status.findElement(By.xpath(".//strong"));
    }

    public static WebElement retirementDateIs(final WebDriver driver, final String retirementDate) {
        return driver.findElement(By.xpath("//strong[contains(text(),'Yes, effective " + retirementDate + "')]"));
    }

    public static WebElement errorMessage(final WebDriver driver) {
        return driver.findElement(By.xpath("/html/body/div[1]/div/div/div/div[3]/p"));
    }

    public static WebElement addressFirstLine(final WebDriver driver) {
        return driver.findElement(By.id("line1"));
    }

    public static WebElement addressCity(final WebDriver driver) {
        return driver.findElement(By.id("city"));
    }

    public static WebElement addressState(final WebDriver driver) {
        return driver.findElement(By.id("state"));
    }

    public static WebElement addressZipCode(final WebDriver driver) {
        return driver.findElement(By.id("zipcode"));
    }

    public static WebElement addressCountry(final WebDriver driver) {
        return driver.findElement(By.id("country"));
    }

    public static WebElement fieldWebsite(final WebDriver driver) {
        return driver.findElement(By.id("organization-website"));
    }

    public static WebElement oncACBNameOnEditForm(final WebDriver driver) {
        return driver.findElement(By.id("organization-name"));
    }

    public static WebElement organizationsToggleNavLink(final WebDriver driver) {
        return driver.findElement(By.id("organizations-toggle"));
    }

    public static WebElement organizationsDropdownLinkToLoadACBManagementPage(final WebDriver driver) {
        return driver.findElement(By.xpath("//*[@id=\"organizations-dropdown-menu\"]/li[4]/a"));
    }
}
