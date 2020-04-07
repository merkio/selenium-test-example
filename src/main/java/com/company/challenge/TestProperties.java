package com.company.challenge;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.ConfigFactory;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({"classpath:tests.properties"})
public interface TestProperties extends Config {

    TestProperties PROPERTIES = ConfigFactory.create(TestProperties.class);

    @Key("baseUrl")
    String baseUrl();

    @Key("userEmail")
    String userEmail();

    @Key("userPassword")
    String userPassword();

    @Key("browser")
    String browser();

    @Key("browserVersion")
    String browserVersion();
}