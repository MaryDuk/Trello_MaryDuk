package com.trello.qa.tests;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class BoardCreationTests extends TestBase{

    @BeforeClass
    public void ensurePreconditionsLogin(){
        if(!app.getSessionHelper().isUserLoggedIn()){
            app.getSessionHelper().login("m.duksaite@gmail.com","trusty07");
        }
    }

    @BeforeMethod
    public void isOnHomePage () {
        if (!app.getBoardHelper().isTherePersonalBoards())
        {
            app.getBoardHelper().returnToHomePage();
        }
    }

    @Test
    public void testBoardCreationByClickingOnCreateNewBoardButton() {
        //Assert.assertTrue(isUserLoggedIn());
        int before = app.getBoardHelper().getPersonalBoardsCount();
        app.getBoardHelper().createBoardGreyButton("Nice");
        app.getBoardHelper().confirmBoardCreationByClickingOnCreateNewBoardButton();
        app.getBoardHelper().returnToHomePage();
        int after = app.getBoardHelper().getPersonalBoardsCount();
        Assert.assertEquals(after, before+1);
        boolean isPresent = app.getBoardHelper().findWebElementByText("Nice");
        Assert.assertEquals(isPresent, true);
    }

    @Test
    public void testBoardCreationByClickingOnPlusOnHeaderRight() {
        int before = app.getBoardHelper().getPersonalBoardsCount();
        app.getBoardHelper().clickOnPlusButtonOnHeader();
        app.getBoardHelper().selectCreateBoardFromDropDown();
        app.getBoardHelper().fillBoardCreationForm("Beautiful");
        app.getBoardHelper().confirmBoardCreationByClickingOnPlusOnHeaderRight();
        app.getBoardHelper().returnToHomePage();
        int after = app.getBoardHelper().getPersonalBoardsCount();
        Assert.assertEquals(after, before+1);
        boolean isPresent = app.getBoardHelper().findWebElementByText("Beautiful");
        Assert.assertEquals(isPresent, true);
    }
    @AfterClass
    public void deleteExtraBoardsTillThreeLeft () throws InterruptedException {
        app.getBoardHelper().deleteBoardsInCycle();
}

}
