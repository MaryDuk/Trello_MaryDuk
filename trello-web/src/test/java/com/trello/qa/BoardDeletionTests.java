package com.trello.qa;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class BoardDeletionTests extends TestBase{

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
    public void deletionBoardTest () {
        int before = getPersonalBoardsCount();
        clickOnFirstPrivateBoard();
        clickOnMoreButtonInBoardMenu();
        initCloseBoard();
        confirmCloseBoard();
        initPermanentlyDeleteBoard();
        confirmDeleteBoard();
        returnToHomePage();
        int after = getPersonalBoardsCount();
        Assert.assertEquals(after, before-1);
    }


}

