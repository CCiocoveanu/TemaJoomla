package com.endava.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

/**
 * Created by cciocoveanu
 */
public class MainPageLoggedIn extends MainPage{

    private WebDriver webDriver;

    @FindBy(css = ".login-greeting")
    private WebElement loginGreeting;

    @FindBy(css = "input[value='Log out']")
    private WebElement logOutButton;

//    @FindBy(xpath = ".//div[contains(@class,'well _menu jmoddiv jmodinside')]/ul/li")
//    private List<WebElement> menuPagesList;



    public MainPageLoggedIn(WebDriver webDriver) {
        this.webDriver = webDriver;

    }

    public MainPageLoggedIn() {
    }

    public String getLoginGreeting(){
        return loginGreeting.getText().trim();
    }

    public void logOut(){
        WebDriverWait wait = new WebDriverWait(webDriver, 5);
        wait.until(ExpectedConditions.visibilityOf(logOutButton));
        logOutButton.click();
    }

    public void goToPage(String pageName){
//        WebDriverWait wait = new WebDriverWait(webDriver, 4);
//        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(".//div[contains(@class,'well _menu jmoddiv jmodinside')]/ul/li")));
        int number;
        switch (pageName){
            case "Your Profile":
                number = 2;
                break;
            case "Submit an Article":
                number = 4;
                break;
            case "Site Administrator":
                number = 3;
                break;
            case "Template Settings":
                number = 6;
                break;
            case "Site Settings":
                number = 7;
                break;
            default:
                throw new IllegalArgumentException("Invalid page name: " + pageName);
        }

        webDriver.findElement(By.cssSelector("li[class='item-10"+number+"']")).click();
    }

}
