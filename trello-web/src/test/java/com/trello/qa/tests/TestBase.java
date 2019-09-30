package com.trello.qa.tests;
import com.trello.qa.manager.ApplicationManager;
import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.*;


public class TestBase {
    protected static ApplicationManager app = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));

    @BeforeSuite
public void setUp() throws InterruptedException {
    app.init();

}

    @AfterSuite
public void tearDown (){
        app.stop();
    }


}
