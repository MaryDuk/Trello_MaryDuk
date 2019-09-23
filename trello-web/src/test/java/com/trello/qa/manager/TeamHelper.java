package com.trello.qa.manager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.Assertion;

public class TeamHelper extends HelperBase {

    public Assertion Assert;

    public TeamHelper(WebDriver driver) {
        super(driver);
    }

    public void fillTeamCreationForm(TeamData team) {
        type(By.cssSelector("[data-test-id='header-create-team-name-input']"), team.getTeamName());
        type(By.cssSelector("textarea"), team.getDescription());
    }

    public void selectCreateTeamFromDropDown() {
        click(By.cssSelector("[data-test-id='header-create-team-button']"));
    }

    public String getTeamNameFromTeamPage() {
        new WebDriverWait(driver, 20).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("h1")));

        return driver.findElement(By.cssSelector("h1")).getText();
    }

    public void deleteTeam() {
        new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.cssSelector(".quiet-button")));
        click(By.cssSelector(".quiet-button"));
        click(By.cssSelector(".js-confirm.full.negate"));
    }

    public void openSettings() {
        //new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.cssSelector(".icon-gear.icon-sm.OiX3P2i2J92XatType a message her")));
        //click(By.cssSelector(".icon-gear.icon-sm.OiX3P2i2J92XatType a message here"));
        waitForElementAndClick(By.xpath("//span[contains(text(),'Settings')]"), 30);
    }

    public void openSettings1() throws InterruptedException {
        Thread.sleep(5000);
        click(By.xpath("//*[@class='icon-gear icon-sm OiX3P2i2J92Xat']/../../.."));
    }


    public void clickOnFirstTeam() {
        click(By.xpath("//*[@class='_mtkwfAlvk6O3f']/../../..//li")); // clicks on first element
    }

    public void clickOnFirstTeam1() {
        //waitForElementAndClick(By.cssSelector("[data-test-id^='home-team-tab-section-']"), 20);
        //click(By.xpath("//*[@data-test-id='home-team-tab-name']"));
        click(By.cssSelector("[data-test-id^='home-team-tab-section-']"));
    }

    public void cleanTeams (){
        int count = getTeamsCount();
        while (count >5){
            clickOnFirstTeam();
            openSettings();
            deleteTeam();
            //returnToHomePage();
            refreshPage();
            count = getTeamsCount();
            System.out.println(count);
        }
    }

    public void initEditTeamProfile() {
        click(By.cssSelector(".js-edit-profile"));
       // waitForElementAndClick(By.cssSelector(".js-edit-profile"), 30);
    }

    public void changeTeamProfile(String name, String description) {
        type(By.name("displayName"), name);
        type(By.name("desc"), description);


    }

    public void confirmEditTeam() {
        click(By.cssSelector(".js-submit-profile"));
    }
}
