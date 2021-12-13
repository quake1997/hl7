package com.engine.hl7.config;

import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.util.DefaultUriBuilderFactory;

@Configuration
public class HttpConfiguration {

    @Bean
    TestRestTemplate testRestTemplate() {
        TestRestTemplate testRestTemplate = new TestRestTemplate();
        testRestTemplate.setUriTemplateHandler(new DefaultUriBuilderFactory("http://localhost:8080"));
        return testRestTemplate;
    }
}
