package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;


public class CalculatorPage {

    private final AndroidDriver driver;
    private final WebDriverWait wait;

    private static final String PACKAGE = "apps.r.calculator:id/";

    public CalculatorPage(AndroidDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void pressDigit(int digit) {
        if (digit < 0 || digit > 9) {
            throw new IllegalArgumentException("Digit must be between 0 and 9, got: " + digit);
        }
        By locator = By.id(PACKAGE + "digit_" + digit);
        clickWhenReady(locator);
    }

    
    public void pressPlus() {
        clickWhenReady(By.id(PACKAGE + "op_add"));
    }

    public void pressMinus() {
        clickWhenReady(By.id(PACKAGE + "op_sub"));
    }


    public void pressMultiply() {
        clickWhenReady(By.id(PACKAGE + "op_mul"));
    }


    public void pressDivide() {
        clickWhenReady(By.id(PACKAGE + "op_div"));
    }


    public void pressEquals() {
        clickWhenReady(By.id(PACKAGE + "eq"));
    }

   
    public String getResult() {
        WebElement resultElement = wait.until(
                d -> d.findElement(By.id(PACKAGE + "result"))
        );
        return resultElement.getText().trim();
    }

    private void clickWhenReady(By locator) {
        WebElement element = wait.until(d -> d.findElement(locator));
        element.click();
    }
}
