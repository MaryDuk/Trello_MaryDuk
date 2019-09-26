package com.trello.qa.tests;

import com.trello.qa.model.TeamData;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TeamModificationTests extends TestBase {
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
            app.getTeamHelper().returnToHomePage();
        }
    }

    @BeforeMethod
    public void isAnyTeamPresent (){
        if (app.getTeamHelper().getTeamsCount()<1){
            app.getTeamHelper().clickOnPlusButtonOnLeftNavMenu();
            app.getTeamHelper().fillTeamCreationForm(new TeamData().withTeamName("Smile").withDescription("People"));
            app.getTeamHelper().clickContinueButton();
            app.getTeamHelper().returnToHomePage();
        }

    }


    @Test
    public void testRenameTeam () throws InterruptedException {
     app.getTeamHelper().clickOnFirstTeam(); //second option available clickOnFirstTeam
     app.getTeamHelper().openSettings(); //second option available openSettings
        app.getTeamHelper().initEditTeamProfile();
        app.getTeamHelper().changeTeamProfile("h h", "hha");
        app.getTeamHelper().confirmEditTeam();
        Thread.sleep(3000);
        System.out.println(app.getTeamHelper().getTeamNameFromTeamPage());
        Thread.sleep(3000);
        Assert.assertEquals(app.getTeamHelper().getTeamNameFromTeamPage(), "h h");


    }
}