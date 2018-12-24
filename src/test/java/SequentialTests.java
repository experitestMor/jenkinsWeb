import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;

public class SequentialTests {


    private String reportDirectory = "reports";
    private String reportFormat = "xml";
    private String testName = "Jenkins Android";
    private String accessKey = System.getenv("accessKey");
    protected AndroidDriver<AndroidElement> driver = null;

    DesiredCapabilities dc = new DesiredCapabilities();

    @BeforeMethod
    public void setUp() throws MalformedURLException {
        dc.setCapability("reportDirectory", reportDirectory);
        dc.setCapability("reportFormat", reportFormat);
        dc.setCapability("testName", testName);
        dc.setCapability("accessKey", accessKey);
        dc.setCapability("deviceQuery", "@os='android' and @category='PHONE'");
        dc.setCapability("app","cloud:com.experitest.ExperiBank/.LoginActivity");
        dc.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.experitest.ExperiBank");
        dc.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, ".LoginActivity");
        dc.setCapability("instrumentApp", true);
        driver = new AndroidDriver<>(new URL("https://sales.experitest.com:443/wd/hub"), dc);
        driver.setLogLevel(Level.INFO);
    }

    @Test
    public void testUntitled() {
        driver.findElement(By.xpath("//*[@hint='Username']")).click();
        new WebDriverWait(driver, 60).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@hint='Username']")));
        driver.findElement(By.xpath("//*[@hint='Username']")).click();
        new WebDriverWait(driver, 120).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@hint='Username']")));
        driver.findElement(By.xpath("//*[@hint='Username']")).sendKeys("company");
        new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@hint='Password']")));
        driver.findElement(By.xpath("//*[@hint='Password']")).sendKeys("company");
        driver.findElement(By.xpath("//*[@text='Login']")).click();
        driver.findElement(By.xpath("//*[@text='Make Payment']")).click();
        driver.findElement(By.xpath("//*[@text='Cancel']")).click();
        driver.findElement(By.xpath("//*[@text='Expense Report']")).click();
        driver.findElement(By.xpath("//*[@text='Back']")).click();

        driver.findElement(By.xpath("//*[@text='Logout']")).click();
        driver.pressKeyCode(AndroidKeyCode.HOME);
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}