package com.endava.Tests;

import com.endava.Pages.*;
import com.endava.Pages.admin.Pages.AddUserPage;
import com.endava.Pages.admin.Pages.GenericAdminPage;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

import javax.swing.*;
import java.util.ArrayList;

/**
 * Created by cciocoveanu
 */
public class MainClassTest extends MainTest{

    @Test
    public void siteSettingsTest() throws InterruptedException {
        // ---  Main page ---
        mainPage.logIn("cciocoveanu","hidden");

        // --- Main page while logged in ---
        mainPageLoggedIn = PageFactory.initElements(webDriver, MainPageLoggedIn.class);
        mainPageLoggedIn.goToPage("Site Settings");

        // --- Site Settings page
        siteSettingsPage = PageFactory.initElements(webDriver, SiteSettingsPage.class);
        Assert.assertEquals("cciocoveanu's Site!", siteSettingsPage.getSiteName());
        siteSettingsPage.editSiteSettings("cciocoveanu's Site!",
                                          100,
                                          "Super Users",
                                          "Joomla - Description: This site is awesome",
                                          "joomla, Joomla",
                                          "Much rights, such wow");
        siteSettingsPage.editSiteSettings(randomString(20));
        siteSettingsPage.editSiteSettings("cciocoveanu's Site!");
        siteSettingsPage.goToHomePage();
        mainPageLoggedIn.logOut();
    }

    @Test
    public void submitArticleTest(){
        // ---  Main page ---
        mainPage.logIn("cciocoveanu","hidden");

        // --- Main page while logged in ---
        mainPageLoggedIn = PageFactory.initElements(webDriver, MainPageLoggedIn.class);
        mainPageLoggedIn.goToPage("Submit an Article");

        // --- Submit an Article page ---
        submitAnArticlePage = PageFactory.initElements(webDriver, SubmitAnArticlePage.class);
        submitAnArticlePage.submitNewArticle(
                randomString(10),
                randomString(100) + System.lineSeparator() +
                        randomString(100) + System.lineSeparator() +
                        "GOOD DAY.",
                "https://media.licdn.com/mpr/mpr/AAEAAQAAAAAAAA2hAAAAJGM5N2E3OTE5LTkzODEtNGFiZS1hMzI1LTVjNWRjODg3M2Y3MQ.jpg");

        submitAnArticlePage.goToHomePage();
        mainPageLoggedIn.logOut();
    }

    @Test
    public void yourProfileTest(){
        // ---  Main page ---
        Assert.assertEquals("cciocoveanu's Site!", mainPage.getSiteTitle());
        mainPage.logIn("cciocoveanu","hidden");

        // --- Main page while logged in ---
        mainPageLoggedIn = PageFactory.initElements(webDriver, MainPageLoggedIn.class);
        Assert.assertEquals("Hi Constantin Ciocoveanu,", mainPageLoggedIn.getLoginGreeting());
        mainPageLoggedIn.goToPage("Your Profile");

        // --- Your Profile ---
        yourProfilePage = PageFactory.initElements(webDriver, YourProfilePage.class);
        yourProfilePage.changeYourSettings("Constantin Ciocoveanu", "ciocoveanu.constantin@gmail.com", "Bucharest");

        yourProfilePage.goToHomePage();
        mainPageLoggedIn.logOut();
    }

    @Test
    public void templateSettingsTest(){
        // ---  Main page ---
        mainPage.logIn("cciocoveanu","hidden");

        // --- Main page while logged in ---
        mainPageLoggedIn = PageFactory.initElements(webDriver, MainPageLoggedIn.class);
        mainPageLoggedIn.goToPage("Template Settings");

        // --- Template Settings ---
        templateSettingsPage = PageFactory.initElements(webDriver, TemplateSettingsPage.class);
        templateSettingsPage.editTemplateSettings("#008000", "#93999c", "Best Site EU",randomString(50));
        templateSettingsPage.setRandomColors();

        templateSettingsPage.goToHomePage();
        mainPageLoggedIn.logOut();
    }

    @Test
    public void createNewUserTest() throws InterruptedException {
        // ---  Main page ---
        mainPage.logIn("cciocoveanu","hidden");

        // --- Main page while logged in ---
        mainPageLoggedIn = PageFactory.initElements(webDriver, MainPageLoggedIn.class);
        mainPageLoggedIn.goToPage("Site Administrator");

        // --- Site Administrator ---
        siteAdminPage = PageFactory.initElements(webDriver,SiteAdminPage.class);
        siteAdminPage.login("cciocoveanu","hidden","English");

        genericAdminPage = PageFactory.initElements(webDriver, GenericAdminPage.class);
        genericAdminPage.goToAddUserPage();


        // --- Add User ---
        addUserPage = PageFactory.initElements(webDriver, AddUserPage.class);
        addUserPage.addNewUser(randomString(20),"cciocovaenu"+randomString(4), "hidden", "ciocoveanu.constantin"+randomString(4)+"@gmail.com");
        addUserPage.logout();

        // --- Back to Site Administrator ---
        siteAdminPage.goToHomePage();

        // --- Back to main page ---
        mainPageLoggedIn.logOut();
    }
}

