package gov.healthit.chpl.aqa.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Class ProductHistoryEyePage definition.
 */
public final class ProductHistoryEyePage {
    private ProductHistoryEyePage() {
    }

    public static WebElement productHistoryDeveloper(final WebDriver driver) {
        return driver.findElement(By.xpath("//li[contains(text(),'Developer changed from')]"));
    }

    public static WebElement productHistoryVersion(final WebDriver driver) {
        return driver.findElement(By.xpath("//li[contains(text(),'Version changed from')]"));
    }

    public static WebElement productHistoryProduct(final WebDriver driver) {
        return driver.findElement(By.xpath("//li[contains(text(),'Product changed from')]"));
    }

}

