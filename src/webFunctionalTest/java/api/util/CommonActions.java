package webFunctionalTest.java.api.util;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CommonActions {
    WebDriver driver = null;

    public CommonActions(WebDriver driver) {
        this.driver = driver;
    }

    // Clicking an element
    public void click(By locator) {
        driver.findElement(locator).click();
    }

    // Fill the element with text
    public void typeText(By locator, String text) {
        WebElement element = driver.findElement(locator);
        element.clear();
        element.sendKeys(text);
    }

    // Pressing Enter
    public void pressEnter(By locator) {
        driver.findElement(locator).sendKeys(Keys.RETURN);
    }

    // Scrolling up the page
    public void scrollUp() {
        Actions actions = new Actions(driver);
        actions.keyDown(Keys.CONTROL).sendKeys(Keys.ARROW_UP).perform();
    }

    // Scrolling down the page
    public void scrollDown() {
        Actions actions = new Actions(driver);
        actions.keyDown(Keys.CONTROL).sendKeys(Keys.ARROW_DOWN).perform();
    }

    // Wait a specific seconds
    public void wait(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void handleAlertAccept() {
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }

    //Navigating a page
    public void navigateToUrl(String url) {
        driver.get(url);
    }

    //Accept Cookie preferences
    public void acceptCookies(By locator) {

        try {
            WebElement acceptButton = driver.findElement(locator);

            if (acceptButton.isDisplayed() && acceptButton.isEnabled()) {
                acceptButton.click();
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
                wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
            }
        } catch (Exception e) {
            System.out.println("The cookie acceptance button was not found or could not be clicked.");
        }
    }

}
