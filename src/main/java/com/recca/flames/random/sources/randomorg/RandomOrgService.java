package com.recca.flames.random.sources.randomorg;

import com.googlecode.jsonrpc4j.JsonRpcClientException;
import com.recca.flames.random.sources.commons.RandomService;
import com.recca.flames.random.sources.randomorg.client.RandomOrgApi;
import com.recca.flames.random.sources.randomorg.exceptions.RandomOrgApiException;
import com.recca.flames.random.sources.randomorg.model.GetIntegersResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.UUID;

public class RandomOrgService implements RandomService<Integer> {
    private static final Logger LOGGER = LoggerFactory.getLogger(RandomOrgService.class);

    @Value("${randomOrg.apiKey}")
    private String apiKey;
    @Value("${randomOrg.range.min}")
    private Integer min;
    @Value("${randomOrg.range.max}")
    private Integer max;

    private final RandomOrgApi randomOrgApi;

    @Autowired
    public RandomOrgService(RandomOrgApi randomOrgApi) {
        this.randomOrgApi = randomOrgApi;
    }

    RandomOrgService(String apiKey, Integer min, Integer max, RandomOrgApi randomOrgApi) {
        this.apiKey = apiKey;
        this.min = min;
        this.max = max;
        this.randomOrgApi = randomOrgApi;
    }

    @Override
    public Integer randomize() {
        LOGGER.debug("Get random integer from Random.org basic API in range {} - {}", min, max);
        try {
            final GetIntegersResponse getIntegersResponse = randomOrgApi.generateIntegers(UUID.fromString(apiKey), 1, min, max);
            return getIntegersResponse.getRandom().getData().get(0);
        } catch (JsonRpcClientException exception) {
            throw new RandomOrgApiException(exception.getMessage());
        }
    }
}
