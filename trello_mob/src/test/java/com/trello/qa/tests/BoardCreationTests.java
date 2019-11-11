package com.trello.qa.tests;

import org.testng.annotations.Test;

public class BoardCreationTests extends TestBase {
    @Test

    public void testBoardCreation(){
        app.getBoardHelper().clickOnPlusButton();

    }


}


