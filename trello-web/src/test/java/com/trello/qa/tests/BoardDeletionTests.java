package com.trello.qa.tests;

import com.trello.qa.model.BoardData;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class BoardDeletionTests extends TestBase{
 //Test passed!!
    @BeforeClass
    public void ensurePreconditionsLogin() throws InterruptedException {
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

    @BeforeMethod
    public void isAnyPersonalBoardPresent () throws InterruptedException {
        if (app.getBoardHelper().getPersonalBoardsCount()<1) { app.getBoardHelper().clickOnPlusButtonOnHeader();
            app.getBoardHelper().selectCreateBoardFromDropDown();
            app.getBoardHelper().fillBoardCreationForm(new BoardData().withBoardTitle("Beautiful"));
            app.getBoardHelper().confirmBoardCreationByClickingOnPlusOnHeaderRight();
            app.getBoardHelper().returnToHomePage();
            app.getBoardHelper().refreshPage();

        }

    }


    @Test
    public void deletionBoardTest () throws InterruptedException {
        int before = app.getBoardHelper().getPersonalBoardsCount();
        System.out.println(before);
        app.getBoardHelper().clickOnFirstPrivateBoard();
        app.getBoardHelper().clickOnMoreButtonInBoardMenu();
        app.getBoardHelper().initCloseBoard();
        app.getBoardHelper().confirmCloseBoard();
        app.getBoardHelper().initPermanentlyDeleteBoard();
        app.getBoardHelper().confirmDeleteBoard();
        app.getBoardHelper().returnToHomePage();
        int after = app.getBoardHelper().getPersonalBoardsCount();
        Assert.assertEquals(after, before-1);
    }

}

