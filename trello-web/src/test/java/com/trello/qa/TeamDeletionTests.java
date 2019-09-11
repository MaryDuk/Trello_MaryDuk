package com.trello.qa;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TeamDeletionTests extends TestBase {
    @Test
    public void deleteTeamFromLeftNavMenu() {
        int before = getTeamsCount();
        clickOnFirstTeam();
        openSettings();
        deleteTeam();
        returnToHomePage();
        int after = getTeamsCount();
        Assert.assertEquals(after, before-1);

    }

}
