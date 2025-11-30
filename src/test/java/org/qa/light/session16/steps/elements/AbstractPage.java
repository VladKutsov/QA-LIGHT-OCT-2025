package org.qa.light.session16.steps.elements;

import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;

public abstract class AbstractPage {

    protected final WebDriver driver;

    public AbstractPage(WebDriver driver) {
        this.driver = driver;
    }

    @SneakyThrows
    public File takeScreenShot(String filename) {
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File storeFile = new File("target/" + filename);
        if (storeFile.exists()) {
            storeFile.delete();
        }
        FileUtils.copyFile(scrFile, storeFile);
        return storeFile;
    }
}
