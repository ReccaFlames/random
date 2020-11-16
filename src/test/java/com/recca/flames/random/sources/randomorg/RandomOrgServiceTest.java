package com.recca.flames.random.sources.randomorg;

import com.googlecode.jsonrpc4j.JsonRpcClientException;
import com.recca.flames.random.sources.commons.Range;
import com.recca.flames.random.sources.randomorg.client.RandomOrgApi;
import com.recca.flames.random.sources.randomorg.configuration.RandomOrgProperties;
import com.recca.flames.random.sources.randomorg.exceptions.RandomOrgApiException;
import com.recca.flames.random.sources.randomorg.model.GetIntegersResponse;
import com.recca.flames.random.sources.randomorg.model.Random;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class RandomOrgServiceTest {

    private static final String API_KEY = "0a01e063-9c96-4f0b-84bd-fb98c00b29db";
    private static final Integer MIN = 0;
    private static final Integer MAX = 2000;

    @Mock
    private RandomOrgApi randomOrgApi;
    @Mock
    private RandomOrgProperties properties;
    @InjectMocks
    private RandomOrgService service;

    @Mock
    private GetIntegersResponse response;
    @Mock
    private Random random;

    @BeforeEach
    void setUp() {
        given(properties.getApiKey()).willReturn(API_KEY);
        given(properties.getRange()).willReturn(new Range(MIN, MAX));
    }

    @Test
    void shouldReturnRandomInt() {
        //given
        given(randomOrgApi.generateIntegers(UUID.fromString(API_KEY), 1, MIN, MAX)).willReturn(response);
        given(response.getRandom()).willReturn(random);
        given(random.getData()).willReturn(Collections.singletonList(1899));

        //when
        final int randomInteger = service.randomize();

        //then
        assertThat(randomInteger).isEqualTo(1899);
    }

    @Test
    void shouldThrowRandomOrgApiException() {
        //given
        given(randomOrgApi.generateIntegers(UUID.fromString(API_KEY), 1, MIN, MAX)).willThrow(JsonRpcClientException.class);

        //when
        assertThatThrownBy(() -> service.randomize())
                .isInstanceOf(RandomOrgApiException.class);
    }
}
