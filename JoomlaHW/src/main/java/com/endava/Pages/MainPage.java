package com.endava.Pages;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Random;

/**
 * Created by cciocoveanu
 */
public class MainPage {

    private WebDriver webDriver;
    Random r = new Random();

    @FindBy(id = "modlgn-username")
    private WebElement usernameField;

    @FindBy(id = "modlgn-passwd")
    private WebElement passwordField;

    @FindBy(css = ".btn.btn-primary.login-button")
    private WebElement logInButton;

    @FindBy(css = ".site-title")
    private WebElement siteTitle;

    @FindBy(xpath = ".//li[contains(@class ,'item-101 default')]/a")
    private WebElement homePageButton;

    public MainPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public MainPage() {
    }

    public void logIn(String user, String password){
        usernameField.clear();
        usernameField.sendKeys(user);
        passwordField.clear();
        passwordField.sendKeys(password);
        logInButton.click();
    }

    public String getSiteTitle(){
        return siteTitle.getAttribute("title");
    }

    public void goToHomePage(){
        homePageButton.click();
    }

    public String randomString(int length){
        return RandomStringUtils.randomAlphabetic(length);
    }
}
