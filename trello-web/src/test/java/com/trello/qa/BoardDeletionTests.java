package com.trello.qa;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class BoardDeletionTests extends TestBase{

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

    @BeforeMethod
    public void isAnyPersonalBoardPresent (){
        if (app.getPersonalBoardsCount()<1) { app.createBoardGreyButton("First Board");
            app.confirmBoardCreationByClickingOnCreateNewBoardButton();
            app.returnToHomePage();  }
    }

    @Test
    public void deletionBoardTest () {
        int before = app.getPersonalBoardsCount();
        app.clickOnFirstPrivateBoard();
        app.clickOnMoreButtonInBoardMenu();
        app.initCloseBoard();
        app.confirmCloseBoard();
        app.initPermanentlyDeleteBoard();
        app.confirmDeleteBoard();
        app.returnToHomePage();
        int after = app.getPersonalBoardsCount();
        Assert.assertEquals(after, before-1);
    }


}

