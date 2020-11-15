package com.recca.flames.random.sources.randomorg.client;

import com.googlecode.jsonrpc4j.JsonRpcParam;
import com.recca.flames.random.sources.randomorg.model.GetIntegersResponse;

import java.util.UUID;

public interface RandomOrgApi {
    GetIntegersResponse generateIntegers(@JsonRpcParam(value = "apiKey") UUID apiKey, @JsonRpcParam(value = "n") int n, @JsonRpcParam(value = "min") int min, @JsonRpcParam(value = "max") int max);
}
