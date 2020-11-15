package com.recca.flames.random.api;

import com.recca.flames.random.api.service.RandomGeneratorService;
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
        given(service.generate()).willReturn(1000);

        //when
        final ResponseEntity response = controller.getRandom();

        //then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(1000);
    }
}
