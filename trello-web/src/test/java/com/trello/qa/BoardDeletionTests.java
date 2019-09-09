package com.trello.qa;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class BoardDeletionTests extends TestBase{

    @Test
    public void deletionBoardTest (){

    int before = getPersonalBoardsCount();

    clickOnFirstPrivateBoard();
    clickOnMoreButtonInBoardMenu();
   // initCloseBoard(); // it's simple click on link Close board

    int after = getPersonalBoardsCount();

    }

    public void clickOnMoreButtonInBoardMenu() {
            WebElement menuButton = driver.findElement(By.cssSelector(".board-header-btn.mod-show-menu"));
            System.out.println(menuButton.getCssValue("visibility"));
        //if(menuButton.getAttribute("class").equals(""to write here what's in "" in that class"")
            if(menuButton.getCssValue("visibility").equals("visible")){
                click(By.cssSelector(".mod-show-menu"));
                click(By.cssSelector(".js-open-more"));
            } else{
                click(By.cssSelector(".js-open-more"));
            }
    }

    public void clickOnFirstPrivateBoard() {
       click(By.xpath("//div[@class='boards-page-board-section mod-no-sidebar']//h3[contains(text(),'Personal Boards')]/ancestor::div[@class='boards-page-board-section mod-no-sidebar']//ul[@class='boards-page-board-section-list']//li"));
    }

}
