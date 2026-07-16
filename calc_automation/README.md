# Calculator Appium Automation (Java + TestNG + POM)

Automates the native Android calculator app (`apps.r.calculator`) on an emulator,
covering addition, subtraction, multiplication, and division.

## Project Structure

```
calculator-appium-automation/
├── pom.xml                                  <- Maven dependencies & build config
├── testng.xml                                <- TestNG suite definition
└── src/
    ├── main/java/pages/
    │   └── CalculatorPage.java              <- Page Object: all button interactions
    └── test/java/tests/
        ├── BaseTest.java                     <- Appium driver setup/teardown
        └── CalculatorTest.java               <- 4 test methods (add/sub/mul/div)
```

## Prerequisites (you already have these installed)

- JDK 17
- Android Studio + SDK + an emulator (AVD) booted, with the calculator app
  (package `apps.r.calculator`) already installed on it
- Node.js + Appium server + `uiautomator2` driver
- Maven

## Setup Steps

### 1. Extract this project
Unzip the provided project folder anywhere on your machine, e.g.:
```
C:\Users\DS\calculator-appium-automation\
```

### 2. Check the device name matches
Open `src/test/java/tests/BaseTest.java` and confirm this line matches your
actual AVD name (check in Android Studio's Device Manager):
```java
private static final String DEVICE_NAME = "Pixel_10";
```
If your AVD has a different name, update it here.

### 3. Boot your emulator
Open Android Studio -> Device Manager -> click Play next to your Pixel 10 AVD.
Wait until it's fully booted to the home screen.

### 4. Start the Appium server
In a Command Prompt window (leave it running):
```
appium
```
Wait for confirmation it's listening on `http://127.0.0.1:4723`.

### 5. Run the tests
Open a **new** Command Prompt, navigate into the project folder:
```
cd C:\Users\DS\calculator-appium-automation
mvn test
```

Maven will download any remaining dependencies on first run (may take a
few minutes), then execute all 4 tests against your emulator. You'll see
each button being pressed live on the emulator screen as the tests run.

## Expected Output

```
[SETUP] Appium session started successfully.
[PASS] Addition test: 7 + 3 = 10
[PASS] Subtraction test: 10 - 4 = 6
[PASS] Multiplication test: 5 x 6 = 30
[PASS] Division test: 20 / 4 = 5
[TEARDOWN] Appium session closed.

Tests run: 4, Failures: 0, Errors: 0, Skipped: 0

BUILD SUCCESS
```

A full HTML/XML report is also generated automatically at:
```
target/surefire-reports/
```

## How the code is organized (Page Object Model)

- **CalculatorPage.java** contains *only* the "how" of interacting with the
  app - pressDigit(), pressPlus(), pressEquals(), getResult(), etc. If a
  button's locator ever changes, you only fix it here.
- **CalculatorTest.java** contains *only* the "what" - the actual test
  scenarios (7+3=10, etc.) built by calling CalculatorPage's methods. It
  never touches raw locators directly.
- **BaseTest.java** handles starting and stopping the Appium session once,
  so each test class/method doesn't need to repeat that setup.

## Troubleshooting

| Problem | Likely Fix |
|---|---|
| `ANDROID_HOME not exported` error | Set `ANDROID_HOME` system environment variable, restart terminal |
| `Cannot start application` error | Re-check `APP_PACKAGE`/`APP_ACTIVITY` in BaseTest.java via `adb shell dumpsys window \| findstr mCurrentFocus` with the app open |
| Tests timeout waiting for elements | Confirm emulator is fully booted and Appium server shows no errors before running `mvn test` |
| `mvn test` can't find Appium server | Confirm the Appium server Command Prompt window is still open and running |
