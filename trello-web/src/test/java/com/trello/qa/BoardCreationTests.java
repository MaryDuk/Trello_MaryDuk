package com.trello.qa;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class BoardCreationTests extends TestBase{

    @BeforeClass
    public void ensurePreconditionsLogin(){
        if(!app.isUserLoggedIn()){
            app.login("m.duksaite@gmail.com","trusty07");
        }
    }

    @BeforeMethod
    public void isOnHomePage () {
        if (!app.isTherePersonalBoards())
        {
            app.returnToHomePage();
        }
    }

    @Test
    public void testBoardCreationByClickingOnCreateNewBoardButton() throws InterruptedException {
        //Assert.assertTrue(isUserLoggedIn());
        int before = app.getPersonalBoardsCount();
        app.createBoardGreyButton("First Board");
        app.confirmBoardCreationByClickingOnCreateNewBoardButton();
        app.returnToHomePage();
        int after = app.getPersonalBoardsCount();
        Assert.assertEquals(after, before+1);
        boolean isPresent = app.findWebElementByText("First Board");
        Assert.assertEquals(isPresent, true);
    }

    @Test
    public void testBoardCreationByClickingOnPlusOnHeaderRight() {
        int before = app.getPersonalBoardsCount();
        app.clickOnPlusButtonOnHeader();
        app.selectCreateBoardFromDropDown();
        app.fillBoardCreationForm("qa21");
        app.confirmBoardCreationByClickingOnPlusOnHeaderRight();
        app.returnToHomePage();
        int after = app.getPersonalBoardsCount();
        Assert.assertEquals(after, before+1);
        boolean isPresent = app.findWebElementByText("qa21");
        Assert.assertEquals(isPresent, true);
    }
//    @AfterClass
//    public void deleteExtraBoards (){
//        deleteBoardsInCycle();
//}

}
