package org.qa.light.session15;

import lombok.SneakyThrows;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;

public class WebDriverFactory {

    @SneakyThrows
    public WebDriver getDriver() {
        EnvType envType = EnvType.valueOf(
                System.getProperty("env.type", "LOCAL_DEFAULT"));

        switch (envType) {
            case LOCAL_DEFAULT:
                return new ChromeDriver();
            case REMOTE_DEFAULT:
                return new RemoteWebDriver(
                        new URL("http://localhost:4444/"),
                        new ChromeOptions());
            case JENKINS_CHROME:
                new RemoteWebDriver(
                        new URL("http://selenium-hub:4444/"),
                        new ChromeOptions());
            default:
                System.err.println("NOT IMPLEMENTED YET, FALLBACK TO LOCAL_CHROME");
                return new ChromeDriver();
        }
    }
}
