package com.recca.flames.random.api.service;

import com.recca.flames.random.sources.commons.RandomService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RandomGeneratorService implements IRandomGeneratorService {
    private static final Logger LOGGER = LoggerFactory.getLogger(RandomGeneratorService.class);

    private final List<RandomService<?>> randomServices;

    @Autowired(required = false)
    public RandomGeneratorService(List<RandomService<?>> randomServices) {
        this.randomServices = randomServices;
    }

    @Override
    public Integer generate() {
        LOGGER.debug("Get random value from {} sources", randomServices.size());
        return getValues().stream()
                .map(Object::toString)
                .mapToInt(Integer::valueOf)
                .sum();
    }

    private List<Object> getValues() {
        return randomServices.stream()
                .map(RandomService::randomize)
                .collect(Collectors.toList());
    }
}
