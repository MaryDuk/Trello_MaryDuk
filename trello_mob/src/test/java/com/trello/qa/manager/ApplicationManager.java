package com.trello.qa.manager;

import com.google.common.io.Files;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {
    AppiumDriver driver;
    TeamHelper teamHelper;
    BoardHelper boardHelper;
    SessionHelper sessionHelper;
    UserHelper userHelper;


    public void init() throws InterruptedException, MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability( "deviceName", "qa21");
        capabilities.setCapability("platformVersion", "9.0");
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("appPackage", "com.trello");
        capabilities.setCapability("appActivity", ".home.HomeActivity");
        capabilities.setCapability("app", "C:/Tools/Trello_new.apk"); // needs to be moved to resoucres

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);


        teamHelper = new TeamHelper(driver);
        boardHelper = new BoardHelper(driver);
        sessionHelper = new SessionHelper(driver);
        userHelper = new UserHelper(driver);

        //sessionHelper.login("m.duksaite@gmail.com","trusty07"); - to check swipe we do not have to login
    }

    public void stop() {
        driver.quit();
    }

    public TeamHelper getTeamHelper() {
        return teamHelper;
    }

    public BoardHelper getBoardHelper() {
        return boardHelper;
    }

    public SessionHelper getSessionHelper() {
        return sessionHelper;
    }

    public UserHelper getUserHelper() {
        return userHelper;
    }
}
