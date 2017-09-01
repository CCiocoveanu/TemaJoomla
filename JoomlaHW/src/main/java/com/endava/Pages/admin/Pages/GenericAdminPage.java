package com.endava.Pages.admin.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

/**
 * Created by cciocoveanu
 */
public class GenericAdminPage {
    private WebDriver webDriver;

    @FindBy(xpath = "//div[@class='container-fluid']/a[@class='admin-logo ']")
    private WebElement ctrlPanelButton;

    @FindBy(xpath = "//ul[@id='menu']/li[2]/a")
    private WebElement usersButton;

    @FindBy(xpath = "//ul[@id='menu']/li[2]/ul/li[not(contains(@class, 'divider'))]")
    private List<WebElement> usersDropdownList;

    @FindBy(xpath = "//ul[contains(@class,'pull-right')]//li[@class='dropdown']/a")
    private WebElement userMenuDropdown;

    private By addUserButtonSelector = By.xpath("//ul[@id='nav-empty']/li/a");

    private By logoutButtonSelector = By.xpath("//li[contains(@class, 'open')]//ul/li[5]/a");

    public GenericAdminPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void goToAddUserPage() {
        usersButton.click();
        Actions builder = new Actions(webDriver);
        builder.moveToElement(usersDropdownList.get(0)).perform();
        WebDriverWait wait = new WebDriverWait(webDriver, 4);
        wait.until(ExpectedConditions.elementToBeClickable(addUserButtonSelector));
        webDriver.findElement(addUserButtonSelector).click();
    }

    public void logout(){
        userMenuDropdown.click();
        webDriver.findElement(logoutButtonSelector).click();
    }
}
