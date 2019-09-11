package com.trello.qa;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class BoardCreationTests extends TestBase{

    @BeforeClass
    public void ensurePreconditionsLogin(){
        if(!isUserLoggedIn()){
            login("m.duksaite@gmail.com","trusty07");
        }
    }

    @BeforeMethod
    public void isOnHomePage () {
        if (!isTherePersonalBoards())
        {
            returnToHomePage();
        }
    }

    @Test
    public void testBoardCreationByClickingOnCreateNewBoardButton() throws InterruptedException {
        //Assert.assertTrue(isUserLoggedIn());
        int before = getPersonalBoardsCount();
        createBoardGreyButton("First Board");
        confirmBoardCreationByClickingOnCreateNewBoardButton();
        returnToHomePage();
        int after = getPersonalBoardsCount();
        Assert.assertEquals(after, before+1);
        boolean isPresent = findWebElementByText("First Board");
        Assert.assertEquals(isPresent, true);
    }

    @Test
    public void testBoardCreationByClickingOnPlusOnHeaderRight() {
        int before = getPersonalBoardsCount();
        clickOnPlusButtonOnHeader();
        selectCreateBoardFromDropDown();
        fillBoardCreationForm("qa21");
        confirmBoardCreationByClickingOnPlusOnHeaderRight();
        returnToHomePage();
        int after = getPersonalBoardsCount();
        Assert.assertEquals(after, before+1);
        boolean isPresent = findWebElementByText("qa21");
        Assert.assertEquals(isPresent, true);
    }
    @AfterClass
    public void deleteExtraBoards (){
        deleteBoardsInCycle();
}

}
