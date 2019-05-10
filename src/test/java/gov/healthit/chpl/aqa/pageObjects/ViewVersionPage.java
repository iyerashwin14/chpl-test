package gov.healthit.chpl.aqa.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Class ViewVersionPage definition.
 */
public final class ViewVersionPage {
    private ViewVersionPage() {
    }

    public static WebElement editVersionLink(final WebDriver driver, final String versionId) {
        return driver.findElement(By.xpath("//*[@id=\"version-component-edit-" + versionId + "\"]/i"));
    }

    public static WebElement editVersionField(final WebDriver driver) {
        return driver.findElement(By.xpath("//input[@id='version-name']"));
    }

    public static WebElement editVersionSaveButton(final WebDriver driver, final String versionId) {
        return driver.findElement(By.xpath("//*[@id=\"version-component-save-" + versionId + "\"]/i"));
    }

    public static WebElement viewVersionPageTitle(final WebDriver driver) {
        return driver.findElement(By.xpath("//span[contains(text(),'View Version')]"));
    }

    public static WebElement viewSavedVersion(final WebDriver driver, final String versionId) {
        return driver.findElement(By.xpath("//*[@id=\"chpl-version-" + versionId + "\"]"));
    }

    public static WebElement mergeVersionLink(final WebDriver driver, final String versionId) {
        return driver.findElement(By.xpath("//*[@id=\"version-component-merge-" + versionId + "\"]/i"));
    }

    public static WebElement versionsToMergeMessage(final WebDriver driver) {
        return driver.findElement(By.xpath("//*[@id=\"main-content\"]/div[4]/div[1]"));
    }

    public static WebElement versionMissingErrorMessage(final WebDriver driver, final String versionId) {
        return driver.findElement(By.xpath("//*[@id=\"chpl-version-" + versionId + "\"]/span/div/div/div"));
    }
}

