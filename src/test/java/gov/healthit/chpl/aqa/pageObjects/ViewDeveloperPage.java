package gov.healthit.chpl.aqa.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Class ViewDeveloperPage definition.
 */
public final class ViewDeveloperPage {
    private ViewDeveloperPage() {
    }

    public static WebElement viewDeveloperPageTitle(final WebDriver driver) {
        return driver.findElement(By.xpath("//span[contains(text(),'View Developer')]"));
    }

    public static WebElement editDeveloperLink(final WebDriver driver, final String developerId) {
        return driver.findElement(By.xpath("//*[@id=\"developer-component-edit-" + developerId + "\"]/i"));
    }

    public static WebElement splitDeveloperLink(final WebDriver driver, final String developerId) {
        return driver.findElement(By.xpath("//*[@id=\"developer-component-split-" + developerId + "\"]/i"));
    }

    public static WebElement mergeDeveloperLink(final WebDriver driver, final String developerId) {
        return driver.findElement(By.xpath("//*[@id=\"developer-component-merge-" + developerId + "\"]/i"));
    }

    public static WebElement developerNameField(final WebDriver driver) {
        return driver.findElement(By.xpath("//input[@id='developer-name']"));
    }

    public static WebElement emailField(final WebDriver driver) {
        return driver.findElement(By.xpath("//input[@id='contactEmail']"));
    }

    public static WebElement developerToMergeMessage(final WebDriver driver) {
        return driver.findElement(By.xpath("//*[@id=\"main-content\"]/div[2]/div[1]"));
    }

    public static WebElement productsMovingToNewDeveloperMessage(final WebDriver driver) {
        return driver.findElement(By.xpath("//*[@id=\"main-content\"]/div[2]/div"));
    }

    public static WebElement developerNameMissingErrorMessage(final WebDriver driver) {
        return driver.findElement(By.xpath("//*[@id=\"chpl-developer\"]/span/div[1]/div/div"));
    }

    public static WebElement emailMissingErrorMessage(final WebDriver driver) {
        return driver.findElement(By.xpath("//*[@id=\"chpl-developer\"]/span/chpl-contact/span/span/div[4]/div/div"));
    }

    public static WebElement editDeveloperSaveButton(final WebDriver driver, final String developerId) {
        return driver.findElement(By.xpath("//*[@id=\"developer-component-save-" + developerId + "\"]"));
    }

    public static WebElement viewDeveloperName(final WebDriver driver) {
        return driver.findElement(By.xpath("//*[@id=\"chpl-developer\"]/span/span[2]"));
    }

}

