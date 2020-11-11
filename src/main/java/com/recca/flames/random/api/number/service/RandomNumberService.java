package com.recca.flames.random.api.number.service;

import com.recca.flames.random.api.number.model.RandomIntSumRequestParameters;
import com.recca.flames.random.api.number.model.RandomNumberSumDto;
import com.recca.flames.random.javarandomgenerator.RandomIntGeneratorService;
import com.recca.flames.random.randomorg.RandomOrgService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class RandomNumberService {
    private static final Logger LOGGER = LoggerFactory.getLogger(RandomNumberService.class);

    private final RandomOrgService randomOrgService;
    private final RandomIntGeneratorService randomIntGeneratorService;

    public RandomNumberService(RandomOrgService randomOrgService, RandomIntGeneratorService randomIntGeneratorService) {
        this.randomOrgService = randomOrgService;
        this.randomIntGeneratorService = randomIntGeneratorService;
    }

    public RandomNumberSumDto getRandomIntSum(RandomIntSumRequestParameters requestParameters) {
        final Integer min = requestParameters.getMin().orElse(0);
        final Integer max = requestParameters.getMax().orElse(2000);
        LOGGER.debug("Get random integer from range {} - {}", min, max);
        final int randomOrgInteger = randomOrgService.getRandomInteger(min, max);
        final int randomIntegerJava = randomIntGeneratorService.getRandomInteger(min, max);
        return RandomNumberSumDto.builder()
                .numberFromFirstSource(randomOrgInteger)
                .numberFromSecondSource(randomIntegerJava)
                .sumOfNumbers(Integer.sum(randomOrgInteger, randomIntegerJava))
                .build();
    }
}
