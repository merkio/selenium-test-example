package com.company.base;

import com.company.config.SpringTestConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.lang.reflect.Method;
import java.nio.file.Path;
import java.nio.file.Paths;

@Slf4j
@ContextConfiguration(classes = SpringTestConfiguration.class)
public class BaseSpringTest extends AbstractTestNGSpringContextTests {

    @BeforeMethod(alwaysRun = true)
    public void logStartTestName(Method method) {
        String root = System.getProperty("user.dir");
        Path path = Paths.get(root, "target", "logs", method.getName() + "-" + Thread.currentThread().getName());
        MDC.put("fileName", path.toAbsolutePath().toString());
        log.info("\n****************\nStarting test {}\n****************", method.getName());
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(Method method, ITestResult testResult) {
        logStopTestName(testResult);
    }

    private void logStopTestName(ITestResult result) {
        String resultName;
        switch (result.getStatus()) {
            case ITestResult.SUCCESS: {
                resultName = "PASSED";
                break;
            }
            case ITestResult.FAILURE: {
                resultName = "FAILED";
                break;
            }
            case ITestResult.SKIP: {
                resultName = "SKIPPED";
                break;
            }
            default: {
                resultName = "UNDEFINED";
                break;
            }
        }
        log.info("\n****************\nFinished test {} with result: {}\n****************",
                 result.getMethod().getMethodName(), resultName);
        MDC.remove("fileName");
    }
}
