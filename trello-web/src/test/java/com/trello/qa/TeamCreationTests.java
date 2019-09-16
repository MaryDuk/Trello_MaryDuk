package com.trello.qa;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TeamCreationTests extends  TestBase{
    @BeforeClass
    public void ensurePreconditionsLogin(){
        if(!app.isUserLoggedIn()){
            app.login("m.duksaite@gmail.com","trusty07");
        }
    }

    @BeforeMethod
    public void isOnHomePage () {
        if (!app.isTherePersonalBoards()) //checking that the element not present on homepage, then only return to homepage
        {
            app.returnToHomePage();
        }
    }

    @Test
    public void testTeamCreationFromPlusButtonOnHeader() throws InterruptedException {
        int before = app.getTeamsCount();
        System.out.println(before);
        app.clickOnPlusButtonOnHeader();
        app.selectCreateTeamFromDropDown();
        String teamName = "qa21";
        app.fillTeamCreationForm(teamName, "descr qa 21");
        app.clickContinueButton();
        String createdTeamName = app.getTeamNameFromTeamPage();
        app.returnToHomePage();
        //refreshPage();
        int after = app.getTeamsCount();
        System.out.println(after);
        Assert.assertEquals(after, before+1);
        Assert.assertEquals(createdTeamName.toLowerCase(), teamName.toLowerCase());
    }

    @Test
    public void testTeamCreationFromLeftNavMenu() throws InterruptedException {
        int before = app.getTeamsCount();
        app.clickOnPlusButtonOnLeftNavMenu();
        app.fillTeamCreationForm("h", "g");
        app.clickContinueButton();
        //String createdTeamName = getTeamNameFromTeamPage();
        app.returnToHomePage();
        int after = app.getTeamsCount();

        Assert.assertEquals(after, before+1);
        //Assert.assertEquals(createdTeamName, "h");
    }

    @Test(enabled=false)
    public void testTeamCancelCreationFromPlusButtonOnHeader(){
        app.clickOnPlusButtonOnHeader();
        app.selectCreateTeamFromDropDown();
        String teamName = "qa21-" + System.currentTimeMillis();
        app.fillTeamCreationForm("qa21", "descr qa 21");
        app.clickXButton();
        //Assert
        Assert.assertTrue(app.isUserLoggedIn());
    }
    @AfterClass
    public void postActions (){
        app.cleanTeams();
    }

}