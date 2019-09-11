package com.trello.qa;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TeamCreationTests extends  TestBase{
    @BeforeClass
    public void ensurePreconditionsLogin(){
        if(!isUserLoggedIn()){
            login("m.duksaite@gmail.com","trusty07");
        }
    }

    @BeforeMethod
    public void isOnHomePage () {
        if (!isTherePersonalBoards()) //checking that the element not present on homepage, then only return to homepage
        {
            returnToHomePage();
        }
    }

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

    @Test
    public void testTeamCreationFromLeftNavMenu() throws InterruptedException {
        int before = getTeamsCount();
        clickOnPlusButtonOnLeftNavMenu();
        fillTeamCreationForm("h", "g");
        clickContinueButton();
        //String createdTeamName = getTeamNameFromTeamPage();
        returnToHomePage();
        int after = getTeamsCount();

        Assert.assertEquals(after, before+1);
        //Assert.assertEquals(createdTeamName, "h");
    }

    @Test(enabled=false)
    public void testTeamCuncellCreationFromPlusButtonOnHeader(){
        clickOnPlusButtonOnHeader();
        selectCreateTeamFromDropDown();
        String teamName = "qa21-" + System.currentTimeMillis();
        fillTeamCreationForm("qa21", "descr qa 21");
        clickXButton();
        //Assert
        Assert.assertTrue(isUserLoggedIn());
    }

}