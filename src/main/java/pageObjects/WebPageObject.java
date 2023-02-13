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

    @FindBy(xpath = "//*[@id='rso']/*")
    private List<WebElement> resultSearch;

    public WebElement getFieldSearch() {
        return fieldSearch;
    }

    public boolean resultContainsText(String search) {
        for (WebElement result : resultSearch) {
            String text = result.getText();
            if (text.contains(search)) {
                return true;
            }
        }
        return false;
    }

}
