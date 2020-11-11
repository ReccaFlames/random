package com.recca.flames.random.randomorg.configuration;

import com.googlecode.jsonrpc4j.JsonRpcHttpClient;
import com.googlecode.jsonrpc4j.ProxyUtil;
import com.recca.flames.random.randomorg.client.RandomOrgApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class RandomOrgConfiguration {
    private static final String ENDPOINT = "https://api.random.org/json-rpc/2/invoke";

    @Bean
    public JsonRpcHttpClient jsonRpcHttpClient() {
        URL url = null;
        final Map<String, String> map = new HashMap<>();
        map.put("Content-Type", "application/json");
        try {
            url = new URL(ENDPOINT);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return new JsonRpcHttpClient(url, map);
    }

    @Bean
    public RandomOrgApi randomOrgApiClient(JsonRpcHttpClient jsonRpcHttpClient) {
        return ProxyUtil.createClientProxy(getClass().getClassLoader(), RandomOrgApi.class, jsonRpcHttpClient);
    }
}
