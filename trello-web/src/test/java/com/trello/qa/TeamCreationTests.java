package com.trello.qa;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TeamCreationTests extends  TestBase{
    @Test
    public void testTeamCreationFromPlusButtonOnHeader() throws InterruptedException {
        int before = getTeamsCount();
        System.out.println(before);
        clickOnPlusButtonOnHeader();
        selectCreateTeamFromDropDown();
        String teamName = "qa21";
        fillTeamCreationForm(teamName, "descr qa 21");
        clickContinueButton();
        String createdTeamName = getTeamNameFromTeamPage();
        returnToHomePage();
        //refreshPage();
        int after = getTeamsCount();
        System.out.println(after);
        Assert.assertEquals(after, before+1);
        Assert.assertEquals(createdTeamName.toLowerCase(), teamName.toLowerCase());
    }

    public void refreshPage() {
        driver.navigate().refresh();
    }

    @Test
    public void testTeamCreationFromLeftNavMenu() throws InterruptedException {
        int before = getTeamsCount();
        clickOnPlusButtonOnLeftNavMenu();
        fillTeamCreationForm("h", "g");
        clickContinueButton();
        String createdTeamName = getTeamNameFromTeamPage();
        returnToHomePage();
        int after = getTeamsCount();

        Assert.assertEquals(after, before+1);
        Assert.assertEquals(createdTeamName, "h");
    }

    public void clickOnPlusButtonOnLeftNavMenu() {
        click(By.cssSelector(".icon-add.icon-sm"));
    }

    @Test(enabled=false)
    public void testTeamCuncellCreationFromPlusButtonOnHeader(){
        clickOnPlusButtonOnHeader();
        selectCreateTeamFromDropDown();
        fillTeamCreationForm("qa21", "descr qa 21");
        clickXButton();
        //Assert


        Assert.assertTrue(isUserLoggedIn());
    }


}