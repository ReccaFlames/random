package com.recca.flames.random.sources.javarandomgenerator;

import com.recca.flames.random.sources.commons.Range;
import com.recca.flames.random.sources.javarandomgenerator.configuration.SecureRandomProperties;
import com.recca.flames.random.sources.javarandomgenerator.exceptions.SecureRandomException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class SecureRandomServiceTest {

    @Mock
    SecureRandomProperties secureRandomProperties;


    @Test
    void shouldReturnRandomInt() {
        //given
        given(secureRandomProperties.getRange()).willReturn(new Range(0, 2000));
        SecureRandomService service = new SecureRandomService(secureRandomProperties);

        //when
        final int result = service.randomize();

        //then
        assertThat(result).isNotNull();
    }

    @Test
    void shouldThrowExceptionForInvalidRange() {
        //given
        SecureRandomService service = new SecureRandomService(secureRandomProperties);
        given(secureRandomProperties.getRange()).willReturn(new Range(0, 0));

        //when
        assertThatThrownBy(service::randomize)
                //then
                .isInstanceOf(SecureRandomException.class)
                .hasMessage("bound must be greater than origin");
    }
}
