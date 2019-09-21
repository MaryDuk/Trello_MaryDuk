package com.trello.qa.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class BoardModificationTests extends TestBase {

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

    @BeforeMethod
    public void isAnyPersonalBoardPresent (){
        if (app.getBoardHelper().getPersonalBoardsCount()<1) { app.getBoardHelper().clickOnPlusButtonOnHeader();
            app.getBoardHelper().selectCreateBoardFromDropDown();
            app.getBoardHelper().fillBoardCreationForm("Beautiful");
            app.getBoardHelper().confirmBoardCreationByClickingOnPlusOnHeaderRight();
            app.getBoardHelper().returnToHomePage();
            app.getBoardHelper().refreshPage();

        }
    }

  @Test
  public void   testChangeBoardTitle () {
        app.getBoardHelper().clickOnFirstPrivateBoard();
        String name = "Great";
        app.getBoardHelper().changeBoardName(name);
        app.getBoardHelper().returnToHomePage();
        app.getBoardHelper().refreshPage();
        boolean isChangedBoardNamePresent = app.getBoardHelper().findWebElementByText(name);
        Assert.assertEquals(isChangedBoardNamePresent, true);
  }

}
