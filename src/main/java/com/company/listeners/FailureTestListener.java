package com.company.listeners;

import com.company.WebDriverHolder;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

@Slf4j
public class FailureTestListener implements ITestListener {

    @Override
    public void onTestSuccess(ITestResult result) {
        analyzeLog(result.getMethod().getMethodName());
    }

    @Override
    @SneakyThrows
    public void onTestFailure(ITestResult result) {
        uploadScreenshots(result);
        analyzeLog(result.getMethod().getMethodName());
    }

    private void uploadScreenshots(ITestResult testResult) throws IOException {
        if (Objects.nonNull(WebDriverHolder.getDriver())) {
            try {
                File scrFile = ((TakesScreenshot) WebDriverHolder.getDriver()).getScreenshotAs(OutputType.FILE);
                attachBrowserScreenshot(scrFile);
            } catch (Exception e) {
                log.error("Failed to attach screenshot to test [{}]", testResult.getName(), e);
            }
        }
    }

    @Attachment(value = "Browser window screenshot", type = "image/png")
    private static byte[] attachBrowserScreenshot(File file) throws IOException {
        return FileUtils.readFileToByteArray(file);
    }

    @Step("Adding collected logs")
    private void analyzeLog(String methodName) {
        if (Objects.nonNull(WebDriverHolder.getDriver())) {
            log.info("Last URL was: '{}'", WebDriverHolder.getDriver().getCurrentUrl());

            if (((RemoteWebDriver) WebDriverHolder.getDriver()).getCapabilities().getBrowserName().equals("chrome")) {
                LogEntries logEntries = WebDriverHolder.getDriver().manage().logs().get(LogType.BROWSER);
                for (LogEntry entry : logEntries) {
                    log.info("Following logs were found during execution test {} {}", methodName, entry.getMessage());
                }
            }
        } else {
            log.info("Drives is 'null' cant perform analyze logs");
        }
    }
}
