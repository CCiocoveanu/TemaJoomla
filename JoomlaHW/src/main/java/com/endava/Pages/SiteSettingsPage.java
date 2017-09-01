package com.endava.Pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;


/**
 * Created by cciocoveanu
 */
public class SiteSettingsPage extends MainPageLoggedIn {

    private WebDriver webDriver;

    @FindBy(css = "#jform_sitename")
    private WebElement siteNameField;

    @FindBy(xpath = "//div[@id='jform_list_limit_chzn']/a")
    private WebElement defaultListLimitDropdown;

    @FindBy(xpath = "//div[@class='btn-group'][1]//button")
    private WebElement saveSettingsButton;

    @FindBy(xpath = "//div[@id='jform_access_chzn']//div/b")
    private WebElement defaultAccessLevelDropdown;

    @FindBy(xpath = "//div[@id='page-site']//div[@class='controls']/textarea")
    private List<WebElement> metadataSettingsList;

    @FindBy(xpath = "//div[@class='alert alert-success']//div[@class='alert-message']")
    private WebElement successMessage;

    private static By accessLevelSelector = By.xpath("//div[@id='jform_access_chzn']//ul/li");

    private static By defaultListLimitSelector = By.xpath("//div[@id='jform_list_limit_chzn']//ul[@class='chzn-results']/li");

    public SiteSettingsPage(WebDriver webDriver) {
        super(webDriver);
        this.webDriver = webDriver;
    }

    public String getSiteName(){
        return siteNameField.getAttribute("value");
    }

    public void editSiteSettings(String newName, Integer listLimit, String accessLevel, String metaDescription, String metaKeywords, String contentRights) throws InterruptedException {
        siteNameField.clear();
        siteNameField.sendKeys(newName);
        siteNameField.sendKeys(Keys.ENTER);
        defaultAccessLevelDropdown.click();
        List<WebElement> accessLevelList = webDriver.findElements(accessLevelSelector);
        switch (accessLevel){
            case "Public":
                accessLevelList.get(0).click();
                break;
            case "Guest":
                accessLevelList.get(1).click();
                break;
            case "Registered":
                accessLevelList.get(2).click();
                break;
            case "Special":
                accessLevelList.get(3).click();
                break;
            case "Super Users":
                accessLevelList.get(4).click();
                break;
            default:
                throw new IllegalArgumentException("Invalid access level: " + accessLevel);
        }

        defaultListLimitDropdown.click();
        List<WebElement> defaultListLimitList = webDriver.findElements(defaultListLimitSelector);
        for(WebElement element : defaultListLimitList){
            if(element.getText().equals(listLimit.toString())) {
                element.click();
                break;
            }
        }
        metadataSettingsList.get(0).clear();
        metadataSettingsList.get(0).sendKeys(metaDescription);
        metadataSettingsList.get(1).clear();
        metadataSettingsList.get(1).sendKeys(metaKeywords);
        metadataSettingsList.get(2).clear();
        metadataSettingsList.get(2).sendKeys(contentRights);

        saveSettingsButton.click();

        WebDriverWait wait = new WebDriverWait(webDriver, 4);
        wait.until(ExpectedConditions.visibilityOf(successMessage));
        Assert.assertEquals("Configuration saved.",successMessage.getText());
    }

    public void editSiteSettings(String newName) throws InterruptedException {
        siteNameField.clear();
        siteNameField.sendKeys(newName);
        siteNameField.sendKeys(Keys.ENTER);
        defaultListLimitDropdown.click();

        List<WebElement> defaultListLimitList = webDriver.findElements(defaultListLimitSelector);
        defaultListLimitList.get(6).click();

        metadataSettingsList.get(0).clear();
        metadataSettingsList.get(0).sendKeys(randomString(20));
        metadataSettingsList.get(1).clear();
        metadataSettingsList.get(1).sendKeys(randomString(100));
        metadataSettingsList.get(2).clear();
        metadataSettingsList.get(2).sendKeys(randomString(80));

        saveSettingsButton.click();

        WebDriverWait wait = new WebDriverWait(webDriver, 4);
        wait.until(ExpectedConditions.visibilityOf(successMessage));
        Assert.assertEquals("Configuration saved.",successMessage.getText());
    }
}
