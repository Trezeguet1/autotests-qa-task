package ui;

import pages.CareerPage;
import org.testng.ITestResult;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import pages.*;
import utils.DriverFactory;
import utils.TestUtils;
import static org.testng.Assert.*;

public class InsiderCareerTest {
    private WebDriver driver;
    
    @Parameters("browser")
    @BeforeClass
    public void setup(@Optional("chrome") String browser) {
        driver = DriverFactory.getDriver(browser);
        driver.manage().window().maximize();
    }
    
    @Test
    public void verifyQualityAssuranceRoleScenario() {
        var homePage = new HomePage(driver);
        homePage.open();
        homePage.clickAcceptAll();
        assertTrue(homePage.isOpened(), "Home page is not opened");

        var careerPage = homePage.navigateToCareerPage();
        assertTrue(careerPage.isOpened(), "Career page is not opened");
        assertTrue(careerPage.isLocationsBlockDisplayed(), "Locations block is not displayed");
        assertTrue(careerPage.isTeamsBlockDisplayed(), "Teams block is not displayed");
        assertTrue(careerPage.isLifeAtInsiderBlockDisplayed(), "Life at Insider block is not displayed");

        var qaJobsPage = careerPage.navigateToQaJobs();
        qaJobsPage.filterJobsByLocationAndDepartment("Istanbul, Turkey", "Quality Assurance");

        assertTrue(qaJobsPage.isJobsListDisplayed(), "Jobs list is not displayed");
        assertTrue(qaJobsPage.verifyAllPositionsContain("Quality Assurance"),
                "Not all positions contain 'Quality Assurance'");
        assertTrue(qaJobsPage.verifyAllDepartmentsContain("Quality Assurance"),
                "Not all departments contain 'Quality Assurance'");
        assertTrue(qaJobsPage.verifyAllLocationsContain("Istanbul, Turkey"),
                "Not all locations contain 'Istanbul, Turkey'");

        qaJobsPage.clickFirstViewRoleButton();
        assertTrue(qaJobsPage.isLeverApplicationFormOpened(),
                "Lever application form is not opened");
    }

    @AfterMethod
    public void takeScreenshot(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            System.out.println("❌ Test failed: " + result.getName());
            // можно вставить скриншот сюда
        }
    }
    
    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}