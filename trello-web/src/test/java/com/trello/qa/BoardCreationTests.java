package com.trello.qa;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
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
        app.createBoardGreyButton("Nice");
        app.confirmBoardCreationByClickingOnCreateNewBoardButton();
        app.returnToHomePage();
        int after = app.getPersonalBoardsCount();
        Assert.assertEquals(after, before+1);
        boolean isPresent = app.findWebElementByText("Nice");
        Assert.assertEquals(isPresent, true);
    }

    @Test
    public void testBoardCreationByClickingOnPlusOnHeaderRight() {
        int before = app.getPersonalBoardsCount();
        app.clickOnPlusButtonOnHeader();
        app.selectCreateBoardFromDropDown();
        app.fillBoardCreationForm("Beutiful");
        app.confirmBoardCreationByClickingOnPlusOnHeaderRight();
        app.returnToHomePage();
        int after = app.getPersonalBoardsCount();
        Assert.assertEquals(after, before+1);
        boolean isPresent = app.findWebElementByText("Beutiful");
        Assert.assertEquals(isPresent, true);
    }
    @Test
    public void deleteExtraBoards (){
        app.deleteBoardsInCycle();
}

}
