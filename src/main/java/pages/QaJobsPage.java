package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import java.util.List;

public class QaJobsPage {
    private WebDriver driver;
    
    @FindBy(id = "location-filter")
    private WebElement locationFilter;
    
    @FindBy(id = "department-filter")
    private WebElement departmentFilter;
    
    @FindBy(css = ".job-list .job-title")
    private List<WebElement> jobTitles;
    
    @FindBy(css = ".job-list .department")
    private List<WebElement> departments;
    
    @FindBy(css = ".job-list .location")
    private List<WebElement> locations;
    
    @FindBy(css = ".job-list .view-role:first-child")
    private WebElement firstViewRoleButton;
    
    public QaJobsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    
    public void filterJobsByLocationAndDepartment(String location, String department) {
        new Select(locationFilter).selectByVisibleText(location);
        new Select(departmentFilter).selectByVisibleText(department);
    }
    
    public boolean isJobsListDisplayed() {
        return !jobTitles.isEmpty();
    }
    
    public boolean verifyAllPositionsContain(String text) {
        return jobTitles.stream().allMatch(e -> e.getText().contains(text));
    }
    
    public boolean verifyAllDepartmentsContain(String text) {
        return departments.stream().allMatch(e -> e.getText().contains(text));
    }
    
    public boolean verifyAllLocationsContain(String text) {
        return locations.stream().allMatch(e -> e.getText().contains(text));
    }
    
    public void clickFirstViewRoleButton() {
        firstViewRoleButton.click();
    }
    
    public boolean isLeverApplicationFormOpened() {
        return driver.getCurrentUrl().contains("jobs.lever.co");
    }
}