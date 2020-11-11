package com.recca.flames.random.api.number.service;

import com.recca.flames.random.api.number.model.RandomIntSumRequestParameters;
import com.recca.flames.random.api.number.model.RandomNumberSumDto;
import com.recca.flames.random.javarandomgenerator.RandomIntGeneratorService;
import com.recca.flames.random.randomorg.RandomOrgService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@ExtendWith(MockitoExtension.class)
class RandomNumberServiceTest {

    @Mock
    private RandomOrgService randomOrgService;
    @Mock
    private RandomIntGeneratorService randomIntGeneratorService;
    @InjectMocks
    private RandomNumberService service;

    @Test
    void shouldReturnRandomSumForDefaultParams() {
        //given
        final RandomIntSumRequestParameters requestParameters = new RandomIntSumRequestParameters(null, null);
        given(randomOrgService.getRandomInteger(0, 2000)).willReturn(1800);
        given(randomIntGeneratorService.getRandomInteger(0, 2000)).willReturn(99);

        //when
        final RandomNumberSumDto result = service.getRandomIntSum(requestParameters);

        //then
        final RandomNumberSumDto expected = RandomNumberSumDto.builder()
                .numberFromFirstSource(1800)
                .numberFromSecondSource(99)
                .sumOfNumbers(1899)
                .build();
        assertThat(result).isEqualTo(expected);
        final InOrder inOrder = inOrder(randomOrgService, randomIntGeneratorService);
        inOrder.verify(randomOrgService).getRandomInteger(0, 2000);
        inOrder.verify(randomIntGeneratorService).getRandomInteger(0, 2000);
        verifyNoMoreInteractions(randomOrgService, randomIntGeneratorService);
    }

    @Test
    void shouldReturnSumForCustomParams() {
        //given
        final RandomIntSumRequestParameters requestParameters = new RandomIntSumRequestParameters(200, 300);
        given(randomOrgService.getRandomInteger(200, 300)).willReturn(150);
        given(randomIntGeneratorService.getRandomInteger(200, 300)).willReturn(151);

        //when
        final RandomNumberSumDto result = service.getRandomIntSum(requestParameters);

        //then
        final RandomNumberSumDto expected = RandomNumberSumDto.builder()
                .numberFromFirstSource(150)
                .numberFromSecondSource(151)
                .sumOfNumbers(301)
                .build();
        assertThat(result).isEqualTo(expected);
        final InOrder inOrder = inOrder(randomOrgService, randomIntGeneratorService);
        inOrder.verify(randomOrgService).getRandomInteger(200, 300);
        inOrder.verify(randomIntGeneratorService).getRandomInteger(200, 300);
        verifyNoMoreInteractions(randomOrgService, randomIntGeneratorService);
    }
}
