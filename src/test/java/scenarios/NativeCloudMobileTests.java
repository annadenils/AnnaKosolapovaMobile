package scenarios;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import setup.BaseTest;
import java.util.Objects;

public class NativeCloudMobileTests extends BaseTest {
    public static String email = RandomStringUtils.random(10,true,false) +"@gmail.com";
    public static String userName = RandomStringUtils.random(10,true,false);
    public static String password = RandomStringUtils.random(8,true,true);

    @Parameters("platformName")
    @Test(groups = "native", description = "Check correct registration in app", priority = 1)
    public void registrationCloudMobileTest(String platformName) throws NoSuchFieldException, IllegalAccessException, InstantiationException {
        getPo().getWebElement("registerButton").click();
        if (Objects.equals(platformName, "iOs")) {
            Assert.assertEquals(getPo().getWebElement("registrationFrameLayout").getText(),
                    "Registration");
        }
        else {
            Assert.assertEquals(getPo().getWebElement("frameLayout").getText(), "Registration");
        }
        getPo().getWebElement("registrationEmail").sendKeys(email);
        getPo().getWebElement("registrationUserName").sendKeys(userName);
        getPo().getWebElement("registrationPassword").sendKeys(password);
        getPo().getWebElement("confirmRegistrationPassword").sendKeys(password);
        if (Objects.equals(platformName, "iOs")) {
            getPo().getWebElement("aggrementButton").click();
//            getPo().getWebElement("registerNewAccButton").click();
        }
        getPo().getWebElement("registerNewAccButton").click();
//        Assert.assertTrue(getPo().getWebElement("registerButton").isDisplayed());
        Assert.assertEquals(getPo().getWebElement("frameLayout").getText(), "EPAM Test App");
        getPo().getWebElement("loginUserName").sendKeys(email);
        getPo().getWebElement("loginPassword").sendKeys(password);
        getPo().getWebElement("signInButton").click();
        Assert.assertTrue(getPo().getWebElement("newExpenseButton").isDisplayed());
        if (Objects.equals(platformName, "iOs")) {
            Assert.assertEquals(getPo().getWebElement("budgetFrameLayout").getText(), "Budget");
        }
        else {
            Assert.assertEquals(getPo().getWebElement("budgetFrameLayout").getText(), "BudgetActivity");
        }
    }
}
