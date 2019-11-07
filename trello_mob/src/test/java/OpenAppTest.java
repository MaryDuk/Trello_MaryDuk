import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class OpenAppTest {
AppiumDriver driver;
    @BeforeMethod
    public void setUp() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability( "deviceName", "qa21");
        capabilities.setCapability("platformVersion", "9.0");
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("appPackage", "com.trello");
        capabilities.setCapability("appActivity", ".home.HomeActivity");
        capabilities.setCapability("app", "C:/Tools/Trello_new.apk"); // needs to be moved to resoucres

driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);



    }

    @Test
    public void openUpTest(){
        System.out.println("App opened");

    }

    @AfterMethod
    public void tearDown(){

    }
}

