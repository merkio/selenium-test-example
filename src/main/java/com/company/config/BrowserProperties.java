package com.company.config;


import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component
public class BrowserProperties {

    @Value("${browser}")
    private String browser;

    @Value("${browser.version}")
    private String version;

    @Value("${browser.executeRemotely}")
    private boolean executeRemotely;

    @Value("${browser.enableVNC}")
    private boolean enableVNC;

    @Value("${browser.enableVideo}")
    private boolean enableVideo;

    @Value("${browser.enableCustomUserAgent}")
    private boolean enableCustomUserAgent;

    @Value("${browser.hub}")
    private String hub;
}
