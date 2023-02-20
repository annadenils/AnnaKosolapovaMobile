package setup;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;
import pageObjects.PageObject;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

import static java.lang.String.format;

public class BaseTest implements IDriver {

    private static AppiumDriver appiumDriver; // singleton
    static IPageObject po;
    private static final String appium_hub = "app.mobitru.com";
    private static final String project_name = "anna_kosolapova1";

    @Override
    public AppiumDriver getDriver() { return appiumDriver; }

    public IPageObject getPo() {
        return po;
    }

    @Parameters({"platformName", "appType", "deviceName", "udid", "browserName", "app", "appPackage", "appActivity",
            "bundleId"})
    @BeforeSuite(alwaysRun = true)
    public void setUp(String platformName, String appType,
                      @Optional("") String deviceName,
                      @Optional("") String udid,
                      @Optional("") String browserName,
                      @Optional("") String app,
                      @Optional("") String appPackage,
                      @Optional("") String appActivity,
                      @Optional("") String bundleId)
            throws Exception {
        System.out.println("Before: app type - " + appType);
        setAppiumDriver(platformName, deviceName, udid, browserName, app, appPackage, appActivity, bundleId);
        setPageObject(appType, appiumDriver);
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() throws Exception {
        System.out.println("After");
        appiumDriver.closeApp();
    }

    private void setAppiumDriver(String platformName, String deviceName, String udid, String browserName,
                                 String app, String appPackage, String appActivity, String bundleId) throws UnsupportedEncodingException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        //mandatory Android capabilities
        capabilities.setCapability("platformName", platformName);
        capabilities.setCapability("deviceName", deviceName);
        capabilities.setCapability("udid", udid);

        if(app.endsWith(".apk")) capabilities.setCapability("app", (new File(app)).getAbsolutePath());

        capabilities.setCapability("browserName", browserName);
        capabilities.setCapability("chromedriverDisableBuildCheck","true");

        // Capabilities for tests of Android native app on Cloud
        capabilities.setCapability("appPackage", appPackage);
        capabilities.setCapability("appActivity", appActivity);

        // Capabilities for tests of iOs native app on Cloud
        capabilities.setCapability("bundleId", bundleId);

        String mobitruToken = System.getProperty("token");
        String token = URLEncoder.encode(mobitruToken, StandardCharsets.UTF_8.name());

        try {
            appiumDriver = new AppiumDriver(new URL(format("https://%s:%s@%s/wd/hub", project_name, token, appium_hub)),
                    capabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        // Timeouts tuning
        appiumDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }

    private void setPageObject(String appType, AppiumDriver appiumDriver) throws Exception {
        po = new PageObject(appType, appiumDriver);
    }
}
