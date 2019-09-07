package com.trello.qa;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BoardCreationTests extends TestBase{

    @Test
    public void testBoardCreationByClickingOnCreateNewBoardButton() throws InterruptedException {
        //Assert.assertTrue(isUserLoggedIn());
        int before = getBoardsCount();
        createBoardGreyButton("First Board");
        confirmBoardCreationByClickingOnCreateNewBoardButton();
        returnToHomePage();
        Thread.sleep(3000);
        int after = getBoardsCount();
        Assert.assertEquals(after, before+1);
        boolean isPresent = findWebElementByText("First Board");
        Assert.assertEquals(isPresent, true);
    }

    @Test
    public void testBoardCreationByClickingOnPlusOnHeaderRight() throws InterruptedException {
        int before = getBoardsCount();
        clickOnPlusButtonOnHeader();
        selectCreateBoardFromDropDown();
        fillBoardCreationForm("qa21");
        confirmBoardCreationByClickingOnPlusOnHeaderRight();
        returnToHomePage();
        Thread.sleep(3000);
        int after = getBoardsCount();
        Assert.assertEquals(after, before+1);
        boolean isPresent = findWebElementByText("qa21");
        Assert.assertEquals(isPresent, true);
    }




}
