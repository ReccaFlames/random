package com.recca.flames.random.sources.javarandomgenerator;

import com.recca.flames.random.sources.javarandomgenerator.exceptions.SecureRandomException;
import org.junit.jupiter.api.Test;

import java.security.NoSuchAlgorithmException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class SecureRandomServiceTest {

    @Test
    void shouldReturnRandomInt() throws NoSuchAlgorithmException {
        SecureRandomService service = new SecureRandomService(0, 2000);
        //when
        final int result = service.randomize();

        //then
        assertThat(result).isNotNull();
    }

    @Test
    void shouldThrowExceptionForInvalidRange() throws NoSuchAlgorithmException {
        SecureRandomService service = new SecureRandomService(0, 0);
        //when
        assertThatThrownBy(service::randomize)
                //then
                .isInstanceOf(SecureRandomException.class)
                .hasMessage("bound must be greater than origin");
    }
}
