package com.trello.qa.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TeamModificationTests extends TestBase {


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