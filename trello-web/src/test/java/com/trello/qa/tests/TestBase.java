package com.trello.qa.tests;
import com.trello.qa.manager.ApplicationManager;
import org.openqa.selenium.remote.BrowserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.*;

import java.lang.reflect.Method;
import java.util.Arrays;


public class TestBase {

    Logger logger = LoggerFactory.getLogger(TestBase.class);
    @BeforeMethod
    public void startLogger(Method m, Object []p){
        logger.info("start test" + m.getName() + "with parameters " + Arrays.asList(p));
    }
    @AfterMethod
    public void stopLogger(Method m){
        logger.info("stop test" + m.getName());
        System.out.println("-----------------------------------------------------------");
    }


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
