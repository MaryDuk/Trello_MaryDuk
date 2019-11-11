package com.trello.qa.manager;

import com.trello.qa.model.BoardData;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class BoardHelper extends HelperBase{
    public BoardHelper(AppiumDriver driver) {
        super(driver);
    }

    public void fillBoardCreationForm(BoardData board) throws InterruptedException {
        type(By.cssSelector("[data-test-id='header-create-board-title-input']"), board.getBoardTitle());
        if(isElementPresent(By.cssSelector(".W6rMLOx8U0MrPx"))){
            click(By.cssSelector(".W6rMLOx8U0MrPx"));
            click(By.xpath("//nav[@class='SdlcRrTVPA8Y3K']//li[1]"));//no team
        }

    }
    public void createBoardGreyButton (BoardData board) throws InterruptedException {
        click(By.xpath("//*[@class='icon-lg icon-member']/../../..//span[contains(text(),'Create new board')]"));
        type(By.cssSelector("[placeholder='Add board title']"),board.getBoardTitle());
        click(By.cssSelector("[class='icon-sm icon-overflow-menu-horizontal']"));
        int teams = getTeamsCount();
        System.out.println(teams);
        //click(By.xpath("//li[5]//div[1]//div[1]"));
        if (getTeamsCount()>0) {
            click(By.xpath("//span[@class='icon-sm icon-down subtle-chooser-trigger-dropdown-icon light']"));
            click(By.xpath("//span[contains(text(),'No team')]"));
        }
        //click(By.xpath("//span[contains(text(),'Private')]"));
    }

    public void selectCreateBoardFromDropDown() throws InterruptedException {
        Thread.sleep(3000);
        waitForElementAndClick(By.cssSelector("[data-test-id='header-create-board-button']"), 30);
    }

    public void confirmBoardCreationByClickingOnCreateNewBoardButton() {
        waitForElementAndClick(By.xpath("//span[contains(text(),'Create Board')]"), 20);
        //click(By.xpath("//span[contains(text(),'Create Board')]"));
    }

    public void confirmBoardCreationByClickingOnPlusOnHeaderRight() {
        //waitForElementAndClick(By.cssSelector("[data-test-id='header-create-board-submit-button']"), 30);
        click(By.cssSelector("[data-test-id='header-create-board-submit-button']"));
    }



    public List<WebElement> getList(String selector){
        return driver.findElements(By.xpath(selector));
    }

    public int getPersonalBoardsCount ()  {
    new WebDriverWait(driver,20).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='boards-page-board-section mod-no-sidebar']//h3[contains(text(),'Personal Boards')]/ancestor::div[@class='boards-page-board-section mod-no-sidebar']//ul[@class='boards-page-board-section-list']//li")));
    return driver.findElements(By.xpath("//div[@class='boards-page-board-section mod-no-sidebar']//h3[contains(text(),'Personal Boards')]/ancestor::div[@class='boards-page-board-section mod-no-sidebar']//ul[@class='boards-page-board-section-list']//li")).size()-1;
    }

    public Boolean findWebElementByText(String boardTitle) throws InterruptedException {
        Thread.sleep(3000);
    List<WebElement> webElementsList = driver.findElements(By.xpath("//div[@class='boards-page-board-section mod-no-sidebar']//h3[contains(text(),'Personal Boards')]/ancestor::div[@class='boards-page-board-section mod-no-sidebar']//ul[@class='boards-page-board-section-list']//li"));
        for(WebElement element : webElementsList){
            if(element.getText().equals(boardTitle))
                return true;
        }
       return false;
    }



    public void confirmDeleteBoard() {
        click(By.xpath("//input[@class='js-confirm full negate']"));
    }

    public void initPermanentlyDeleteBoard() throws InterruptedException {
        refreshPage();
        Thread.sleep(3000);
        waitForElementAndClick(By.xpath("//a[@class='quiet js-delete']"), 30);
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
        new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@class='icon-lg icon-member']/../../..//li")));
        click(By.xpath("//*[@class='icon-lg icon-member']/../../..//li"));
        takeScreenShot();
    }

    public void deleteBoardsInCycle () throws InterruptedException {
        int before = getPersonalBoardsCount();
            while (before > 3) {
               clickOnFirstPrivateBoard();
               Thread.sleep(3000);
                clickOnMoreButtonInBoardMenu();
                initCloseBoard();
                confirmCloseBoard();
                initPermanentlyDeleteBoard(); // check locator
                confirmDeleteBoard();
                returnToHomePage();
                before = getPersonalBoardsCount();
           }
    }

    public void changeBoardName(String name) {
        //waitForElementAndClick(By.xpath("//span[@class='js-board-editing-target board-header-btn-text']"), 10);
        //type(By.xpath("//span[@class='js-board-editing-target board-header-btn-text']"), name);
        click(By.xpath("//span[@class='js-board-editing-target board-header-btn-text']"));
        typeByJavaScriptExecutorBoardName(By.xpath("//input[@class='board-name-input js-board-name-input']"), name);
    }
//______________________________________________________
    public void clickOnPlusButton() {
        click(By.id("add_fab"));
    }
}
