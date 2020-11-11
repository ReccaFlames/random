package com.recca.flames.random.javarandomgenerator;

import com.recca.flames.random.javarandomgenerator.exceptions.RandomIntGeneratorException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ExtendWith(MockitoExtension.class)
class RandomIntGeneratorServiceTest {

    @InjectMocks
    private RandomIntGeneratorService service;

    @Test
    void shouldReturnRandomInt() {
        //when
        final int result = service.getRandomInteger(10, 20);

        //then
        assertThat(result).isNotNull();
    }

    @Test
    void shouldThrowExceptionForInvalidRange() {
        //when
        assertThatThrownBy(() -> service.getRandomInteger(0,0))
            //then
            .isInstanceOf(RandomIntGeneratorException.class)
            .hasMessage("bound must be greater than origin");
    }
}
