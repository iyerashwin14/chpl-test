package gov.healthit.chpl.aqa.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Class UserManagementPage definition.
 */
public final class UserManagementPage extends BasePage {
    private UserManagementPage() {
    }

    public static WebElement impersonateRoleDeveloper(final WebDriver driver) {
        return driver.findElement(By.xpath("//button[@id='user-component-impersonate-75']"));
    }

    public static WebElement impersonateRoleAcb(final WebDriver driver) {
        return driver.findElement(By.xpath("//button[@id='user-component-impersonate-73']"));
    }

    public static WebElement impersonateRole(final WebDriver driver, final String impersonateRoleName) {
        return driver.findElement(By.xpath("//span[contains(text(),'" + impersonateRoleName + "')]"));
    }

    public static WebElement impersonateRoleCMS(final WebDriver driver) {
        return driver.findElement(By.xpath("//button[@id='user-component-impersonate-125']"));
    }

    public static WebElement impersonateRoleAtl(final WebDriver driver) {
        return driver.findElement(By.xpath("//button[@id='user-component-impersonate-41']"));
    }

    public static WebElement impersonateRoleUsername(final WebDriver driver) {
        return driver.findElement(By.xpath("//button[@id='login-toggle']"));
    }
}

