package com.design.pattern.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
public class TestConfiguration {
    @Value("${my.env}")
    public String env;
}
