package com.company.challenge;

import com.company.challenge.pages.base.BasePage;
import com.company.challenge.pages.base.PageFactory;
import com.google.common.base.Charsets;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

import static java.lang.System.currentTimeMillis;

public class BaseTest {

    protected static TestProperties properties = TestProperties.PROPERTIES;
    protected ThreadLocal<Logger> logger = ThreadLocal.withInitial(() -> null);
    private ThreadLocal<File> logFile = ThreadLocal.withInitial(() -> null);
    protected ThreadLocal<WebDriver> webDriver = ThreadLocal.withInitial(() -> null);
    protected ThreadLocal<WebDriverWait> webDriverWait = ThreadLocal.withInitial(() -> null);
    protected ThreadLocal<PageFactory> pageFactory = ThreadLocal.withInitial(() -> null);

    protected <T extends BasePage> T getPage(Class<T> pageClass) {
        return pageFactory.get().getPage(pageClass);
    }

    @BeforeEach
    void setUp(TestInfo testInfo) throws IOException {
        logger.set(getTestLogger(testInfo));
        webDriver.set(getDriver());
        webDriverWait.set(new WebDriverWait(webDriver.get(), 10, 50));
        pageFactory.set(new PageFactory(webDriver.get(), logger.get(), webDriverWait.get()));
    }

    @AfterEach
    void tearDown() throws IOException {
        logger.get().info("Attaching logs and screenshots");
        try {
            addBrowserLogs();
            addLogs();
            File scrFile = ((TakesScreenshot) webDriver.get()).getScreenshotAs(OutputType.FILE);
            addScreenShot(scrFile);
        } finally {
            webDriver.get().quit();
        }
    }

    private WebDriver getDriver() {
        return getChromeDriver();
    }

    protected WebDriver getChromeDriver() {
        WebDriverManager.chromedriver().version(properties.browserVersion()).setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized"); // https://stackoverflow.com/a/26283818/1689770
        options.addArguments("enable-automation"); // https://stackoverflow.com/a/43840128/1689770
//        options.addArguments("--headless"); // only if you are ACTUALLY running headless
        options.addArguments("--no-sandbox"); //https://stackoverflow.com/a/50725918/1689770
        options.addArguments("--disable-infobars"); //https://stackoverflow.com/a/43840128/1689770
        options.addArguments("--disable-features=NetworkService");
        options.addArguments("--dns-prefetch-disable");
        options.addArguments("--disable-extensions");
        options.addArguments("--disable-dev-shm-usage"); //https://stackoverflow.com/a/50725918/1689770
        options.addArguments("--disable-browser-side-navigation"); //https://stackoverflow.com/a/49123152/1689770
        options.addArguments("--disable-gpu"); //https://stackoverflow.com/questions/51959986/how-to-solve-selenium-chromedriver-timed-out-receiving-message-from-renderer-exc

        return new ChromeDriver(options);
    }

    private Logger getTestLogger(TestInfo testInfo) throws IOException {
        logFile.set(File.createTempFile(currentTimeMillis() + "_" + RandomStringUtils.random(10, true, false), ".log"));
        ConsoleAppender consoleAppender = new ConsoleAppender();
        consoleAppender.setName("Console appender");
        consoleAppender.setThreshold(Level.INFO);
        consoleAppender.setTarget(ConsoleAppender.SYSTEM_OUT);
        consoleAppender.setLayout(new PatternLayout("%d{ABSOLUTE}\t%4p\t%c{1}\t%m%n"));
        consoleAppender.activateOptions();

        FileAppender fileAppender = new FileAppender();
        fileAppender.setName("File appender");
        fileAppender.setFile(logFile.get().getAbsolutePath());
        fileAppender.setLayout(new PatternLayout("%d{ABSOLUTE}\t%4p\t%c{1}\t%m%n"));
        fileAppender.setThreshold(Level.INFO);
        fileAppender.setAppend(true);
        fileAppender.activateOptions();

        Logger logger = Logger.getLogger(testInfo.getTestClass().orElse(this.getClass()));
        logger.addAppender(fileAppender);
        logger.addAppender(consoleAppender);

        return logger;
    }


    @Attachment(value = "screenshot", type = "image/png")
    protected byte[] addScreenShot(File file) throws IOException {
        return FileUtils.readFileToByteArray(file);
    }

    @Attachment(value = "testLog.log", type = "")
    protected String addLogs() throws IOException {
        return FileUtils.readFileToString(logFile.get(), Charsets.UTF_8);
    }

    @Step("Adding browser logs")
    private void addBrowserLogs() {
        if (Objects.nonNull(webDriver.get())) {
            logger.get().info(String.format("Last URL was: [%s]", webDriver.get().getCurrentUrl()));

            if (properties.browser().equalsIgnoreCase("chrome")) {
                LogEntries logEntries = webDriver.get().manage().logs().get(LogType.BROWSER);
                for (LogEntry entry : logEntries) {
                    logger.get().info(String.format("Following logs were found during execution test %s", entry.getMessage()));
                }
            }
        } else {
            logger.get().info("Drives is 'null' cant perform analyze logs");
        }
    }
}
