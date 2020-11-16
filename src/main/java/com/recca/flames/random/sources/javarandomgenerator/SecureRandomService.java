package com.recca.flames.random.sources.javarandomgenerator;

import com.recca.flames.random.sources.commons.GenerateRandomService;
import com.recca.flames.random.sources.commons.RandomValue;
import com.recca.flames.random.sources.commons.Range;
import com.recca.flames.random.sources.javarandomgenerator.configuration.SecureRandomProperties;
import com.recca.flames.random.sources.javarandomgenerator.exceptions.SecureRandomException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class SecureRandomService implements GenerateRandomService {
    private static final Logger LOGGER = LoggerFactory.getLogger(SecureRandomService.class);

    private final SecureRandom random;
    private final SecureRandomProperties properties;

    public SecureRandomService(SecureRandomProperties secureRandomProperties) {
        try {
            random = SecureRandom.getInstanceStrong();
        } catch (NoSuchAlgorithmException e) {
            throw new SecureRandomException(e.getMessage());
        }
        this.properties = secureRandomProperties;
    }

    @Override
    public RandomValue randomize() {
        final Range range = properties.getRange();
        final Integer min = range.getMin();
        final Integer max = range.getMax();
        LOGGER.debug("Get random integer from java.util.SecureRandom in range {} - {}", min, max);
        try {
            final int value = this.random.ints(min, max)
                    .findFirst()
                    .getAsInt();
            return new RandomValue(value);
        } catch (IllegalArgumentException exception) {
            throw new SecureRandomException(exception.getMessage());
        }
    }
}
