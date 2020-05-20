package com.company;

import io.github.bonigarcia.wdm.WebDriverManager;
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

import static com.company.config.BrowserProperties.BROWSER_PROPERTIES;

@Slf4j
@Component
public class BrowserFactory {

    public static List<String> videoNames = new LinkedList<>();
    public static Map<String, String> userAgents = new HashMap<>();

    @SneakyThrows(MalformedURLException.class)
    private static WebDriver getChromeBrowser(String testName) {
        DesiredCapabilities caps = new DesiredCapabilities();
        LoggingPreferences logPrefs = new LoggingPreferences();
        ChromeOptions options = new ChromeOptions();

        logPrefs.enable(LogType.BROWSER, Level.WARNING);

        caps.setVersion(BROWSER_PROPERTIES.version());
        caps.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
        caps.setCapability("elementScrollBehavior", true);

        caps.setCapability("name", testName);
        caps.setCapability("enableVNC", BROWSER_PROPERTIES.enableVNC());
        String uuid = UUID.randomUUID().toString();

        if (BROWSER_PROPERTIES.enableCustomUserAgent()) {
            options.addArguments("--user-agent=selenium" + ";test=" + testName + ";uuid=" + uuid);
            userAgents.put(testName, uuid);
        }

        caps.setCapability(ChromeOptions.CAPABILITY, options);
        caps.setCapability("customCapability", "test");

        if (BROWSER_PROPERTIES.executeRemotely()) {
            String videoName = testName + new Date().getTime() + ".mp4";
            if (BROWSER_PROPERTIES.enableVideo()) {
                log.info("Enabling video recording because test failed");
                caps.setCapability("enableVideo", true);
                caps.setCapability("videoName", videoName);
                videoNames.add(videoName);
            }
            return new RemoteWebDriver(new URL(BROWSER_PROPERTIES.hub()), caps);
        } else {
            WebDriverManager.chromedriver().setup();
            options.merge(caps);
            return new ChromeDriver(options);
        }
    }

    @SneakyThrows(MalformedURLException.class)
    private static WebDriver getFireFoxBrowser(String testName) {
        DesiredCapabilities caps = DesiredCapabilities.firefox();
        LoggingPreferences logPrefs = new LoggingPreferences();
        FirefoxOptions options = new FirefoxOptions();

        caps.setVersion(BROWSER_PROPERTIES.version());
        logPrefs.enable(LogType.BROWSER, Level.WARNING);
        caps.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
        caps.setCapability("elementScrollBehavior", true);
        caps.setCapability("enableVNC", BROWSER_PROPERTIES.enableVNC());
        caps.setCapability("name", testName);

        if (BROWSER_PROPERTIES.executeRemotely()) {
            return new RemoteWebDriver(new URL(BROWSER_PROPERTIES.hub()), caps);
        } else {
            WebDriverManager.firefoxdriver().setup();
            options.merge(caps);
            return new FirefoxDriver(options);
        }
    }

    @SneakyThrows(MalformedURLException.class)
    private static WebDriver getMobileWebEmulationBrowser(String testName) {
        DesiredCapabilities caps = DesiredCapabilities.chrome();
        LoggingPreferences logPrefs = new LoggingPreferences();
        ChromeOptions chromeOptions = new ChromeOptions();

        logPrefs.enable(LogType.BROWSER, Level.WARNING);

        caps.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
        caps.setCapability("name", testName);
        caps.setCapability("enableVNC", BROWSER_PROPERTIES.enableVNC());

        Map<String, String> mobileEmulation = new HashMap<>();
        mobileEmulation.put("deviceName", "iPhone 6");

        caps.setVersion(BROWSER_PROPERTIES.version());
        chromeOptions.setCapability("mobileEmulation", mobileEmulation);
        caps.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
        caps.setCapability("name", testName);

        if (BROWSER_PROPERTIES.executeRemotely()) {
            return new RemoteWebDriver(new URL(BROWSER_PROPERTIES.hub()), caps);
        } else {
            WebDriverManager.chromedriver().setup();
            chromeOptions.merge(caps);
            return new ChromeDriver(chromeOptions);
        }
    }

    public static WebDriver getBrowser(String browserName, String testName) {
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
