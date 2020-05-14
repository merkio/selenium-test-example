package com.company.logger;

import org.apache.logging.log4j.core.Filter;
import org.apache.logging.log4j.core.Layout;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.appender.AbstractAppender;
import org.apache.logging.log4j.core.config.Property;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginAttribute;
import org.apache.logging.log4j.core.config.plugins.PluginElement;
import org.apache.logging.log4j.core.config.plugins.PluginFactory;

import java.io.Serializable;

@Plugin(name = "ReportingAppender", category = "Core", elementType = "appender")
public class Log4jAppender extends AbstractAppender {

    public Log4jAppender(String name, Filter filter, Layout<? extends Serializable> layout,
                         boolean ignoreExceptions, Property[] properties) {
        super(name, filter, layout, ignoreExceptions, properties);
    }

    @PluginFactory
    public static Log4jAppender createAppender(@PluginAttribute("name") String name,
                                               @PluginElement("filter") Filter filter,
                                               @PluginElement("layout") Layout<? extends Serializable> layout) {
        if (name == null) {
            LOGGER.error("No name provided for Log4jAppender");
            return null;
        }

        if (layout == null) {
            LOGGER.error("No layout provided for Log4jAppender");
            return null;
        }
        return new Log4jAppender(name, filter, layout, false, null);
    }

    @Override
    public void append(LogEvent logEvent) {

    }

    @Override
    public void stop() {
        super.stop();
    }

    //    private Logger getTestLogger(TestInfo testInfo) throws IOException {
//        logFile.set(File.createTempFile(currentTimeMillis() + "_" + RandomStringUtils.random(10, true, false), ".log"));
//        ConsoleAppender consoleAppender = new ConsoleAppender();
//        consoleAppender.setName("Console appender");
//        consoleAppender.setThreshold(Level.INFO);
//        consoleAppender.setTarget(ConsoleAppender.SYSTEM_OUT);
//        consoleAppender.setLayout(new PatternLayout("%d{ABSOLUTE}\t%4p\t%c{1}\t%m%n"));
//        consoleAppender.activateOptions();
//
//        FileAppender fileAppender = new FileAppender();
//        fileAppender.setName("File appender");
//        fileAppender.setFile(logFile.get().getAbsolutePath());
//        fileAppender.setLayout(new PatternLayout("%d{ABSOLUTE}\t%4p\t%c{1}\t%m%n"));
//        fileAppender.setThreshold(Level.INFO);
//        fileAppender.setAppend(true);
//        fileAppender.activateOptions();
//
//        Logger logger = Logger.getLogger(testInfo.getTestClass().orElse(this.getClass()));
//        logger.addAppender(fileAppender);
//        logger.addAppender(consoleAppender);
//
//        return logger;
//    }
}
