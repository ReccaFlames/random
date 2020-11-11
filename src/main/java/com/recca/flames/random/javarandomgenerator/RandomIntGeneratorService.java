package com.recca.flames.random.javarandomgenerator;

import com.recca.flames.random.commons.RandomNumberGeneratorService;
import com.recca.flames.random.javarandomgenerator.exceptions.RandomIntGeneratorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

@Service
public class RandomIntGeneratorService implements RandomNumberGeneratorService {
    private static final Logger LOGGER = LoggerFactory.getLogger(RandomIntGeneratorService.class);

    private final SecureRandom random = SecureRandom.getInstanceStrong();

    public RandomIntGeneratorService() throws NoSuchAlgorithmException {
    }

    @Override
    public int getRandomInteger(int min, int max) {
        LOGGER.debug("Get random integer from java.util.SecureRandom in range {} - {}", min, max);
        try {
            return this.random.ints(min, max)
                    .findFirst()
                    .getAsInt();
        } catch (IllegalArgumentException exception) {
            throw new RandomIntGeneratorException(exception.getMessage());
        }
    }
}
