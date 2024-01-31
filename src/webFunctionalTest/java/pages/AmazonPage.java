package webFunctionalTest.java.pages;

import webFunctionalTest.java.api.util.CommonActions;
import org.openqa.selenium.*;

import java.util.List;

public class AmazonPage {
    WebDriver driver =null;

    private CommonActions commonActions;
    public AmazonPage(WebDriver driver, CommonActions commonActions) {
        this.driver = driver;
        this.commonActions = commonActions;
    }
    // Locators
    By searchBoxLocator = By.id("twotabsearchtextbox");
    By searchButtonLocator = By.xpath("//input[@value='Go']");
    By productsLocator = By.cssSelector("div[data-asin]");
    By acceptCookieLocator = By.id("sp-cc-accept");
    public By productTitleLocator = By.cssSelector(".s-result-item h2 span");

    // Methods
    public void navigatingHomePage(){
        commonActions.navigateToUrl("https://www.amazon.com.tr/");
    }

    public void acceptCookieForAmazon(){
        commonActions.acceptCookies(acceptCookieLocator);
    }
    public void searchProduct(String productName) {
        // Arama kutusuna ürün adını yazın
        driver.findElement(searchBoxLocator).sendKeys(productName);

        // Enter tuşuna basarak aramayı başlatın
        driver.findElement(searchBoxLocator).sendKeys(Keys.RETURN);
    }

    public void clickSearchButton() {
        // Arama butonuna tıklayın
        driver.findElement(searchButtonLocator).click();
    }

    public boolean areProductsDisplayed(){
        List<WebElement> products = driver.findElements(productsLocator);
        return !products.isEmpty();
    }
    public void maximizeWindow(){
        driver.manage().window().maximize();
    }
    public void amazonScrollDown(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,350)", "");
    }
}
