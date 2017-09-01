package com.endava.Tests;

import com.endava.Pages.*;
import com.endava.Pages.admin.Pages.AddUserPage;
import com.endava.Pages.admin.Pages.GenericAdminPage;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by cciocoveanu
 */
public class MainTest {

    static WebDriver webDriver;
    static MainPage mainPage;
    static MainPageLoggedIn mainPageLoggedIn;
    static YourProfilePage yourProfilePage;
    static SubmitAnArticlePage submitAnArticlePage;
    static SiteSettingsPage siteSettingsPage;
    static TemplateSettingsPage templateSettingsPage;
    static SiteAdminPage siteAdminPage;
    static GenericAdminPage genericAdminPage;
    static AddUserPage addUserPage;

    @BeforeClass
    public static void before(){
        System.setProperty("webdriver.chrome.driver","C:/Users/cciocoveanu/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        webDriver = new ChromeDriver(options);
        webDriver.get("localhost:81/joomla/");

        mainPage = PageFactory.initElements(webDriver, MainPage.class);
    }

    @AfterClass
    public static void after(){
        webDriver.close();
    }

    public String randomString(int length){
        return RandomStringUtils.randomAlphabetic(length);
    }
    String readFromProperties(String key, String fileName){
        try{
            File file = new File(fileName);
            FileInputStream fileInput = new FileInputStream(file);
            Properties properties = new Properties();
            properties.load(fileInput);
            fileInput.close();

            String value = properties.getProperty(key);
            return value;
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
