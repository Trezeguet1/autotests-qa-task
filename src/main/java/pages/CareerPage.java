package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.DriverUtils;

public class CareerPage {
    private WebDriver driver;
    
    @FindBy(id = "career-our-location")
    private WebElement locationsBlock;
    
    @FindBy(id = "career-find-our-calling")
    private WebElement teamsBlock;
    
    @FindBy(xpath = "//section[.//h2[text()='Life at Insider']]")
    private WebElement lifeAtInsiderBlock;

    @FindBy(xpath = "//section[@id='career-find-our-calling']//a[text()='See all teams']")
    private WebElement seeAllTeamsButton;

    @FindBy(linkText = "Quality Assurance")
    private WebElement qaJobsLink;
    
    public CareerPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    
    public boolean isLocationsBlockDisplayed() {
        return locationsBlock.isDisplayed();
    }
    
    public boolean isTeamsBlockDisplayed() {
        return teamsBlock.isDisplayed();
    }
    
    public boolean isLifeAtInsiderBlockDisplayed() {
        return lifeAtInsiderBlock.isDisplayed();
    }
    
    public QaJobsPage navigateToQaJobs() {
        DriverUtils.waitUntilElementPresent(driver, By.xpath("//section[@id='career-find-our-calling']//a[text()='See all teams']"));
        DriverUtils.scrollIntoView(driver, seeAllTeamsButton);
        DriverUtils.waitUntilElementDisplayed(driver, seeAllTeamsButton);
        DriverUtils.scrollBy(driver, -100);
        DriverUtils.waitUntilElementVisible(driver, seeAllTeamsButton);
        seeAllTeamsButton.click();
        DriverUtils.waitUntilElementDisplayed(driver, qaJobsLink);
        DriverUtils.scrollIntoView(driver, qaJobsLink);
        DriverUtils.waitUntilElementDisplayed(driver, qaJobsLink);
        DriverUtils.scrollBy(driver, -100);
        DriverUtils.waitUntilElementVisible(driver, qaJobsLink);
        qaJobsLink.click();
        return new QaJobsPage(driver);
    }

    public boolean isOpened() {
        System.out.println(">>>>> CALLED isOpened() FROM CareerPage");
        return driver.getCurrentUrl().contains("/careers");
    }
}