package gov.healthit.chpl.aqa.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;


/**
 * Class ChplDownloadPage definition.
 */
public final class ChplDownloadPage {
    private ChplDownloadPage() {}
    private static WebElement element = null;

    /**
     * Download files definition list(drop down) element.
     * @param driver WebDriver
     * @return definition list element
     */
    public static WebElement definitionSelectList(final WebDriver driver) {
        element = driver.findElement(By.xpath("//*[@id=\"definitionSelect\"]"));
        return element;
    }

    /**
     * Download files list(drop down) element.
     * @param driver WebDriver
     * @return download list element
     */
    public static WebElement downloadSelectList(final WebDriver driver) {
        element = driver.findElement(By.xpath("//*[@id='downloadSelect']"));
        return element;
    }

    /**
     * Download files button element.
     * @param driver WebDriver
     * @return download files button element
     */
    public static WebElement downloadFileButton(final WebDriver driver) {
        element = driver.findElement(By.xpath("//*[@id=\"downloadChplLink\"]"));
        return element;
    }

    /**
     * Download definition files button element.
     * @param driver WebDriver
     * @return download definition files button element
     */
    public static WebElement downloadDefinitionButton(final WebDriver driver) {
        element = driver.findElement(By.xpath("//*[@id=\"downloadChplDefinitionLink\"]"));
        return element;
    }

    /**
     * Download file - 2015 edition Products file element.
     * @param driver WebDriver
     * @return 2015 edition Products file element
     */
    public static WebElement downloadoption2015editionProductsFile(final WebDriver driver) {
        element = driver.findElement(By.xpath("//*[@id=\"downloadSelect\"]/option[1]"));
        return element;
    }

    /**
     * Download file - 2014 edition Products file element.
     * @param driver WebDriver
     * @return 2014 edition Products file element
     */
    public static WebElement downloadoption2014editionProductsFile(final WebDriver driver) {
        element = driver.findElement(By.xpath("//*[@id=\"downloadSelect\"]/option[2]"));
        return element;
    }

    /**
     * Download file - 2011 edition Products file element.
     * @param driver WebDriver
     * @return 2011 edition Products file element
     */
    public static WebElement downloadoption2011editionProductsFile(final WebDriver driver) {
        element = driver.findElement(By.xpath("//*[@id=\"downloadSelect\"]/option[3]"));
        return element;
    }

    /**
     * Download file - 2015 summary file element.
     * @param driver WebDriver
     * @return 2015 summary file element
     */
    public static WebElement downloadoption2015summaryFile(final WebDriver driver) {
        element = driver.findElement(By.xpath("//*[@id=\"downloadSelect\"]/option[4]"));
        return element;
    }

    /**
     * Download file - 2014 summary file element.
     * @param driver WebDriver
     * @return 2015 edition summary file element
     */
    public static WebElement downloadoption2014summaryFile(final WebDriver driver) {
        element = driver.findElement(By.xpath("//*[@id=\"downloadSelect\"]/option[5]"));
        return element;
    }

    /**
     * Returns element holding main content.
     * @param driver WebDriver
     * @return element holding main content
     */
    public static WebElement mainContent(final WebDriver driver) {
        element = driver.findElement(By.id("main-content"));
        return element;
    }

    public static WebElement selectFilefromDropdown(final WebDriver driver, final String fileName) {
        Select selectDropdown = new Select(driver.findElement(By.id("downloadSelect")));
        selectDropdown.selectByVisibleText(fileName);
        return driver.findElement(By.id("downloadSelect"));
    }

    public static WebElement selectFilefromDefinitionDropdown(final WebDriver driver, final String fileName) {
        Select selectDropdown = new Select(driver.findElement(By.id("definitionSelect")));
        selectDropdown.selectByVisibleText(fileName);
        return driver.findElement(By.id("definitionSelect"));
    }
}
