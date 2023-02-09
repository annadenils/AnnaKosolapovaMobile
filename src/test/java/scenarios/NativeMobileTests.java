package scenarios;

import org.testng.Assert;
import org.testng.annotations.Test;
import setup.BaseTest;

import static org.assertj.core.api.Assertions.assertThat;

public class NativeMobileTests extends BaseTest {
    String email = System.getenv("email");
    String userName = System.getenv("userName");
    String password = System.getenv("password");

    @Test(description = "This simple test just click on the Sign In button")
    public void simpleNativeTest() throws IllegalAccessException, NoSuchFieldException, InstantiationException {
        getPo().getWebElement("signInButton").click();
        System.out.println("Simplest Android native test done");
    }

    @Test(groups = "native", description = "Check correct registration in app", priority = 1)
    public void registrationNativeTest() throws NoSuchFieldException, IllegalAccessException, InstantiationException {
        getPo().getWebElement("registerButton").click();
        Assert.assertEquals(getPo().getWebElement("frameLayout").getText(), "Registration");
        getPo().getWebElement("registrationEmail").sendKeys(email);
        getPo().getWebElement("registrationUserName").sendKeys(userName);
        getPo().getWebElement("registrationPassword").sendKeys(password);
        getPo().getWebElement("confirmRegistrationPassword").sendKeys(password);
        getPo().getWebElement("registerNewAccButton").click();
        Assert.assertTrue(getPo().getWebElement("registerButton").isDisplayed());
        Assert.assertEquals(getPo().getWebElement("frameLayout").getText(), "EPAM Test App");
    }

    @Test(groups = "native", description = "Check login in app", priority = 2)
    public void loginNativeTest() throws NoSuchFieldException, IllegalAccessException, InstantiationException {
        Assert.assertEquals(getPo().getWebElement("frameLayout").getText(), "EPAM Test App");
        getPo().getWebElement("loginUserName").sendKeys(email);
        getPo().getWebElement("loginPassword").sendKeys(password);
        getPo().getWebElement("signInButton").click();
        Assert.assertTrue(getPo().getWebElement("newExpenseButton").isDisplayed());
        Assert.assertEquals(getPo().getWebElement("budgetFrameLayout").getText(), "BudgetActivity");
    }
}
