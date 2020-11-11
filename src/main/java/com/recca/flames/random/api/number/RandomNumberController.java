package com.recca.flames.random.api.number;

import com.recca.flames.random.api.number.model.RandomIntSumRequestParameters;
import com.recca.flames.random.api.number.model.RandomIntSumRequestParametersBean;
import com.recca.flames.random.api.number.model.RandomNumberSumDto;
import com.recca.flames.random.api.number.service.RandomNumberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/api/random-number")
public class RandomNumberController {
    private static final Logger LOGGER = LoggerFactory.getLogger(RandomNumberController.class);

    private final RandomNumberService service;

    public RandomNumberController(RandomNumberService service) {
        this.service = service;
    }

    @GetMapping("/sum")
    public ResponseEntity<RandomNumberSumDto> getRandomNumberSum(RandomIntSumRequestParametersBean requestParametersBean) {
        final RandomIntSumRequestParameters requestParameters = requestParametersBean.getRequestParameters();
        LOGGER.info("Get random number sum from two different sources with {}", requestParameters);
        final RandomNumberSumDto randomIntSum = service.getRandomIntSum(requestParameters);
        return new ResponseEntity<>(randomIntSum, HttpStatus.OK);
    }
}
