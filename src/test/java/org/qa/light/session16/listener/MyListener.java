package org.qa.light.session16.listener;

import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.qa.light.session15.WebDriverFactory;
import org.qa.light.session16.steps.elements.MyWebException;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;

import java.io.File;

public class MyListener implements IInvokedMethodListener {

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
        if (!testResult.isSuccess()) {
            if (testResult.getThrowable() instanceof MyWebException) {
                MyWebException myWebException = (MyWebException) testResult.getThrowable();
                takeScreenShot(myWebException.getDriver(), System.currentTimeMillis() + "-click.png");
            }
        }
    }

    @SneakyThrows
    public File takeScreenShot(String filename) {
        return takeScreenShot(WebDriverFactory.getDriver(), filename);
    }

    @SneakyThrows
    public File takeScreenShot(WebDriver driver, String filename) {
        File scrFile = ((TakesScreenshot) driver)
                .getScreenshotAs(OutputType.FILE);
        File storeFile = new File("target/" + filename);
        if (storeFile.exists()) {
            storeFile.delete();
        }
        FileUtils.copyFile(scrFile, storeFile);
        return storeFile;
    }
}