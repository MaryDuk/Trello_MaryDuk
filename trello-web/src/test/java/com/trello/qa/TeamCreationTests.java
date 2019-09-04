package com.trello.qa;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TeamCreationTests extends TestBase {
    @Test

    public void testTeamCreation(){
        Assert.assertTrue(isUserLoggedIn());

    }

}
