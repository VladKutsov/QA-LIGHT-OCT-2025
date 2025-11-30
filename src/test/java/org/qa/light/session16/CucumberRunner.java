package org.qa.light.session16;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.openqa.selenium.WebDriver;
import org.qa.light.session15.DBConnectionFactory;
import org.qa.light.session15.WebDriverFactory;
import org.qa.light.session16.listener.MyListener;
import org.qa.light.session16.steps.DBSteps;
import org.qa.light.session16.steps.DBStepsV2;
import org.qa.light.session16.steps.WebSteps;
import org.qa.light.session16.steps.elements.GeneralPageObject;
import org.qa.light.session8.CloudFlarePage;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

import java.sql.Connection;
import java.sql.SQLException;

//TODO: Write cucumber scenario, which will search for random device from DB
//TODO: Search for that random device
//TODO: If device price did not change - test passes
//TODO: If device pice HAS changed - test fails, and update price in DB

@CucumberOptions(
        tags = "@wip",
        glue = "org.qa.light.session16.steps",
        features = "src/test/resources/features/demo",
        plugin = {
                "pretty",
                "html:target/report.html",
                "json:target/Cucumber.json",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
//                , "org.qa.light.session16.CucumberHooks"
        }
)
@Listeners(MyListener.class)
public class CucumberRunner extends AbstractTestNGCucumberTests {
    private final static WebDriverFactory driverFactory = new WebDriverFactory();
    private final static DBConnectionFactory connectionFactory = new DBConnectionFactory();

    private Connection conn;
    private WebDriver driver;

    @BeforeSuite
    public void beforeSuite() {
        driver = driverFactory.getDriver();
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
