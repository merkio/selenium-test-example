package com.company.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component
public class TestProperties {

    @Value("${baseUrl}")
    private String baseUrl;

    @Value("${user.email.default}")
    private String userEmailDefault;

    @Value("${user.password.default}")
    private String userPasswordDefault;
}
