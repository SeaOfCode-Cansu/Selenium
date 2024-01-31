package webFunctionalTest.java.test.commonTest;

import webFunctionalTest.java.api.util.CommonActions;
import webFunctionalTest.java.pages.AmazonPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class AmazonTest {
    public static WebDriver driver;
    CommonActions commonActions;
    AmazonPage amazonPage;

    @BeforeTest
    public void setUpTest() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        commonActions = new CommonActions(driver);
        amazonPage = new AmazonPage(driver, commonActions);
    }

    @Test
    public void AmazonProductSearchTest() {
        amazonPage.navigatingHomePage();
        amazonPage.acceptCookieForAmazon();
        amazonPage.searchProduct("Laptop");

        //Check that search results are displayed
        Assert.assertTrue(amazonPage.areProductsDisplayed(), "Search results are not displayed.");
    }

    @Test
    public void verifyProductDetailsInSearchResults() {
        amazonPage.navigatingHomePage();
        amazonPage.acceptCookieForAmazon();
        amazonPage.searchProduct("Laptop");
        amazonPage.maximizeWindow();
        amazonPage.amazonScrollDown();

        List<WebElement> productResults = driver.findElements(By.cssSelector(".s-result-item"));
        Assert.assertTrue(productResults.size() > 0, "No search results found.");

        for (WebElement product : productResults) {
            // Price Check
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            WebElement priceElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".a-price")));
            Assert.assertNotNull(priceElement, "The product has no price");
            Assert.assertNotEquals(priceElement.getText(), "", "The price of the product is empty");

            //Image Check
            WebElement imageElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".s-image")));
            Assert.assertNotNull(imageElement, "The product has no image");

            // Rating Check
            WebElement ratingElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".a-icon-alt")));
            Assert.assertNotNull(ratingElement, "The product has no rating");
            Assert.assertNotEquals(ratingElement.getText(), "", "The product's rating is empty");
        }
    }

    @AfterClass
    public void teardown() {
        driver.quit();
    }

}
