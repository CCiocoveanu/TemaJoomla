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

    public SubmitAnArticlePage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void submitNewArticle(String title, String content, String imageURL){
        articleTitleField.clear();
        articleTitleField.sendKeys(title);
        webDriver.switchTo().frame(articleFrame);
        webDriver.findElement(By.id("tinymce")).sendKeys(content);
        webDriver.switchTo().defaultContent();

        addImageButton.click();
        WebDriverWait wait = new WebDriverWait(webDriver, 4);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//div[contains(@id,'-body')]/iframe")));
        webDriver.switchTo().frame(webDriver.findElement(By.xpath(".//div[contains(@id,'-body')]/iframe")));
        webDriver.findElement(By.cssSelector("#f_url")).sendKeys(imageURL);
        webDriver.findElement(By.cssSelector("#f_align_chzn .chzn-single>div>b")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("li[data-option-array-index='2']")));
        webDriver.findElement(By.cssSelector("li[data-option-array-index='2']")).click();
        webDriver.findElement(By.cssSelector(".btn.btn-success.button-save-selected")).click();
        webDriver.switchTo().defaultContent();

        saveArticleButton.click();
    }
    public void submitNewArticle(String title, String content){
        articleTitleField.clear();
        articleTitleField.sendKeys(title);
        webDriver.switchTo().frame(articleFrame);
        webDriver.findElement(By.id("tinymce")).sendKeys(content);
        webDriver.switchTo().defaultContent();
        saveArticleButton.click();
    }
}
