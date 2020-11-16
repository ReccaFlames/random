package com.recca.flames.random.api.service;

import com.recca.flames.random.sources.commons.GenerateRandomService;
import com.recca.flames.random.sources.javarandomgenerator.SecureRandomService;
import com.recca.flames.random.sources.javarandomgenerator.configuration.SecureRandomProperties;
import com.recca.flames.random.sources.randomorg.RandomOrgService;
import com.recca.flames.random.sources.randomorg.client.RandomOrgApi;
import com.recca.flames.random.sources.randomorg.configuration.RandomOrgProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RandomGeneratorServiceConfig {

    @Bean
    public GenerateRandomService getSecureRandomService(SecureRandomProperties secureRandomProperties) {
        return new SecureRandomService(secureRandomProperties);
    }

    @Bean
    public GenerateRandomService getRandomOrgService(RandomOrgApi randomOrgApi, RandomOrgProperties properties) {
        return new RandomOrgService(properties, randomOrgApi);
    }
}
