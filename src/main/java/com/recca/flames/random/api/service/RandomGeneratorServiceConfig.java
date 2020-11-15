package com.recca.flames.random.api.service;

import com.recca.flames.random.sources.commons.RandomService;
import com.recca.flames.random.sources.javarandomgenerator.SecureRandomService;
import com.recca.flames.random.sources.randomorg.RandomOrgService;
import com.recca.flames.random.sources.randomorg.client.RandomOrgApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.security.NoSuchAlgorithmException;

@Configuration
public class RandomGeneratorServiceConfig {

    private RandomOrgApi randomOrgApi;

    @Bean
    public RandomService getSecureRandomService() throws NoSuchAlgorithmException {
        return new SecureRandomService();
    }

    @Bean
    public RandomService getRandomOrgService() {
        return new RandomOrgService(randomOrgApi);
    }

    @Autowired
    public void setRandomOrgApi(RandomOrgApi randomOrgApi) {
        this.randomOrgApi = randomOrgApi;
    }
}
