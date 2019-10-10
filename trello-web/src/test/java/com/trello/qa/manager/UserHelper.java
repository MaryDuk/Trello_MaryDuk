package com.trello.qa.manager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import java.io.File;

public class UserHelper extends HelperBase {
    public UserHelper(WebDriver driver) {
        super(driver);
    }

    public void clickOnAvatar() {
        click(By.cssSelector("[data-test-id='header-member-menu-button']"));
    }

    public void openProfileFromDropDown() {
        click(By.cssSelector("[data-test-id='header-member-menu-profile']"));
    }

    public void initAvatarChanging() {
        new Actions(driver).moveToElement(driver.findElement(By.cssSelector(".rsiNque2CCqtPE"))).click().perform();
    }

    public void addPicture() throws InterruptedException {
        attach(new File("https:/www.dropbox.com/s/kxfkxviu90awm2s/2014-03-22%2010.57.26.jpg?dl=0")); //path needs to be changed to save picture on local pc
        Thread.sleep(3000);
    }
    //C:/Uswer.....total patch where picture is

    private void attach(File file) {
        driver.findElement(By.name("file")).sendKeys(file.getAbsolutePath());
    }


}
