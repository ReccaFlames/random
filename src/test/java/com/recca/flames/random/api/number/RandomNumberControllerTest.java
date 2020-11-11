package com.recca.flames.random.api.number;

import com.recca.flames.random.api.number.RandomNumberController;
import com.recca.flames.random.api.number.model.RandomIntSumRequestParameters;
import com.recca.flames.random.api.number.model.RandomIntSumRequestParametersBean;
import com.recca.flames.random.api.number.model.RandomNumberSumDto;
import com.recca.flames.random.api.number.service.RandomNumberService;
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
class RandomNumberControllerTest {

    @Mock
    private RandomNumberService service;
    @InjectMocks
    private RandomNumberController controller;

    @Mock
    private RandomIntSumRequestParametersBean parametersBean;
    @Mock
    private RandomNumberSumDto randomNumberSumDto;

    @Test
    void shouldReturnSumOfRandomInts() {
        //given
        final RandomIntSumRequestParameters requestParameters = new RandomIntSumRequestParameters(10, 1000);
        given(parametersBean.getRequestParameters()).willReturn(requestParameters);
        given(service.getRandomIntSum(requestParameters)).willReturn(randomNumberSumDto);

        //when
        final ResponseEntity<RandomNumberSumDto> response = controller.getRandomNumberSum(parametersBean);

        //then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(randomNumberSumDto);
    }
}
