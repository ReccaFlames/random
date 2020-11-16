package com.recca.flames.random.api;

import com.recca.flames.random.api.service.RandomGeneratorService;
import com.recca.flames.random.sources.commons.RandomValue;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class RandomControllerTest {

    @Mock
    private RandomGeneratorService service;
    @InjectMocks
    private RandomController controller;

    @Test
    void shouldReturnSumOfRandomInts() {
        //given
        final RandomValue randomValue = new RandomValue(1000);
        given(service.generate()).willReturn(randomValue);

        //when
        final ResponseEntity response = controller.getRandom();

        //then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(randomValue);
    }
}
