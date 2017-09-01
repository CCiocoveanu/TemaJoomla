package com.endava.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cciocoveanu
 */
public class SiteAdminPage {

    private WebDriver webDriver;

    @FindBy(id = "element-box")
    private WebElement elementBox;

    @FindBy(id = "mod-login-username")
    private WebElement usernameField;

    @FindBy(id = "mod-login-password")
    private WebElement passwordField;

    @FindBy(css = ".chzn-single>div>b")
    private WebElement languageDropdown;

    @FindBy(css = ".pull-left")
    private WebElement homePageButton;

    @FindBy(xpath = "//div[@class='controls']//button")
    private WebElement loginButton;

    private static By languageSelector = By.xpath("//div[@class='chzn-drop']/ul/li");

    public SiteAdminPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void login(String user, String password, String language){
        usernameField.clear();
        usernameField.sendKeys(user);
        passwordField.clear();
        passwordField.sendKeys(password);

        languageDropdown.click();
        List<WebElement> languageList = webDriver.findElements(languageSelector);
        for (WebElement element : languageList){
            if(element.getText().contains(language)) element.click();
        }
        loginButton.click();
    }

    public void goToHomePage(){

        homePageButton.click();

        ArrayList<String> newTab = new ArrayList<>(webDriver.getWindowHandles());
        webDriver.close();
        webDriver.switchTo().window(newTab.get(1));
    }
}
