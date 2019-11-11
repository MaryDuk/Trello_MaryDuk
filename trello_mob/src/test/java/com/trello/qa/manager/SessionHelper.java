package com.trello.qa.manager;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SessionHelper extends HelperBase {
    public SessionHelper(AppiumDriver driver) {
        super(driver);
    }

    public void login(String email, String password) throws InterruptedException {
        click(By.id("log_in_button"));
    type(By.id("user"),email);
    type(By.id("password"),password);
        click(By.id("authenticate"));

    }

    public void openSite(String url) {
    driver.get(url);
    }

    public  boolean isUserLoggedIn() {
        return isElementPresent(By.cssSelector("[data-test-id='header-member-menu-button']"));
    }
}
