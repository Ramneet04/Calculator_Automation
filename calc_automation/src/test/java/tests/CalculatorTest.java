package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * CalculatorTest: automates the 4 basic arithmetic operations on the
 * Each test follows the same pattern:
 *   1. Press the first operand's digit(s)
 *   2. Press the operator
 *   3. Press the second operand's digit(s)
 *   4. Press equals
 *   5. Read the displayed result and assert it matches expectation
 */
public class CalculatorTest extends BaseTest {

    @Test(description = "Validates addition: 7 + 3 = 10")
    public void testAddition() {
        calculatorPage.pressDigit(7);
        calculatorPage.pressPlus();
        calculatorPage.pressDigit(3);
        calculatorPage.pressEquals();

        String actualResult = calculatorPage.getResult();
        Assert.assertEquals(actualResult, "10",
                "Addition failed: expected 7 + 3 to equal 10, but got " + actualResult);

        System.out.println("[PASS] Addition test: 7 + 3 = " + actualResult);
    }

    @Test(description = "Validates subtraction: 10 - 4 = 6")
    public void testSubtraction() {
        calculatorPage.pressDigit(1);
        calculatorPage.pressDigit(0);
        calculatorPage.pressMinus();
        calculatorPage.pressDigit(4);
        calculatorPage.pressEquals();

        String actualResult = calculatorPage.getResult();
        Assert.assertEquals(actualResult, "6",
                "Subtraction failed: expected 10 - 4 to equal 6, but got " + actualResult);

        System.out.println("[PASS] Subtraction test: 10 - 4 = " + actualResult);
    }

    @Test(description = "Validates multiplication: 5 x 6 = 30")
    public void testMultiplication() {
        calculatorPage.pressDigit(5);
        calculatorPage.pressMultiply();
        calculatorPage.pressDigit(6);
        calculatorPage.pressEquals();

        String actualResult = calculatorPage.getResult();
        Assert.assertEquals(actualResult, "30",
                "Multiplication failed: expected 5 x 6 to equal 30, but got " + actualResult);

        System.out.println("[PASS] Multiplication test: 5 x 6 = " + actualResult);
    }

    @Test(description = "Validates division: 20 / 4 = 5")
    public void testDivision() {
        calculatorPage.pressDigit(2);
        calculatorPage.pressDigit(0);
        calculatorPage.pressDivide();
        calculatorPage.pressDigit(4);
        calculatorPage.pressEquals();

        String actualResult = calculatorPage.getResult();
        Assert.assertEquals(actualResult, "5",
                "Division failed: expected 20 / 4 to equal 5, but got " + actualResult);

        System.out.println("[PASS] Division test: 20 / 4 = " + actualResult);
    }
}
