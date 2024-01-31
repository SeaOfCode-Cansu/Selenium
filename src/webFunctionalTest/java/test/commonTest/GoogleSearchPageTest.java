package webFunctionalTest.java.test.commonTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import webFunctionalTest.java.pages.GoogleSearchPageObjects;

public class GoogleSearchPageTest {
    public static WebDriver driver =null;

    @BeforeTest
    public void setUpTest(){
         WebDriverManager.chromedriver().setup();
         driver = new ChromeDriver();
     }
   /* public static void main(String[] args) {
        googleSearchTest();
    }
*/
    @Test
    public void googleSearchTest(){

        GoogleSearchPageObjects searchPageObj= new GoogleSearchPageObjects(driver);

        driver.get("https://google.com");

        searchPageObj.setTextInSearchBox("A B C D");
        searchPageObj.clickSearchButton();

        driver.close();
    }

    @AfterTest
    public void tearDownTest(){
        //close browser
        driver.quit();
        System.out.println("Test completed successfully");

    }
}
