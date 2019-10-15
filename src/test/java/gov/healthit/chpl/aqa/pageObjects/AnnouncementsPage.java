package gov.healthit.chpl.aqa.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Class AnnouncementsPage definition.
 */
public final class AnnouncementsPage extends BasePage {
    private AnnouncementsPage() {}

    public static WebElement announcementsPageTitle(final WebDriver driver) {
        return driver.findElement(By.xpath("//h2[contains(text(),'Announcements')]"));
    }

    public static WebElement announcementsColumnName(final WebDriver driver) {
        return driver.findElement(By.xpath("//*[@id=\"main-content\"]/div/ui-view/chpl-announcements/div/div"));
    }

    public static WebElement addAnnouncementsButton(final WebDriver driver) {
        return driver.findElement(By.xpath("//button[contains(text(),'Add')]"));
    }

    public static WebElement announcementFieldInput(final WebDriver driver, final String filedInputId) {
        return driver.findElement(By.xpath("//input[@id='" + filedInputId + "']"));
    }

    public static WebElement errorMessage(final WebDriver driver, final String errorMessage) {
        return driver.findElement(By.xpath("//div[contains(text(),'" + errorMessage + "')]"));
    }

    public static WebElement cancelButton(final WebDriver driver) {
        return driver.findElement(By.xpath("//button[@id='announcement-cancel']"));
    }

    public static WebElement saveButton(final WebDriver driver) {
        return driver.findElement(By.xpath("//button[@id='announcement-create']"));
    }

    public static List<WebElement> allAnnouncements(final WebDriver driver) {
        return driver.findElements(By.xpath("//*[@id=\"main-content\"]/div/ui-view/chpl-announcements/div/div/table/tbody/tr"));
    }

    public static WebElement announcementReportDetails(final WebDriver driver) {
        return driver.findElement(By.xpath("//*[@id=\"main-content\"]/div/ui-view/chpl-reports-announcements/div"));
    }

    public static WebElement deleteAnnouncement(final WebDriver driver) {
        return driver.findElement(By.xpath("//button[@id='announcement-delete']"));
    }

}

