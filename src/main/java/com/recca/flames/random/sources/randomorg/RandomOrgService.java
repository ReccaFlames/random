package com.recca.flames.random.sources.randomorg;

import com.googlecode.jsonrpc4j.JsonRpcClientException;
import com.recca.flames.random.sources.commons.GenerateRandomService;
import com.recca.flames.random.sources.commons.RandomValue;
import com.recca.flames.random.sources.commons.Range;
import com.recca.flames.random.sources.randomorg.client.RandomOrgApi;
import com.recca.flames.random.sources.randomorg.configuration.RandomOrgProperties;
import com.recca.flames.random.sources.randomorg.exceptions.RandomOrgApiException;
import com.recca.flames.random.sources.randomorg.model.GetIntegersResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

public class RandomOrgService implements GenerateRandomService {
    private static final Logger LOGGER = LoggerFactory.getLogger(RandomOrgService.class);

    private final RandomOrgProperties properties;
    private final RandomOrgApi randomOrgApi;

    public RandomOrgService(RandomOrgProperties properties, RandomOrgApi randomOrgApi) {
        this.properties = properties;
        this.randomOrgApi = randomOrgApi;
    }

    @Override
    public RandomValue randomize() {
        final String apiKey = properties.getApiKey();
        final Range range = properties.getRange();
        final Integer min = range.getMin();
        final Integer max = range.getMax();
        LOGGER.debug("Get random integer from Random.org basic API in range {} - {}", min, max);
        try {
            final GetIntegersResponse getIntegersResponse = randomOrgApi.generateIntegers(UUID.fromString(apiKey), 1, min, max);
            final Integer value = getIntegersResponse.getRandom().getData().get(0);
            return new RandomValue(value);
        } catch (JsonRpcClientException exception) {
            throw new RandomOrgApiException(exception.getMessage());
        }
    }
}
