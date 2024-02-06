package webFunctionalTest.java.test.commonTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import webFunctionalTest.java.api.util.CommonActions;
import webFunctionalTest.java.pages.AmazonPage;
import webFunctionalTest.java.pages.ProductPage;

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
    }

    @Test
    public void testProductSearch() {
        productPage.searchForProduct();
        productPage.clickApple();

        // Assert product image is showing
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement productImage = wait.until(ExpectedConditions.visibilityOfElementLocated(productPage.productImage));
        Assert.assertTrue(productImage.isDisplayed());
    }

    @Test
    public void verifyUElements() {
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
    }

    @AfterClass
    public void teardown() {
        driver.quit();
    }
}
