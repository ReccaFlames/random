package com.recca.flames.random.sources.javarandomgenerator.configuration;

import com.recca.flames.random.sources.commons.Range;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;


@Configuration
@ConfigurationProperties(prefix = "securerandom")
@Getter
@Setter
public class SecureRandomProperties {
    private Range range;
}
