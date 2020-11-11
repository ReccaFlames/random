package com.recca.flames.random.api.number;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.recca.flames.random.javarandomgenerator.exceptions.RandomIntGeneratorException;
import com.recca.flames.random.randomorg.exceptions.RandomOrgApiException;
import lombok.Getter;
import lombok.ToString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.Instant;

@ControllerAdvice
public class ControllerAdvisor {

    private static final Logger LOGGER = LoggerFactory.getLogger(ControllerAdvisor.class);

    @ExceptionHandler(RandomOrgApiException.class)
    public ResponseEntity<?> handleRandomOrgApiException(RandomOrgApiException ex, WebRequest request) {
        return prepareResponseEntity(ex, request, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RandomIntGeneratorException.class)
    public ResponseEntity<?> handleRandomIntGeneratorException(RandomIntGeneratorException ex, WebRequest request) {
        return prepareResponseEntity(ex, request, HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<?> prepareResponseEntity(Exception ex, WebRequest request, HttpStatus status) {
        final ErrorDetailsDto errorDetailsDto = new ErrorDetailsDto(Instant.now(), ex.getMessage(), request.getDescription(false));
        LOGGER.error("ERROR -> " + errorDetailsDto, ex);
        return new ResponseEntity<>(errorDetailsDto, status);
    }

    @Getter
    @ToString
    public static class ErrorDetailsDto {
        private final Instant timestamp;
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private final String message;
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private final String path;

        public ErrorDetailsDto(Instant timestamp, String message, String path) {
            this.timestamp = timestamp;
            this.message = message;
            this.path = path;
        }
    }
}
