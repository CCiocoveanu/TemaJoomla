package com.endava.Pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by cciocoveanu
 */
public class YourProfilePage extends MainPageLoggedIn{

    private WebDriver webDriver;

    @FindBy(id = "jform_name")
    private WebElement nameField;

    @FindBy(id = "jform_email1")
    private WebElement emailField;

    @FindBy(id = "jform_email2")
    private WebElement confirmEmailField;

    @FindBy(xpath = "//div[@class='controls']/button[contains(@class,'btn-primary')]")
    private WebElement submitButton;

    @FindBy(css = "#jform_params_timezone_chzn .chzn-single>div>b")
    private WebElement timeZoneDropdownBox;

    @FindBy(css = "#jform_params_timezone_chzn .chzn-search>input")
    private WebElement timeZoneInputField;


    public YourProfilePage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void changeYourSettings(String name, String email, String timeZone){
        nameField.clear();
        nameField.sendKeys(name);
        emailField.clear();
        emailField.sendKeys(email);
        confirmEmailField.clear();
        confirmEmailField.sendKeys(email);
        timeZoneDropdownBox.click();
        timeZoneInputField.sendKeys(timeZone);
        timeZoneInputField.sendKeys(Keys.ENTER);
        submitButton.click();

        WebElement successMessage = webDriver.findElement(By.cssSelector(".alert.alert-success .alert-message"));
        WebDriverWait wait = new WebDriverWait(webDriver, 4);
        wait.until(ExpectedConditions.visibilityOf(successMessage));

        Assert.assertEquals("Profile saved.",successMessage.getText());
    }
}
