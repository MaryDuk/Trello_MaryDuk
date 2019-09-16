package com.trello.qa;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class BoardHelper extends HelperBase{
    public BoardHelper(WebDriver driver) {
        super(driver);
    }

    public void fillBoardCreationForm(String boardName) {
        type(By.cssSelector("[data-test-id='header-create-board-title-input']"), boardName);
        if(isElementPresent(By.cssSelector(".W6rMLOx8U0MrPx"))){
            click(By.cssSelector(".W6rMLOx8U0MrPx"));
            click(By.xpath("//nav[@class='SdlcRrTVPA8Y3K']//li[1]"));//no team
        }

    }

    public void selectCreateBoardFromDropDown() {
        waitForElementAndClick(By.cssSelector("[data-test-id='header-create-board-button']"), 20);
    }

    public void confirmBoardCreationByClickingOnCreateNewBoardButton() {
        waitForElementAndClick(By.xpath("//span[contains(text(),'Create Board')]"), 20);
        //click(By.xpath("//span[contains(text(),'Create Board')]"));
    }

    public void confirmBoardCreationByClickingOnPlusOnHeaderRight() {
        waitForElementAndClick(By.cssSelector("[data-test-id='header-create-board-submit-button']"), 30);
    }

    public void createBoardGreyButton (String boardTitle) {
        click(By.xpath("//*[@class='icon-lg icon-member']/../../..//span[contains(text(),'Create new board')]"));
        type(By.cssSelector("[placeholder='Add board title']"),boardTitle);
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

    public void confirmDeleteBoard() {
        click(By.xpath("//input[@class='js-confirm full negate']"));
    }

    public void initPermanentlyDeleteBoard() {
        refreshPage();
        waitForElementAndClick(By.xpath("//a[@class='quiet js-delete']"), 20);
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
    }

    public void deleteBoardsInCycle (){
        int before = getPersonalBoardsCount();
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
