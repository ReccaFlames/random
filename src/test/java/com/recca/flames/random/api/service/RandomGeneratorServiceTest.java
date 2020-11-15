package com.recca.flames.random.api.service;

import com.recca.flames.random.sources.javarandomgenerator.SecureRandomService;
import com.recca.flames.random.sources.randomorg.RandomOrgService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@ExtendWith(MockitoExtension.class)
class RandomGeneratorServiceTest {

    @Mock
    private RandomOrgService randomOrgService;
    @Mock
    private SecureRandomService secureRandomService;

    private RandomGeneratorService service;

    @BeforeEach
    void setUp() {
        service = new RandomGeneratorService(Arrays.asList(randomOrgService, secureRandomService));
    }

    @Test
    void shouldReturnRandomSumForDefaultParams() {
        //given
        given(randomOrgService.randomize()).willReturn(1800);
        given(secureRandomService.randomize()).willReturn(99);

        //when
        final Integer result = service.generate();

        //then
        assertThat(result).isEqualTo(1899);
        final InOrder inOrder = inOrder(randomOrgService, secureRandomService);
        inOrder.verify(randomOrgService).randomize();
        inOrder.verify(secureRandomService).randomize();
        verifyNoMoreInteractions(randomOrgService, secureRandomService);
    }

    @Test
    void shouldReturnSumForCustomParams() {
        //given
        given(randomOrgService.randomize()).willReturn(150);
        given(secureRandomService.randomize()).willReturn(151);

        //when
        final Integer result = service.generate();

        //then
        assertThat(result).isEqualTo(301);
        final InOrder inOrder = inOrder(randomOrgService, secureRandomService);
        inOrder.verify(randomOrgService).randomize();
        inOrder.verify(secureRandomService).randomize();
        verifyNoMoreInteractions(randomOrgService, secureRandomService);
    }
}
