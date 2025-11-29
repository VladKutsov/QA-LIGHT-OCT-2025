package org.qa.light.session15;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.openqa.selenium.WebDriver;
import org.qa.light.session14.steps.DBSteps;
import org.qa.light.session14.steps.DBStepsV2;
import org.qa.light.session14.steps.WebSteps;
import org.qa.light.session14.steps.elements.GeneralPageObject;
import org.qa.light.session8.CloudFlarePage;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.net.MalformedURLException;
import java.sql.Connection;
import java.sql.SQLException;

//TODO: homework for session 11 in cucumber

@CucumberOptions(
        glue = "org.qa.light.session14.steps",
        features = "src/test/resources/features/demo",
        plugin = {
                "pretty",
                "html:target/report.html",
                "json:target/Cucumber.json",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
        }
)
public class CucumberRunner extends AbstractTestNGCucumberTests {
    private final static WebDriverFactory driverFactory = new WebDriverFactory();
    private final static DBConnectionFactory connectionFactory = new DBConnectionFactory();

    private Connection conn;
    private WebDriver driver;

    @BeforeSuite
    public void beforeSuite() throws SQLException, MalformedURLException {
        driver = driverFactory.getDriver();
        WebSteps.driver = driver;
        WebSteps.generalPageObject = new GeneralPageObject(driver);
        WebSteps.cloudFlarePage = new CloudFlarePage(driver);

        conn = connectionFactory.getConnection();
        DBSteps.conn = conn;
        DBStepsV2.conn = conn;
    }

    @AfterSuite
    public void afterSuite() throws SQLException {
        driver.quit();
        conn.close();
    }
}
