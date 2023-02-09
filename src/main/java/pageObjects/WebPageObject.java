package pageObjects;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class WebPageObject  {

    public WebPageObject(AppiumDriver appiumDriver) {
        PageFactory.initElements(appiumDriver, this);

    }

    @FindBy(xpath = "//input[@name='q']")
    public WebElement fieldSearch;

    @FindBy(xpath = "//div[@id=\"rso\"]/div[2]/div")
    WebElement resultSearch;

    public WebElement getFieldSearch() {
        return fieldSearch;
    }

    public WebElement getResultSearch() {
        return resultSearch;
    }

}
