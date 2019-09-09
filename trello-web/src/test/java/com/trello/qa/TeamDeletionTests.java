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
        Assert.assertEquals(after, before - 1);


    }

    public void deleteTeam() {
        new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(By.cssSelector(".quiet-button")));
        click(By.cssSelector(".quiet-button"));
        click(By.cssSelector(".js-confirm.full.negate"));
    }

    public void openSettings() {
        click(By.cssSelector(".icon-gear.icon-sm.OiX3P2i2J92XatType a message here"));
    }

    public void clickOnFirstTeam() {
        click(By.xpath("//*[@class='_mtkwfAlvk6O3f']/../../..//li")); // clicks on first element
    }

}
