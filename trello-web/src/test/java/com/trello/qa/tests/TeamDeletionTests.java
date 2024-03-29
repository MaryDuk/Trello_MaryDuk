package com.trello.qa.tests;

import com.trello.qa.model.TeamData;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TeamDeletionTests extends TestBase {

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
            app.getTeamHelper().returnToHomePage();
        }
    }

    @BeforeMethod
    public void isAnyTeamPresent () throws InterruptedException {
        if (app.getTeamHelper().getTeamsCount()<1){
            app.getTeamHelper().clickOnPlusButtonOnLeftNavMenu();
            app.getTeamHelper().fillTeamCreationForm(new TeamData().withTeamName("Smile").withDescription("People"));
            app.getTeamHelper().clickContinueButton();
            app.getTeamHelper().returnToHomePage();
        }

    }

    @Test
    public void deleteTeamFromLeftNavMenu() throws InterruptedException {
        int before = app.getTeamHelper().getTeamsCount();
        app.getTeamHelper().clickOnFirstTeam();
        app.getTeamHelper().openSettings();
        app.getTeamHelper().deleteTeam();
        app.getTeamHelper().returnToHomePage();
        app.getTeamHelper().refreshPage();
        int after = app.getTeamHelper().getTeamsCount();
        Assert.assertEquals(after, before-1);

    }

}
