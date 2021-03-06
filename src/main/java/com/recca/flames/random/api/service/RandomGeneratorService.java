package com.recca.flames.random.api.service;

import com.recca.flames.random.sources.commons.GenerateRandomService;
import com.recca.flames.random.sources.commons.RandomValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RandomGeneratorService implements IRandomGeneratorService {
    private static final Logger LOGGER = LoggerFactory.getLogger(RandomGeneratorService.class);

    private final List<GenerateRandomService> randomServices;

    @Autowired(required = false)
    RandomGeneratorService(List<GenerateRandomService> randomServices) {
        this.randomServices = randomServices;
    }

    @Override
    public GeneratedRandom generate() {
        LOGGER.debug("Get random value from {} sources", randomServices.size());
        final int result = getValues().stream()
                .map(RandomValue::getValue)
                .mapToInt(Integer::valueOf)
                .sum();
        return new RandomValue(result);
    }

    private List<RandomValue> getValues() {
        return randomServices.stream()
                .map(GenerateRandomService::randomize)
                .collect(Collectors.toList());
    }
}
