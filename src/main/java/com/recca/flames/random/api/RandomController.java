package com.recca.flames.random.api;

import com.recca.flames.random.api.service.GeneratedRandom;
import com.recca.flames.random.api.service.IRandomGeneratorService;
import com.recca.flames.random.api.service.RandomGeneratorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/api/random")
public class RandomController {
    private static final Logger LOGGER = LoggerFactory.getLogger(RandomController.class);

    private final IRandomGeneratorService service;

    public RandomController(RandomGeneratorService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity getRandom() {
        LOGGER.info("Get random");
        final GeneratedRandom result = service.generate();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
