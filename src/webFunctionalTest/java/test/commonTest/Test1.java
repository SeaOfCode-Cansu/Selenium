package webFunctionalTest.java.test.commonTest;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;

public class Test1 {
    public static void main(String[] args) {
        googleSearch();
    }

    public static void googleSearch(){
        WebDriverManager.chromedriver().setup();
        ChromeDriver driver = new ChromeDriver();

        //goto google.com
        driver.get("https://google.com");

        //enter text in search box
        driver.findElement(By.name("q")).sendKeys("Automation Step by Step");

        //click on search button
        driver.findElement(By.name("q")).sendKeys(Keys.ENTER);

        //close browser
        driver.close();

        System.out.println("Test completed");



    }

}
