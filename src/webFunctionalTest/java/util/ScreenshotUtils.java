package webFunctionalTest.java.util;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.devtools.v85.io.IO;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;


public class ScreenshotUtils {
    public static void takeScreenshot(WebDriver driver) throws IOException {
        TakesScreenshot screenshot = (TakesScreenshot) driver;
        //Set the file extension and save path
        String fileExtention = ".png";
        String savePath = "src/webFunctionalTest/java/util/screenShot/";

        // Get current date and time
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        String timestamp = dateFormat.format(new Date());

        try {
            FileUtils.copyFile(screenshot.getScreenshotAs(OutputType.FILE), new File(savePath +timestamp+ fileExtention));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}

