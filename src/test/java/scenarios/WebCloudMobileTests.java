package scenarios;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pageObjects.WebPageObject;
import setup.BaseTest;

import java.util.List;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

public class WebCloudMobileTests extends BaseTest {


    @Test(groups = "web", description = "Test about searching EPAM on google page")
    public void googleCloudWebTest(String platformName) {
        SoftAssert softAssert = new SoftAssert();
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
        int resultSize = webPageObject.getResultSearch().size();
        assertThat(resultSize).isNotZero();
        List<WebElement> resultSearch = webPageObject.getResultSearch();
        for (WebElement resultSearchItem : resultSearch) {
            softAssert.assertTrue(resultSearchItem.getText().contains("EPAM"));
        }
        softAssert.assertAll();
    }
}
