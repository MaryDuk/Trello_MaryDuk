package com.trello.qa;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SessionHelper extends HelperBase {
    public SessionHelper(WebDriver driver) {
        super(driver);
    }

    public void login(String email, String password) {
    click(By.cssSelector("[href='/login']"));
    type(By.cssSelector("[type='email']"),email);
    type(By.cssSelector("[type='password']"),password);
    click(By.id("login")); // inspected element and added only value login

    }

    public void openSite(String url) {
    driver.get(url);
    }

    public  boolean isUserLoggedIn() {
        return isElementPresent(By.cssSelector("[data-test-id='header-member-menu-button']"));
    }
}
