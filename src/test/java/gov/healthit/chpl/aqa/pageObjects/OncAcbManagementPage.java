package gov.healthit.chpl.aqa.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Class OncAcbManagementPage definition.
 */
public final class OncAcbManagementPage extends BasePage {
    private OncAcbManagementPage() {}

    public static WebElement oncACBManagementLink(final WebDriver driver) {
        return driver.findElement(By.xpath("//a[contains(text(),'ONC-ACB Management')]"));
    }

    public static WebElement oncACBName(final WebDriver driver, final String oncAcbName) {
        return driver.findElement(By.xpath("//a[contains(text(),'" + oncAcbName + "')]"));
    }

    public static WebElement editONCACB(final WebDriver driver) {
        return driver.findElement(By.xpath("//*[@id=\"main-content\"]/ai-acb-management/section/div/div[2]/div/button"));
    }

    public static WebElement markRetirementStatus(final WebDriver driver) {
        return driver.findElement(By.xpath("//*[@id=\"acb-retired\"]"));
    }

    public static WebElement retirementDate(final WebDriver driver) {
        return driver.findElement(By.xpath("///*[@id=\"retirement-date\"]"));
    }

    public static WebElement saveONCACB(final WebDriver driver) {
        return driver.findElement(By.xpath("/html/body/div[1]/div/div/div/div[3]/button[1]"));
    }

    public static WebElement retirementStatus(final WebDriver driver, final String retirementStatus) {
        return driver.findElement(By.xpath("//span[contains(text(),'Retired: " + retirementStatus + "')]"));
    }

    public static WebElement retirementDateIs(final WebDriver driver, final String reitrementDate) {
        return driver.findElement(By.xpath("//span[contains(text(),'Retirement Date:" + reitrementDate + "')]"));
    }

    public static WebElement errorMessage(final WebDriver driver) {
        return driver.findElement(By.xpath("/html/body/div[1]/div/div/div/div[3]/p"));
    }

    public static WebElement addressFirstLine(final WebDriver driver) {
        return driver.findElement(By.xpath("//input[@id='address-line-1']"));
    }

    public static WebElement addressCity(final WebDriver driver) {
        return driver.findElement(By.xpath("//input[@id='address-city']"));
    }

    public static WebElement addressState(final WebDriver driver) {
        return driver.findElement(By.xpath("//input[@id='address-state']"));
    }

    public static WebElement addressZipCode(final WebDriver driver) {
        return driver.findElement(By.xpath("//input[@id='address-zipcode']"));
    }

    public static WebElement addressCountry(final WebDriver driver) {
        return driver.findElement(By.xpath("//input[@id='address-country']"));
    }

    public static WebElement fieldWebsite(final WebDriver driver) {
        return driver.findElement(By.xpath("//input[@id='acb-website']"));
    }

    public static WebElement oncACBNameOnEditForm(final WebDriver driver) {
        return driver.findElement(By.id("acb-name"));
    }

    public static WebElement organizationsToggleNavLink(final WebDriver driver) {
        return driver.findElement(By.xpath("//*[@id=\"organizations-toggle\"]"));
    }

    public static WebElement organizationsDropdownLinkToLoadACBManagementPage(final WebDriver driver) {
        return driver.findElement(By.xpath("//*[@id=\"organizations-dropdown-menu\"]/li[4]/a"));
    }
}

