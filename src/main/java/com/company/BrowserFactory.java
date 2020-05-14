package com.company;

import com.company.config.BrowserProperties;
import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.stereotype.Component;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;
import java.util.logging.Level;

@Slf4j
@Component
@RequiredArgsConstructor
public class BrowserFactory {

    private final BrowserProperties browserProperties;
    public static List<String> videoNames = new LinkedList<>();
    public static Map<String, String> userAgents = new HashMap<>();

    @SneakyThrows(MalformedURLException.class)
    private WebDriver getChromeBrowser(String testName) {
        DesiredCapabilities caps = new DesiredCapabilities();
        LoggingPreferences logPrefs = new LoggingPreferences();
        ChromeOptions options = new ChromeOptions();

        logPrefs.enable(LogType.BROWSER, Level.WARNING);

        caps.setVersion(browserProperties.getVersion());
        caps.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
        caps.setCapability("elementScrollBehavior", true);
        caps.setCapability("name", testName);
        caps.setCapability("enableVNC", browserProperties.isEnableVNC());
        String uuid = UUID.randomUUID().toString();

        if (browserProperties.isEnableCustomUserAgent()) {
            options.addArguments("--user-agent=selenium" + ";test=" + testName + ";uuid=" + uuid);
            userAgents.put(testName, uuid);
        }

        caps.setCapability(ChromeOptions.CAPABILITY, options);
        caps.setCapability("customCapability", "test");

        if (browserProperties.isExecuteRemotely()) {
            String videoName = testName + new Date().getTime() + ".mp4";
            if (browserProperties.isEnableVideo()) {
                log.info("Enabling video recording because test failed");
                caps.setCapability("enableVideo", true);
                caps.setCapability("videoName", videoName);
                videoNames.add(videoName);
            }
            return new RemoteWebDriver(new URL(browserProperties.getHub()), caps);
        } else {
            WebDriverManager.chromedriver().setup();
            options.merge(caps);
            return new ChromeDriver(options);
        }
    }

    @SneakyThrows(MalformedURLException.class)
    private WebDriver getFireFoxBrowser(String testName) {
        DesiredCapabilities caps = DesiredCapabilities.firefox();
        LoggingPreferences logPrefs = new LoggingPreferences();
        FirefoxOptions options = new FirefoxOptions();

        caps.setVersion(browserProperties.getVersion());
        logPrefs.enable(LogType.BROWSER, Level.WARNING);
        caps.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
        caps.setCapability("elementScrollBehavior", true);
        caps.setCapability("enableVNC", browserProperties.isEnableVNC());
        caps.setCapability("name", testName);

        if (browserProperties.isExecuteRemotely()) {
            return new RemoteWebDriver(new URL(browserProperties.getHub()), caps);
        } else {
            WebDriverManager.firefoxdriver().setup();
            options.merge(caps);
            return new FirefoxDriver(options);
        }
    }

    @SneakyThrows(MalformedURLException.class)
    private WebDriver getMobileWebEmulationBrowser(String testName) {
        DesiredCapabilities caps = DesiredCapabilities.chrome();
        LoggingPreferences logPrefs = new LoggingPreferences();
        ChromeOptions chromeOptions = new ChromeOptions();

        logPrefs.enable(LogType.BROWSER, Level.WARNING);

        caps.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
        caps.setCapability("name", testName);
        caps.setCapability("enableVNC", browserProperties.isEnableVNC());

        Map<String, String> mobileEmulation = new HashMap<>();
        mobileEmulation.put("deviceName", "iPhone 6");

        caps.setVersion(browserProperties.getVersion());
        chromeOptions.setCapability("mobileEmulation", mobileEmulation);
        caps.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
        caps.setCapability("name", testName);

        if (browserProperties.isExecuteRemotely()) {
            return new RemoteWebDriver(new URL(browserProperties.getHub()), caps);
        } else {
            WebDriverManager.chromedriver().setup();
            chromeOptions.merge(caps);
            return new ChromeDriver(chromeOptions);
        }
    }

    public WebDriver getBrowser(String browserName, String testName) {
        switch (browserName) {
            case "firefox":
                return getFireFoxBrowser(testName);
            case "mobile_web":
                return getMobileWebEmulationBrowser(testName);
            default:
                return getChromeBrowser(testName);
        }
    }
}
