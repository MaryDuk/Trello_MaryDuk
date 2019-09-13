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
        if (app.getPersonalBoardsCount()<1) { app.clickOnPlusButtonOnHeader();
            app.selectCreateBoardFromDropDown();
            app.fillBoardCreationForm("Beautiful");
            app.confirmBoardCreationByClickingOnPlusOnHeaderRight();
            app.returnToHomePage();
            app.refreshPage();

        }

    }


    @Test
    public void deletionBoardTest (){
        int before = app.getPersonalBoardsCount();
        System.out.println(before);
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

