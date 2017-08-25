package com.endava.Pages.admin.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by cciocoveanu
 */
public class AddUserPage extends GenericAdminPage{
    private WebDriver webDriver;
    @FindBy(id = "jform_name")
    private WebElement nameField;

    @FindBy(id = "jform_username")
    private WebElement usernameField;

    @FindBy(id = "jform_password")
    private WebElement passwordField;

    @FindBy(id = "jform_password2")
    private WebElement passwordField2;

    @FindBy(id = "jform_email")
    private WebElement emailField;

    @FindBy(xpath = "//div[@id='toolbar-save']/button")
    private WebElement saveAndCloseButton;

    public AddUserPage(WebDriver webDriver) {
        super(webDriver);
        this.webDriver = webDriver;
    }

    public void addNewUser(String realName, String uName, String uPassword, String uEmail){
       nameField.sendKeys(realName);
       usernameField.clear();
       usernameField.sendKeys(uName);
       passwordField.sendKeys(uPassword);
       passwordField2.sendKeys(uPassword);
       emailField.sendKeys(uEmail);
       saveAndCloseButton.click();
    }

}
