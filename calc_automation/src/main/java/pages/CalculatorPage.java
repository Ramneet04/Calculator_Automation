package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

/**
 * Page Object for the Calculator app.
 * Every button/screen-element interaction is defined here as a method,
 * so test classes never talk to raw locators directly (Page Object Model).
 */
public class CalculatorPage {

    private final AndroidDriver driver;
    private final WebDriverWait wait;

    // ---- Locators (resource-ids found via Appium Inspector) ----
    private static final String PACKAGE = "apps.r.calculator:id/";

    public CalculatorPage(AndroidDriver driver) {
        this.driver = driver;
        // Explicit wait: gives the app up to 10 seconds to render elements
        // before failing - protects against timing/flakiness on slower emulators.
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    /**
     * Presses a single digit button (0-9).
     * @param digit the digit to press, e.g. 7
     */
    public void pressDigit(int digit) {
        if (digit < 0 || digit > 9) {
            throw new IllegalArgumentException("Digit must be between 0 and 9, got: " + digit);
        }
        By locator = By.id(PACKAGE + "digit_" + digit);
        clickWhenReady(locator);
    }

    /** Presses the addition (+) button. */
    public void pressPlus() {
        clickWhenReady(By.id(PACKAGE + "op_add"));
    }

    /** Presses the subtraction (-) button. */
    public void pressMinus() {
        clickWhenReady(By.id(PACKAGE + "op_sub"));
    }

    /** Presses the multiplication (x) button. */
    public void pressMultiply() {
        clickWhenReady(By.id(PACKAGE + "op_mul"));
    }

    /** Presses the division (/) button. */
    public void pressDivide() {
        clickWhenReady(By.id(PACKAGE + "op_div"));
    }

    /** Presses the equals (=) button to compute the result. */
    public void pressEquals() {
        clickWhenReady(By.id(PACKAGE + "eq"));
    }

    /**
     * Reads the text currently shown in the result display.
     * @return the displayed result as a String, e.g. "10"
     */
    public String getResult() {
        WebElement resultElement = wait.until(
                d -> d.findElement(By.id(PACKAGE + "result"))
        );
        return resultElement.getText().trim();
    }

    /**
     * Shared helper: waits for an element to be present/clickable, then clicks it.
     * Centralizing this avoids repeating wait logic in every press-method above.
     */
    private void clickWhenReady(By locator) {
        WebElement element = wait.until(d -> d.findElement(locator));
        element.click();
    }
}
