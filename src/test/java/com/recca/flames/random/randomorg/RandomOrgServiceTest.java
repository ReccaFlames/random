package com.recca.flames.random.randomorg;

import com.googlecode.jsonrpc4j.JsonRpcClientException;
import com.recca.flames.random.javarandomgenerator.RandomIntGeneratorService;
import com.recca.flames.random.randomorg.client.RandomOrgApi;
import com.recca.flames.random.randomorg.exceptions.RandomOrgApiException;
import com.recca.flames.random.randomorg.model.GetIntegersResponse;
import com.recca.flames.random.randomorg.model.Random;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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

    @Mock
    private RandomOrgApi randomOrgApi;

    private RandomOrgService service;

    @Mock
    private GetIntegersResponse response;
    @Mock
    private Random random;

    @BeforeEach
    void setUp() {
        service = new RandomOrgService(API_KEY, randomOrgApi);
    }

    @Test
    void shouldReturnRandomInt() {
        //given
        final int min = 10;
        final int max = 20;
        given(randomOrgApi.generateIntegers(UUID.fromString(API_KEY), 1, min, max)).willReturn(response);
        given(response.getRandom()).willReturn(random);
        given(random.getData()).willReturn(Collections.singletonList(1899));

        //when
        final int randomInteger = service.getRandomInteger(min, max);

        //then
        assertThat(randomInteger).isEqualTo(1899);
    }

    @Test
    void shouldThrowRandomOrgApiException() {
        //given
        final int min = 10;
        final int max = 10;
        given(randomOrgApi.generateIntegers(UUID.fromString(API_KEY), 1, min, max)).willThrow(JsonRpcClientException.class);

        //when
        assertThatThrownBy(() -> service.getRandomInteger(min, max))
                .isInstanceOf(RandomOrgApiException.class);
    }
}
