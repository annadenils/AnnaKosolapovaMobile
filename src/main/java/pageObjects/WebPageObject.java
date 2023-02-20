package pageObjects;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;

public class WebPageObject  {

    public WebPageObject(AppiumDriver appiumDriver) {
        PageFactory.initElements(appiumDriver, this);

    }

    @FindBy(xpath = "//input[@name='q']")
    public WebElement fieldSearch;

    @FindBy(css = "div[id='rso'] div[role='heading'][aria-level='3']")
    private List<WebElement> resultSearch;

    @FindBy(xpath = "//button[@id='KByQx']")
    public WebElement downPopUpButton;
    @FindBy(xpath = "//button[@id='L2AGLb']")
    public WebElement cookiesButton;

    public WebElement getFieldSearch() {
        return fieldSearch;
    }

    public List<WebElement> getResultSearch() {
        return resultSearch;
    }

}
