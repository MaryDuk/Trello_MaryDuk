package com.trello.qa;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TeamDeletionTests extends TestBase {
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
    public void isAnyTeamPresent (){
        if (app.getTeamsCount()<1){
            app.clickOnPlusButtonOnLeftNavMenu();
            app.fillTeamCreationForm("Smile", "People");
            app.clickContinueButton();
            app.returnToHomePage();
        }

    }

    @Test
    public void deleteTeamFromLeftNavMenu(){
        int before = app.getTeamsCount();
        app.clickOnFirstTeam();
        app.openSettings();
        app.deleteTeam();
        app.returnToHomePage();
        int after = app.getTeamsCount();
        Assert.assertEquals(after, before-1);

    }

}
