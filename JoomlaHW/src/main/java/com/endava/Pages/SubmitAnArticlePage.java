package com.endava.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by cciocoveanu
 */
public class SubmitAnArticlePage extends MainPageLoggedIn {

    private WebDriver webDriver;

    @FindBy(id = "jform_title")
    private WebElement articleTitleField;

    @FindBy(id = "jform_articletext_ifr")
    private WebElement articleFrame;

    @FindBy(xpath = ".//form[@id='adminForm']/div/div[1]/button")
    private WebElement saveArticleButton;

    @FindBy(css = "#mceu_54>button")
    private WebElement addImageButton;

    private static By contentField = By.id("tinymce");
    private static By addImageIframe = By.xpath("//div[contains(@id,'-body')]/iframe");
    private static By imageURLField = By.cssSelector("#f_url");
    private static By allignDropdown = By.cssSelector("#f_align_chzn .chzn-single>div>b");
    private static By selectCenterAllign = By.cssSelector("li[data-option-array-index='2']");
    private static By submitImageButton = By.cssSelector(".btn.btn-success.button-save-selected");

    public SubmitAnArticlePage(WebDriver webDriver) {
        super(webDriver);
        this.webDriver = webDriver;
    }

    public void submitNewArticle(String title, String content, String imageURL){
        articleTitleField.clear();
        articleTitleField.sendKeys(title);
        webDriver.switchTo().frame(articleFrame);
        webDriver.findElement(contentField).sendKeys(content);
        webDriver.switchTo().defaultContent();

        addImageButton.click();
        WebDriverWait wait = new WebDriverWait(webDriver, 4);
        wait.until(ExpectedConditions.visibilityOfElementLocated(addImageIframe));
        webDriver.switchTo().frame(webDriver.findElement(addImageIframe));
        webDriver.findElement(imageURLField).sendKeys(imageURL);
        webDriver.findElement(allignDropdown).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(selectCenterAllign));
        webDriver.findElement(selectCenterAllign).click();
        webDriver.findElement(submitImageButton).click();
        webDriver.switchTo().defaultContent();

        saveArticleButton.click();
    }
    public void submitNewArticle(String title, String content){
        articleTitleField.clear();
        articleTitleField.sendKeys(title);
        webDriver.switchTo().frame(articleFrame);
        webDriver.findElement(contentField).sendKeys(content);
        webDriver.switchTo().defaultContent();
        saveArticleButton.click();
    }
}
