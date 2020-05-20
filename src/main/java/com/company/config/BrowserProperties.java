package com.company.config;


import org.aeonbits.owner.Config;
import org.aeonbits.owner.ConfigFactory;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({"classpath:tests.properties"})
public interface BrowserProperties extends Config {

    BrowserProperties BROWSER_PROPERTIES = ConfigFactory.create(BrowserProperties.class);

    @Key("browser")
    String browser();

    @Key("browser.version")
    String version();

    @Key("browser.executeRemotely")
    boolean executeRemotely();

    @Key("browser.enableVNC")
    boolean enableVNC();

    @Key("browser.enableVideo")
    boolean enableVideo();

    @Key("browser.enableCustomUserAgent")
    boolean enableCustomUserAgent();

    @Key("browser.hub")
    String hub();

    @DefaultValue("60")
    @Key("browser.fluent_wait")
    Integer fluentWaitInterval();
}
