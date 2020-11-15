package com.recca.flames.random.sources.javarandomgenerator;

import com.recca.flames.random.sources.commons.RandomService;
import com.recca.flames.random.sources.javarandomgenerator.exceptions.SecureRandomException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class SecureRandomService implements RandomService<Integer> {
    private static final Logger LOGGER = LoggerFactory.getLogger(SecureRandomService.class);

    private final SecureRandom random = SecureRandom.getInstanceStrong();
    @Value("${range.random.min}")
    private Integer min;
    @Value("${range.random.max}")
    private Integer max;

    public SecureRandomService() throws NoSuchAlgorithmException {
    }

    SecureRandomService(Integer min, Integer max) throws NoSuchAlgorithmException {
        this.min = min;
        this.max = max;
    }

    @Override
    public Integer randomize() {
        LOGGER.debug("Get random integer from java.util.SecureRandom in range {} - {}", min, max);
        try {
            return this.random.ints(min, max)
                    .findFirst()
                    .getAsInt();
        } catch (IllegalArgumentException exception) {
            throw new SecureRandomException(exception.getMessage());
        }
    }
}
