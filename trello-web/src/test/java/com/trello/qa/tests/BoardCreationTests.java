package com.trello.qa.tests;

import com.trello.qa.model.BoardData;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BoardCreationTests extends TestBase{

@DataProvider
public Iterator<Object[]> validBoards(){
    List<Object[]> list = new ArrayList<>();
    list.add(new Object[]{"title"});
    list.add(new Object[]{"TITLE"});
    list.add(new Object[]{"123345"});
    list.add(new Object[]{"!@#$%"});
    list.add(new Object[]{"title title"});
     return list.iterator();
    }

    @DataProvider
    public Iterator<Object> validBoardscsv() throws IOException {
        List <Object> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/Board.csv")));
        String line = reader.readLine();
        while (line!=null){
            BoardData boardData = new BoardData();
            list.add(boardData.withBoardTitle(line));
            line = reader.readLine();
        }
        return list.iterator();
    }

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

    @Test
    public void testBoardCreationByClickingOnCreateNewBoardButton() throws InterruptedException {
        //Assert.assertTrue(isUserLoggedIn());
        int before = app.getBoardHelper().getPersonalBoardsCount();
        app.getBoardHelper().createBoardGreyButton(new BoardData().withBoardTitle("Nice"));
        app.getBoardHelper().confirmBoardCreationByClickingOnCreateNewBoardButton();
        app.getBoardHelper().returnToHomePage();
        int after = app.getBoardHelper().getPersonalBoardsCount();
        Assert.assertEquals(after, before+1);
        boolean isPresent = app.getBoardHelper().findWebElementByText("Nice");
        Assert.assertEquals(isPresent, true);
    }

    @Test
    public void testBoardCreationByClickingOnPlusOnHeaderRight() throws InterruptedException {
        int before = app.getBoardHelper().getPersonalBoardsCount();
        app.getBoardHelper().clickOnPlusButtonOnHeader();
        app.getBoardHelper().selectCreateBoardFromDropDown();
        app.getBoardHelper().fillBoardCreationForm(new BoardData().withBoardTitle("Beautiful"));
        app.getBoardHelper().confirmBoardCreationByClickingOnPlusOnHeaderRight();
        app.getBoardHelper().returnToHomePage();
        int after = app.getBoardHelper().getPersonalBoardsCount();
        Assert.assertEquals(after, before+1);
        boolean isPresent = app.getBoardHelper().findWebElementByText("Beautiful");
        Assert.assertEquals(isPresent, true);
    }

    @Test(dataProvider = "validBoards")
    public void testBoardCreationByClickingOnPlusOnHeaderRightWithDataProvider(String boardTitle) throws InterruptedException {
        BoardData board = new BoardData().withBoardTitle(boardTitle);
        int before = app.getBoardHelper().getPersonalBoardsCount();
        app.getBoardHelper().clickOnPlusButtonOnHeader();
        app.getBoardHelper().selectCreateBoardFromDropDown();
        app.getBoardHelper().fillBoardCreationForm(board);
        app.getBoardHelper().confirmBoardCreationByClickingOnPlusOnHeaderRight();
        app.getBoardHelper().returnToHomePage();
        int after = app.getBoardHelper().getPersonalBoardsCount();
        Assert.assertEquals(after, before+1);
        boolean isPresent = app.getBoardHelper().findWebElementByText(boardTitle);
        Assert.assertEquals(isPresent, true);
    }

    @Test (dataProvider = "validBoards")
    public void testBoardCreationByClickingOnCreateNewBoardButtonWithDataProvider(String boardTitle) throws InterruptedException {
        BoardData board = new BoardData().withBoardTitle(boardTitle);
        int before = app.getBoardHelper().getPersonalBoardsCount();
        app.getBoardHelper().createBoardGreyButton(board);
        app.getBoardHelper().confirmBoardCreationByClickingOnCreateNewBoardButton();
        app.getBoardHelper().returnToHomePage();
        int after = app.getBoardHelper().getPersonalBoardsCount();
        Assert.assertEquals(after, before + 1);
        boolean isPresent = app.getBoardHelper().findWebElementByText(boardTitle);
        Assert.assertEquals(isPresent, true);
    }
    @Test (dataProvider = "validBoardscsv")
    public void testBoardCreationByClickingOnPlusOnHeaderRightWithDataProvidercsv(BoardData board) throws InterruptedException {
        int before = app.getBoardHelper().getPersonalBoardsCount();
        app.getBoardHelper().clickOnPlusButtonOnHeader();
        app.getBoardHelper().selectCreateBoardFromDropDown();
        app.getBoardHelper().fillBoardCreationForm(board);
        app.getBoardHelper().confirmBoardCreationByClickingOnPlusOnHeaderRight();
        app.getBoardHelper().returnToHomePage();
        int after = app.getBoardHelper().getPersonalBoardsCount();
        Assert.assertEquals(after, before+1);
        boolean isPresent = app.getBoardHelper().findWebElementByText(board.getBoardTitle());
        Assert.assertEquals(isPresent, true);
    }

    @AfterClass
    public void deleteExtraBoardsTillThreeLeft () throws InterruptedException {
        app.getBoardHelper().deleteBoardsInCycle();
    }

}
