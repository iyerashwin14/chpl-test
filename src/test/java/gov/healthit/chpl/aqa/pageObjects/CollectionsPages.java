package gov.healthit.chpl.aqa.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Class CollectionsPages definition.
 */
public final class CollectionsPages {
    private CollectionsPages() {}
    private static WebElement element = null;

    /**
     * Returns ACB Filter button element.
     * @param driver WebDriver
     * @return element holding main content
     */
    public static WebElement acbFilterButton(final WebDriver driver) {
        element = driver.findElement(By.id("filter-button"));
        return element;
    }

    public static WebElement topInfoTextOnCollectionsPages(final WebDriver driver) {
        return driver.findElement(By.xpath("//*[@id=\"main-content\"]/div[1]/div/ng-transclude/ai-body-text/p[2]"));
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

    /**
     * Returns element holding CMS FAQ link.
     * @param driver WebDriver
     * @return link element
     */
    public static WebElement cmsFaqLink(final WebDriver driver) {
        element = driver.findElement(By.linkText("CMS FAQ"));
        return element;
    }

    public static WebElement sedDetailsFileButton(final WebDriver driver) {
        return driver.findElement(By.xpath("//a[contains(text(),'Download All SED Details')]"));
    }
}
