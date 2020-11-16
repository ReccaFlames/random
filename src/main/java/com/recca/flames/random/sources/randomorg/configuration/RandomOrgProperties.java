package com.recca.flames.random.sources.randomorg.configuration;

import com.recca.flames.random.sources.commons.Range;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "randomorg")
@Getter
@Setter
public class RandomOrgProperties {
    private String apiKey;
    private Range range;
}
