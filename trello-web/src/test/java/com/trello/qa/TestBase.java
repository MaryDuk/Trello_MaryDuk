package com.trello.qa;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class TestBase {
WebDriver driver;
@BeforeClass
public void setUp(){
    driver = new ChromeDriver();
    driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
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

    @AfterClass
public void tearDown (){
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
        click(By.cssSelector("[data-test-id='header-create-menu-button']"));
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
        click(By.cssSelector("[data-test-id='header-create-board-submit-button']"));
    }

    protected String getTeamNameFromTeamPage() {

        return driver.findElement(By.cssSelector("h1")).getText();
    }

    public void returnToHomePage() throws InterruptedException {
        Thread.sleep(10000);
        click(By.cssSelector("a[aria-label='Back to Home']"));
        click(By.cssSelector("a[aria-label='Back to Home']"));
    }

    public int getTeamsCount() throws InterruptedException {
        Thread.sleep(5000);
            return driver.findElements(By.xpath("//*[@class='_mtkwfAlvk6O3f']/../../..//li")).size();
        }


    public void clickXButton() {

    }

    public void createBoardGreyButton (String boardTitle) throws InterruptedException {
        click(By.cssSelector("[class='board-tile mod-add']"));
        type(By.cssSelector("[placeholder='Add board title']"),boardTitle);
        click(By.cssSelector("[class='icon-sm icon-overflow-menu-horizontal']"));
        click(By.xpath("//li[5]//div[1]//div[1]"));
        click(By.xpath("//span[@class='icon-sm icon-down subtle-chooser-trigger-dropdown-icon light']"));
        click(By.xpath("//span[contains(text(),'No team')]"));
        //click(By.xpath("//span[contains(text(),'Private')]"));
    }

    public List<WebElement> getList(String selector){
        return driver.findElements(By.xpath(selector));
    }

    public int getPersonalBoardsCount (){
    return driver.findElements(By.xpath("//div[@class='boards-page-board-section mod-no-sidebar']//h3[contains(text(),'Personal Boards')]/ancestor::div[@class='boards-page-board-section mod-no-sidebar']//ul[@class='boards-page-board-section-list']//li")).size() -1;
    }


    protected Boolean findWebElementByText (String boardTitle) {
    List<WebElement> webElementsList = driver.findElements(By.xpath("//div[@class='boards-page-board-section mod-no-sidebar']//h3[contains(text(),'Personal Boards')]/ancestor::div[@class='boards-page-board-section mod-no-sidebar']//ul[@class='boards-page-board-section-list']//li"));
        for(WebElement element : webElementsList){
            if(element.getText().equals(boardTitle))
                return true;
        }
       return false;
    }





}
