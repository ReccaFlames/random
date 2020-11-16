package com.recca.flames.random.sources.commons;

import com.recca.flames.random.api.service.GeneratedRandom;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RandomValue implements GeneratedRandom {
    private Integer value;
}
