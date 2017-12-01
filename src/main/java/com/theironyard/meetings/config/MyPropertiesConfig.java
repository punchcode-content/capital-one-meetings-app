package com.theironyard.meetings.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class MyPropertiesConfig {
    @Value("${meetings.allowConflict:false}")
    private Boolean allowConflict;

    @Value("${meetings.maxConflicts:2}")
    private Integer maxConflicts;

    @PostConstruct
    public void logConfig() {
        Logger log = LoggerFactory.getLogger(this.getClass());
        log.info("meetings.allowConflict: " + allowConflict);
        log.info("meetings.maxConflicts: " + maxConflicts);
    }
}