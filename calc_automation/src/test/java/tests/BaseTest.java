package tests;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import pages.CalculatorPage;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * BaseTest: handles everything common to all test classes -
 * starting the Appium session before tests run, and cleanly
 * quitting it afterwards. Test classes extend this so they only
 * need to focus on actual test logic, not driver setup.
 */
public class BaseTest {

    protected AndroidDriver driver;
    protected CalculatorPage calculatorPage;

    private static final String APPIUM_SERVER_URL = "http://127.0.0.1:4723";

    // Update this if your emulator's AVD name is different
    private static final String DEVICE_NAME = "Pixel_10";

    // Confirmed via Appium Inspector against the installed calculator app
    private static final String APP_PACKAGE = "apps.r.calculator";
    private static final String APP_ACTIVITY = "apps.r.calculator.Theme.LightGreen";

    @BeforeClass
    public void setUp() throws MalformedURLException {
        UiAutomator2Options options = new UiAutomator2Options();
        options.setPlatformName("Android");
        options.setDeviceName(DEVICE_NAME);
        options.setAppPackage(APP_PACKAGE);
        options.setAppActivity(APP_ACTIVITY);
        // noReset = true: don't reinstall/clear app data between runs, app is already present
        options.setNoReset(true);

        driver = new AndroidDriver(new URL(APPIUM_SERVER_URL), options);
        calculatorPage = new CalculatorPage(driver);

        System.out.println("[SETUP] Appium session started successfully.");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            System.out.println("[TEARDOWN] Appium session closed.");
        }
    }
}
