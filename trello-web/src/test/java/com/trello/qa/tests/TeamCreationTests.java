package com.trello.qa.tests;

import com.trello.qa.model.TeamData;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TeamCreationTests extends  TestBase{
    @DataProvider
    public Iterator<Object[]>validTeams(){
        List <Object[]> list = new ArrayList<>();
        list.add(new Object[]{"name", "description"});
        list.add(new Object[]{"NAME", "DESC"});
        list.add(new Object[]{"1234", "4567"});
        list.add(new Object[]{"^%$#", "#$%^&@"});
        list.add(new Object[]{"name", ""});
      return list.iterator();
    }
    @DataProvider
    public Iterator<Object[]>validTeamsfromcsv() throws IOException {
        List <Object[]> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/Team.csv")));
        String line = reader.readLine();
        while (line!=null){
            TeamData teamData = new TeamData();
            String[] values = line.split(",");
            list.add(new Object[] {
                    teamData.withTeamName(values[0]).withDescription(values[1])
            });
            line = reader.readLine();
        }
        return list.iterator();
    }

    @BeforeClass
    public void ensurePreconditionsLogin() throws InterruptedException {
        if(!app.getSessionHelper().isUserLoggedIn()){
            app.getSessionHelper().login("m.duksaite@gmail.com","trusty07");
        }
    }

    @BeforeMethod
    public void isOnHomePage () {
        if (!app.getTeamHelper().isTherePersonalBoards()) //checking that the element not present on homepage, then only return to homepage
        {
            app.getTeamHelper().returnToHomePage();
        }
    }

    @Test
    public void testTeamCreationFromPlusButtonOnHeader() throws InterruptedException {
        int before = app.getTeamHelper().getTeamsCount();
        System.out.println(before);
        app.getTeamHelper().clickOnPlusButtonOnHeader();
        app.getTeamHelper().selectCreateTeamFromDropDown();
        String teamName = "qa21";
        app.getTeamHelper().fillTeamCreationForm(new TeamData().withTeamName(teamName).withDescription("descr qa 21"));
        app.getTeamHelper().clickContinueButton();
        String createdTeamName = app.getTeamHelper().getTeamNameFromTeamPage();
        app.getTeamHelper().returnToHomePage();
        //refreshPage();
        int after = app.getTeamHelper().getTeamsCount();
        System.out.println(after);
        Assert.assertEquals(after, before+1);
        Assert.assertEquals(createdTeamName.toLowerCase(), teamName.toLowerCase());
    }

    @Test
    public void testTeamCreationFromLeftNavMenu() throws InterruptedException {
        int before = app.getTeamHelper().getTeamsCount();
        app.getTeamHelper().clickOnPlusButtonOnLeftNavMenu();
        app.getTeamHelper().fillTeamCreationForm(new TeamData().withTeamName("h").withDescription("g"));
        app.getTeamHelper().clickContinueButton();
        //String createdTeamName = getTeamNameFromTeamPage();
        app.getTeamHelper().returnToHomePage();
        int after = app.getTeamHelper().getTeamsCount();

        Assert.assertEquals(after, before+1);
        //Assert.assertEquals(createdTeamName, "h");
    }

    @Test(enabled=false)
    public void testTeamCancelCreationFromPlusButtonOnHeader() throws InterruptedException {
        app.getTeamHelper().clickOnPlusButtonOnHeader();
        app.getTeamHelper().selectCreateTeamFromDropDown();
        String teamName = "qa21-" + System.currentTimeMillis();
        app.getTeamHelper().fillTeamCreationForm(new TeamData().withTeamName("qa21").withDescription("descr qa 21"));
        app.getTeamHelper().clickXButton();
        //Assert
        Assert.assertTrue(app.getSessionHelper().isUserLoggedIn());
    }

    @Test(dataProvider = "validTeams")
    public void testTeamCreationFromPlusButtonOnHeaderWithDataProvider(String teamName, String description) throws InterruptedException {
       TeamData team = new TeamData().withTeamName(teamName).withDescription(description);
        int before = app.getTeamHelper().getTeamsCount();
        System.out.println(before);
        app.getTeamHelper().clickOnPlusButtonOnHeader();
        app.getTeamHelper().selectCreateTeamFromDropDown();
        //String teamName = "qa21";
        app.getTeamHelper().fillTeamCreationForm(team);
        app.getTeamHelper().clickContinueButton();
        String createdTeamName = app.getTeamHelper().getTeamNameFromTeamPage();
        app.getTeamHelper().returnToHomePage();
        //refreshPage();
        int after = app.getTeamHelper().getTeamsCount();
        System.out.println(after);
        Assert.assertEquals(after, before+1);
        Assert.assertEquals(createdTeamName.toLowerCase(), teamName.toLowerCase());
    }

    @Test(dataProvider = "validTeamsfromcsv")
    public void testTeamCreationFromPlusButtonOnHeaderWithDataProviderfromcsv(TeamData team) throws InterruptedException {
        // TeamData team = new TeamData().withTeamName(teamName).withDescription(description);
        int before = app.getTeamHelper().getTeamsCount();
        app.getTeamHelper().clickOnPlusButtonOnHeader();
        app.getTeamHelper().selectCreateTeamFromDropDown();
        //  String teamName = "QA21-"+ System.currentTimeMillis();
        app.getTeamHelper().fillTeamCreationForm(team);
        app.getTeamHelper().clickContinueButton();
        //  String createdTeamName = getTeamNameFromTeamPage();
        app.getTeamHelper().returnToHomePage();
        int after = app.getTeamHelper().getTeamsCount();
        Assert.assertEquals(after, before+1);
        //  Assert.assertEquals(createdTeamName.toLowerCase(), teamName.toLowerCase());
    }
    @AfterClass (enabled=false)
    public void postActions () throws InterruptedException {
        app.getTeamHelper().cleanTeams();
    }

}