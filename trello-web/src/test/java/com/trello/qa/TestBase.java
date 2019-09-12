package com.trello.qa;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class TestBase {
    protected static ApplicationManager app = new ApplicationManager();

    @BeforeClass
public void setUp(){
    app.init();

}

    @AfterClass
public void tearDown (){
        app.stop();
    }


}
