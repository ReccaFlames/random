package com.recca.flames.random.randomorg;

import com.googlecode.jsonrpc4j.JsonRpcClientException;
import com.recca.flames.random.commons.RandomNumberGeneratorService;
import com.recca.flames.random.randomorg.client.RandomOrgApi;
import com.recca.flames.random.randomorg.exceptions.RandomOrgApiException;
import com.recca.flames.random.randomorg.model.GetIntegersResponse;
import org.assertj.core.util.VisibleForTesting;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class RandomOrgService implements RandomNumberGeneratorService {
    private static final Logger LOGGER = LoggerFactory.getLogger(RandomOrgService.class);

    @Value("${randomOrg.apiKey}")
    private String apiKey;

    private final RandomOrgApi randomOrgApi;

    @Autowired
    public RandomOrgService(RandomOrgApi randomOrgApi) {
        this.randomOrgApi = randomOrgApi;
    }

    @VisibleForTesting
    public RandomOrgService(String apiKey, RandomOrgApi randomOrgApi) {
        this.apiKey = apiKey;
        this.randomOrgApi = randomOrgApi;
    }

    @Override
    public int getRandomInteger(int min, int max) {
        LOGGER.debug("Get random integer from Random.org basic API in range {} - {}", min, max);
        try {
            final GetIntegersResponse getIntegersResponse = randomOrgApi.generateIntegers(UUID.fromString(apiKey), 1, min, max);
            return getIntegersResponse.getRandom().getData().get(0);
        } catch (JsonRpcClientException exception) {
            throw new RandomOrgApiException(exception.getMessage());
        }
    }
}
