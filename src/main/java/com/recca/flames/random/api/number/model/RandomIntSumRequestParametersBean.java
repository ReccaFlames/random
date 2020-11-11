package com.recca.flames.random.api.number.model;

import lombok.Setter;

@Setter
public class RandomIntSumRequestParametersBean {

    private Integer min;
    private Integer max;

    public RandomIntSumRequestParameters getRequestParameters() {
        return new RandomIntSumRequestParameters(min, max);
    }
}
