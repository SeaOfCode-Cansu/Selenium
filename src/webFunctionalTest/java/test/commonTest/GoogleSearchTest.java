package webFunctionalTest.java.test.commonTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import webFunctionalTest.java.pages.GoogleSearchPage;

public class GoogleSearchTest {

    private static WebDriver driver = null;

    public static void main(String[] args) {
        googleSearch();
    }

    public static void googleSearch() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        //goto google.com
        driver.get("https://google.com");

        //enter text in search box
        GoogleSearchPage.textbox_search(driver).sendKeys("Automation Step by Step");

        //click on search button
        GoogleSearchPage.button_search(driver).sendKeys(Keys.ENTER);

        //close browser
        driver.close();

        System.out.println("Test completed");


    }

}
