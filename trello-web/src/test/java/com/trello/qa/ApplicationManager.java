package com.trello.qa;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {


    WebDriver driver;

    public void init() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        openSite("https://trello.com");
        login("m.duksaite@gmail.com","trusty07");
    }

    public void login(String email, String password) {
    click(By.cssSelector("[href='/login']"));
    type(By.cssSelector("[type='email']"),email);
    type(By.cssSelector("[type='password']"),password);
    click(By.id("login")); // inspected element and added only value login

    }

    public void click(By locator) {
    driver.findElement(locator).click();
    }

    public void type(By locator, String text){
    driver.findElement(locator).click();
    driver.findElement(locator).clear();
    driver.findElement(locator).sendKeys(text);

    }

    public void openSite(String url) {
    driver.get(url);
    }

    public void stop() {
        driver.quit();
    }

    public  boolean isUserLoggedIn() {
        return isElementPresent(By.cssSelector("[data-test-id='header-member-menu-button']"));
    }

    public boolean isElementPresent (By locator) {
        return driver.findElements(locator).size()>0;
    }

    public void clickContinueButton() {
        click(By.cssSelector("[type=submit]"));
    }

    public void fillTeamCreationForm(String teamName, String description) {
        type(By.cssSelector("[data-test-id='header-create-team-name-input']"), teamName);
        type(By.cssSelector("textarea"), description);
    }

    public void selectCreateTeamFromDropDown() {
        click(By.cssSelector("[data-test-id='header-create-team-button']"));
    }

    public void clickOnPlusButtonOnHeader() {
        waitForElementAndClick(By.cssSelector("[data-test-id='header-create-menu-button']"), 15);
    }

    public void fillBoardCreationForm(String boardName) {
        type(By.cssSelector("[data-test-id='header-create-board-title-input']"), boardName);
        if(isElementPresent(By.cssSelector(".W6rMLOx8U0MrPx"))){
            click(By.cssSelector(".W6rMLOx8U0MrPx"));
            click(By.xpath("//nav[@class='SdlcRrTVPA8Y3K']//li[1]"));//no team
        }

    }

    public void selectCreateBoardFromDropDown() {
        click(By.cssSelector("[data-test-id='header-create-board-button']"));
    }

    public void confirmBoardCreationByClickingOnCreateNewBoardButton() {
        click(By.xpath("//span[contains(text(),'Create Board')]"));
    }

    public void confirmBoardCreationByClickingOnPlusOnHeaderRight() {
        waitForElementAndClick(By.cssSelector("[data-test-id='header-create-board-submit-button']"), 15);
    }

    protected String getTeamNameFromTeamPage() {
        new WebDriverWait(driver, 20).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("h1")));

        return driver.findElement(By.cssSelector("h1")).getText();
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

        new WebDriverWait(driver,20).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@class='_mtkwfAlvk6O3f']/../../..//li")));
            return driver.findElements(By.xpath("//*[@class='_mtkwfAlvk6O3f']/../../..//li")).size();
        } //checking that the element is present block of teams

    public void clickXButton() {

    }

    public void createBoardGreyButton (String boardTitle) throws InterruptedException {
        click(By.xpath("//*[@class='icon-lg icon-member']/../../..//span[contains(text(),'Create new board')]"));
        type(By.cssSelector("[placeholder='Add board title']"),boardTitle);
        click(By.cssSelector("[class='icon-sm icon-overflow-menu-horizontal']"));
        //click(By.xpath("//li[5]//div[1]//div[1]"));
        click(By.xpath("//span[@class='icon-sm icon-down subtle-chooser-trigger-dropdown-icon light']"));
        click(By.xpath("//span[contains(text(),'No team')]"));
        //click(By.xpath("//span[contains(text(),'Private')]"));
    }

    public List<WebElement> getList(String selector){
        return driver.findElements(By.xpath(selector));
    }

    public int getPersonalBoardsCount ()  {
    new WebDriverWait(driver,20).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='boards-page-board-section mod-no-sidebar']//h3[contains(text(),'Personal Boards')]/ancestor::div[@class='boards-page-board-section mod-no-sidebar']//ul[@class='boards-page-board-section-list']//li")));
    return driver.findElements(By.xpath("//div[@class='boards-page-board-section mod-no-sidebar']//h3[contains(text(),'Personal Boards')]/ancestor::div[@class='boards-page-board-section mod-no-sidebar']//ul[@class='boards-page-board-section-list']//li")).size()-1;
    }

    protected Boolean findWebElementByText (String boardTitle) {
    List<WebElement> webElementsList = driver.findElements(By.xpath("//div[@class='boards-page-board-section mod-no-sidebar']//h3[contains(text(),'Personal Boards')]/ancestor::div[@class='boards-page-board-section mod-no-sidebar']//ul[@class='boards-page-board-section-list']//li"));
        for(WebElement element : webElementsList){
            if(element.getText().equals(boardTitle))
                return true;
        }
       return false;
    }

    public boolean isTherePersonalBoards() {
        return isElementPresent(By.xpath("//*[@class='icon-lg icon-member']/../../.."));
    }

    public void refreshPage() {
        driver.navigate().refresh();
    }

    public void clickOnPlusButtonOnLeftNavMenu() {
        click(By.cssSelector(".icon-add.icon-sm"));
    }

    public void deleteTeam() {
        new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(By.cssSelector(".quiet-button")));
        click(By.cssSelector(".quiet-button"));
        click(By.cssSelector(".js-confirm.full.negate"));
    }

    public void openSettings() {
        new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(),'Settings')]")));
        //click(By.cssSelector(".icon-gear.icon-sm.OiX3P2i2J92XatType a message here"));
        click(By.xpath("//span[contains(text(),'Settings')]"));
    }

    public void clickOnFirstTeam() {
        click(By.xpath("//*[@class='_mtkwfAlvk6O3f']/../../..//li")); // clicks on first element
    }

    public void confirmDeleteBoard() {
        click(By.xpath("//input[@class='js-confirm full negate']"));
    }

    public void initPermanentlyDeleteBoard() {
        click(By.xpath("//a[@class='quiet js-delete']"));
    }

    public void confirmCloseBoard() {
        click(By.cssSelector("[class='js-confirm full negate']"));
    }

    public void initCloseBoard() {
        click(By.cssSelector("[class='board-menu-navigation-item-link js-close-board']"));
    }

    public void clickOnMoreButtonInBoardMenu() {
            WebElement menuButton = driver.findElement(By.cssSelector(".board-header-btn.mod-show-menu"));
            System.out.println(menuButton.getCssValue("visibility"));
        //if(menuButton.getAttribute("class").equals(""value of the class"")
            if(menuButton.getCssValue("visibility").equals("visible")){
                click(By.cssSelector(".mod-show-menu"));
                click(By.cssSelector(".js-open-more"));
            } else{
                click(By.cssSelector(".js-open-more"));
            }
    }

    public void clickOnFirstPrivateBoard() {
       //click(By.xpath("//div[@class='boards-page-board-section mod-no-sidebar']//h3[contains(text(),'Personal Boards')]/ancestor::div[@class='boards-page-board-section mod-no-sidebar']//ul[@class='boards-page-board-section-list']//li"));
        click(By.xpath("//*[@class='icon-lg icon-member']/../../..//li"));
    }

    public void deleteBoardsInCycle (){
        int before = getPersonalBoardsCount();
        if (before >=4) {
            while (before > 3) {
               clickOnFirstPrivateBoard();
                clickOnMoreButtonInBoardMenu();
                initCloseBoard();
                confirmCloseBoard();
                initPermanentlyDeleteBoard(); // check locator
                confirmDeleteBoard();
                returnToHomePage();
                before = getPersonalBoardsCount();
           }
        }

    }

    public void waitForElementAndClick(By locator, int time) {
        new WebDriverWait(driver, time).until(ExpectedConditions.elementToBeClickable(locator)).click();
    }
}
