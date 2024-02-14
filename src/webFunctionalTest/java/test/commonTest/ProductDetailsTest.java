package webFunctionalTest.java.test.commonTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import webFunctionalTest.java.api.util.CommonActions;
import webFunctionalTest.java.pages.AmazonPage;
import webFunctionalTest.java.pages.ProductPage;
import webFunctionalTest.java.util.ScreenshotUtils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class ProductDetailsTest {
    public static WebDriver driver;
    CommonActions commonActions;
    AmazonPage amazonPage;
    ProductPage productPage;

    @BeforeTest
    public void setUpTest() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        commonActions = new CommonActions(driver);
        amazonPage = new AmazonPage(driver, commonActions);
        productPage = new ProductPage(driver, commonActions, amazonPage);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test(groups = "smoke")
    public void testProductSearch() {
        productPage.searchForProduct();
        productPage.clickApple();

        // Assert product image is showing
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement productImage = wait.until(ExpectedConditions.visibilityOfElementLocated(productPage.productImage));
        Assert.assertTrue(productImage.isDisplayed());
    }

    @Test(description = "This test is for product elements visibility on product search page", groups = "smoke")
    public void verifyElementsVisibility() {
        productPage.searchForProduct();
        productPage.clickApple();

        // Assert product name is showing
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement productName = wait.until(ExpectedConditions.visibilityOfElementLocated(productPage.productName));
        Assert.assertTrue(productName.isDisplayed());

        // Assert product price is showing
        WebElement productPrice = wait.until(ExpectedConditions.visibilityOfElementLocated(productPage.productPrice));
        Assert.assertTrue(productPrice.isDisplayed());

        // Assert product rate is showing
        WebElement productRate = wait.until(ExpectedConditions.visibilityOfElementLocated(productPage.productRate));
        Assert.assertTrue(productRate.isDisplayed());

        // Assert add to card button is showing
        WebElement addCardBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(productPage.btnAddCard));
        Assert.assertTrue(addCardBtn.isDisplayed());

        try {
            ScreenshotUtils.takeScreenshot(driver);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test(description = "This test is checking page title and elements' texts")
    public void verifyUIElements() {
        SoftAssert softassert = new SoftAssert();
        productPage.searchForProduct();
        productPage.clickApple();
        String actualPageTitle = driver.getTitle();
        softassert.assertEquals(actualPageTitle, productPage.expectedPageTitle,"Title verification failed.");
        String actualAddCardTxt = driver.findElement(productPage.expectedAddToCardTxt).getAttribute("value");
        //String actualAddCardTxt = driver.findElement(By.id("add-to-cart-button")).getAttribute("value");
        softassert.assertEquals(actualAddCardTxt,productPage.txtaddCard,"Add to card button text verification failed.");
        softassert.assertAll();
    }

    @Test
    public void skipTest(){
        System.out.println("Skipping this test");
        throw new SkipException("Skipping this test");
    }

    @AfterClass
    public void teardown() {
        driver.quit();
    }
}
