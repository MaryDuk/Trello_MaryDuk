package com.trello.qa.manager;

import com.google.common.io.Files;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;

public class HelperBase {

    WebDriver driver;

    public HelperBase(WebDriver driver) {

        this.driver = driver;
    }

    public void click(By locator) {
    driver.findElement(locator).click();
    }

    public void type(By locator, String text) throws InterruptedException {
        if (text!=null){
            Thread.sleep(3000);
            driver.findElement(locator).click();
            driver.findElement(locator).clear();
            driver.findElement(locator).sendKeys(text);
        }
    }

    public void typeByJavaScriptExecutorBoardName(By inputLocator, String text){
//        Thread.sleep(1000);
//        driver.findElement(btnLocator).click();
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].value='" + text + "';", driver.findElement(inputLocator));
        //driver.findElement(inputLocator).clear();
        //driver.findElement(inputLocator).sendKeys(text);
    }

    public boolean isElementPresent (By locator) {
        return driver.findElements(locator).size()>0;
    }

    public void clickContinueButton() {
        click(By.cssSelector("[type=submit]"));
    }

    public void clickOnPlusButtonOnHeader() {
        waitForElementAndClick(By.cssSelector("[data-test-id='header-create-menu-button']"), 15);
    }

    public void returnToHomePage() {
        if(isElementPresent(By.cssSelector("._3gUubwRZDWaOF0._2WhIqhRFBTG7Ry._2NubQcQM83YCVV"))){
            new WebDriverWait(driver, 20).until(ExpectedConditions.stalenessOf(driver.findElement(By.cssSelector("._3gUubwRZDWaOF0._2WhIqhRFBTG7Ry._2NubQcQM83YCVV")))); // stalenessOf - dissapears
            click(By.cssSelector("a[href='/']"));
            click(By.cssSelector("a[href='/']"));
        } else
            click(By.cssSelector("a[href='/']"));
            waitForElementAndClick(By.cssSelector("a[href='/']"), 20);
    } //waiting while the blocking element disapears and we can click on home button

    public int getTeamsCount() {
        if(isElementPresent(By.xpath("//span[contains(text(),'Create a team')]"))) {
            new WebDriverWait(driver, 20).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@class='_mtkwfAlvk6O3f']/../../..//li")));
            return driver.findElements(By.xpath("//*[@class='_mtkwfAlvk6O3f']/../../..//li")).size()-1;
        }else {
            new WebDriverWait(driver, 20).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@class='_mtkwfAlvk6O3f']/../../..//li")));
            return driver.findElements(By.xpath("//*[@class='_mtkwfAlvk6O3f']/../../..//li")).size();
        }

        } //checking that the element is present block of teams

    public boolean isTherePersonalBoards() {
        return isElementPresent(By.xpath("//*[@class='icon-lg icon-member']/../../.."));
    }

    public void clickXButton() {

    }

    public void refreshPage() {
        driver.navigate().refresh();
    }//a[@class='quiet js-delete

    public void clickOnPlusButtonOnLeftNavMenu() {
        click(By.cssSelector(".icon-add.icon-sm"));
    }

    public void waitForElementAndClick(By locator, int time) {
        new WebDriverWait(driver, time).until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    public void takeScreenShot(){
        File tmp = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        File screen = new File("src/test/resources/Screenshots/screen"+ System.currentTimeMillis()+".png"); // choose try catch
        try {
            Files.copy(tmp, screen);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
