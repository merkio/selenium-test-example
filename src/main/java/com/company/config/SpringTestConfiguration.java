package com.company.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@Configuration
@ComponentScan(basePackages = "com.company")
@PropertySources({
    @PropertySource(value = "classpath:/tests.properties", ignoreResourceNotFound = true)
})
public class SpringTestConfiguration {

}
