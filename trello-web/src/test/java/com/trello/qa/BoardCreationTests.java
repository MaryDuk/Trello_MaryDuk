package com.trello.qa;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BoardCreationTests extends TestBase{

    @Test
    public void testTeamCreation() throws InterruptedException {
        Assert.assertTrue(isUserLoggedIn());
        createBoard("First Board");
    }

    public void createBoard (String title) throws InterruptedException {
        click(By.cssSelector("[class='board-tile mod-add']"));
        type(By.cssSelector("[placeholder='Add board title']"),title);
        click(By.cssSelector("[class='icon-sm icon-overflow-menu-horizontal']"));
        click(By.xpath("//li[5]//div[1]//div[1]"));
        click(By.xpath("//span[@class='icon-sm icon-down subtle-chooser-trigger-dropdown-icon light']"));
        click(By.xpath("//span[contains(text(),'Private')]"));
        click(By.xpath("//span[contains(text(),'Create Board')]"));
        Thread.sleep(5000);
    }
}
