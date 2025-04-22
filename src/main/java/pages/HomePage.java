package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
    private WebDriver driver;
    
    @FindBy(linkText = "Company")
    private WebElement companyMenu;

    @FindBy(id = "wt-cli-accept-all-btn")
    private WebElement acceptAllButton;
    
    @FindBy(linkText = "Careers")
    private WebElement careersLink;
    
    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    
    public void open() {
        driver.get("https://useinsider.com/");
    }
    
    public boolean isOpened() {
        return driver.getTitle().contains("Insider");
    }

    public void clickAcceptAll(){
        acceptAllButton.click();
    }
    
    public CareerPage navigateToCareerPage() {
        companyMenu.click();
        careersLink.click();
        return new CareerPage(driver);
    }
}