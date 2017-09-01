package com.endava.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Random;

/**
 * Created by cciocoveanu
 */
public class TemplateSettingsPage extends MainPageLoggedIn {

    private WebDriver webDriver;

    @FindBy(id = "params_templateColor")
    private WebElement templateColor;

    @FindBy(id = "params_templateBackgroundColor")
    private WebElement backgroundColor;

    @FindBy(id = "params_sitetitle")
    private WebElement titleField;

    @FindBy(id = "params_sitedescription")
    private WebElement descriptionField;

    @FindBy(xpath = "//div[@class='btn-toolbar']/div[@class='btn-group'][1]/button")
    private WebElement saveButton;

    @FindBy(xpath = "//div[@class='alert alert-success']//div[@class='alert-message']")
    private WebElement successMessage;

    public static String successMessageText = null;

    public TemplateSettingsPage(WebDriver webDriver) {
        super(webDriver);
        this.webDriver = webDriver;
    }

    public void editTemplateSettings(String templateColorInput, String backgroundColorInput, String title, String description){
        successMessageText = null;
        if(!templateColor.getAttribute("value").equals(templateColorInput)) {
            templateColor.clear();
            templateColor.sendKeys(templateColorInput);
        }
        if(!backgroundColor.getAttribute("value").equals(backgroundColorInput)) {
            backgroundColor.clear();
            backgroundColor.sendKeys(backgroundColorInput);
        }
        titleField.clear();
        titleField.sendKeys(title);
        descriptionField.clear();
        descriptionField.sendKeys(description);
        saveButton.click();

        WebDriverWait wait = new WebDriverWait(webDriver, 4);
        wait.until(ExpectedConditions.visibilityOf(successMessage));

        successMessageText = successMessage.getText();
    }

    private String getRandomColor(){
        Random random = new Random();
        int nextInt = random.nextInt(256*256*256);
        String colorCode = String.format("#%06x", nextInt);
        return colorCode;
    }

    public void setRandomColors(){
        successMessageText = null;
        templateColor.clear();
        templateColor.sendKeys(getRandomColor());
        backgroundColor.clear();
        backgroundColor.sendKeys(getRandomColor());
        saveButton.click();

        WebDriverWait wait = new WebDriverWait(webDriver, 4);
        wait.until(ExpectedConditions.visibilityOf(successMessage));

        successMessageText = successMessage.getText();
    }
}



