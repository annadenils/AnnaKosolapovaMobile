package scenarios;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.WebPageObject;
import setup.BaseTest;
import java.util.Objects;

public class WebCloudMobileTests extends BaseTest {


    @Test(groups = "web", description = "Test about searching EPAM on google page")
    public void googleCloudWebTest(String platformName) {
        new WebDriverWait(getDriver(), 10).until(
                wd -> ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));
        getDriver().get("http://google.com");
        WebPageObject webPageObject = new WebPageObject(getDriver());
        if (Objects.equals(platformName, "Android")) {
            webPageObject.downPopUpButton.click();
            webPageObject.downPopUpButton.click();
            webPageObject.cookiesButton.click();
        }
        webPageObject.getFieldSearch().sendKeys("EPAM" + Keys.ENTER);
        Assert.assertTrue(webPageObject.resultContainsText("EPAM"));
    }
}
