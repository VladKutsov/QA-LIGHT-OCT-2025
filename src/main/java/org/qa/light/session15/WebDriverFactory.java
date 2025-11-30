package org.qa.light.session15;

import lombok.SneakyThrows;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;

public class WebDriverFactory {

    private static WebDriver activeDriver;

    @SneakyThrows
    public static WebDriver getDriver() {
        if (activeDriver != null) {
            return activeDriver;
        }

        EnvType envType = EnvType.valueOf(
                System.getProperty("env.type", "LOCAL_DEFAULT"));

        switch (envType) {
            case LOCAL_DEFAULT:
                activeDriver = new ChromeDriver();
                break;
            case REMOTE_DEFAULT:
                activeDriver = new RemoteWebDriver(
                        new URL("http://localhost:4444/"),
                        new ChromeOptions());
                break;
            case JENKINS_CHROME:
                activeDriver = new RemoteWebDriver(
                        new URL("http://selenium-hub:4444/"),
                        new ChromeOptions());
                break;
            default:
                System.err.println("NOT IMPLEMENTED YET, FALLBACK TO LOCAL_CHROME");
                activeDriver = new ChromeDriver();
        }
        return activeDriver;
    }
}
