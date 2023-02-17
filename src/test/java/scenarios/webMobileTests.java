package scenarios;

import static org.assertj.core.api.Assertions.assertThat;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pageObjects.WebPageObject;
import setup.BaseTest;
import java.util.List;

public class webMobileTests extends BaseTest {

    @Test(description = "Make sure that we've opened IANA homepage")
    public void simpleWebTest() throws InterruptedException {
        getDriver().get("http://iana.org"); // open IANA homepage

        // Make sure that page has been loaded completely
        new WebDriverWait(getDriver(), 10).until(
                wd -> ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete")
        );

        // Check IANA homepage title
        assert ((WebDriver) getDriver()).getTitle().equals("Internet Assigned Numbers Authority") : "This is not IANA homepage";

        // Log that test finished
        System.out.println("Site opening done");
    }

    @Test(groups = "web", description = "Web test case")
    public void googleWebTest() {
        SoftAssert softAssert = new SoftAssert();
        getDriver().get("http://google.com");
        new WebDriverWait(getDriver(), 10).until(
                wd -> ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete")
        );
        WebPageObject webPageObject = new WebPageObject(getDriver());
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
