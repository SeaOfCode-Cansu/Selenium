package webFunctionalTest.java.test.commonTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ExtendReportsBasicDemo {
    private static WebDriver driver =null;

    public static void main(String[] args) {
        ExtentSparkReporter spark = new ExtentSparkReporter("Spark.html");
        ExtentReports extent = new ExtentReports();

        ExtentTest test1 = extent.createTest("MyFirstTest","This is a test to validate Google search functionality");

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        test1.log(Status.INFO,"Starting test case");

        driver.get("https://google.com");
        test1.pass("Navigated to google.com");

        driver.findElement(By.name("q")).sendKeys("Automation Step by Step");
        test1.pass("Entered text in searchbox");

        driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
        test1.pass("Press keyboard enter key");

        driver.close();
        test1.pass("Closed the browser.");
        driver.quit();
        test1.info("Test completed");

        extent.flush();
    }
}
