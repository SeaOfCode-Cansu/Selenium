package webFunctionalTest.java.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import webFunctionalTest.java.api.util.CommonActions;

import java.time.Duration;
import java.util.List;

public class ProductPage {
    WebDriver driver = null;

    private CommonActions commonActions;
    private AmazonPage amazonPage;

    public ProductPage(WebDriver driver, CommonActions commonActions, AmazonPage amazonPage) {
        this.driver = driver;
        this.commonActions = commonActions;
        this.amazonPage = amazonPage;
    }
    // Locators

    By appleProduct = By.xpath("(//img[@class='s-image'])[1]");
    public By productImage = By.cssSelector("#landingImage");
    public By productName = By.id("productTitle");
    public By productPrice = By.xpath("//span[@class='a-price-whole']");
    public By productRate = By.id("acrPopover");
    public By btnAddCard = By.id("add-to-cart-button");

    // Methods
    public void searchForProduct() {
        amazonPage.navigatingHomePage();
        amazonPage.acceptCookieForAmazon();
        amazonPage.searchProduct("Laptop");
        commonActions.maximixeScreen();
    }

    public void clickApple() {
        // Click on the first product on the results page
        amazonPage.amazonScrollDown();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement product = wait.until(ExpectedConditions.visibilityOfElementLocated(appleProduct));
        product.click();
    }
}
