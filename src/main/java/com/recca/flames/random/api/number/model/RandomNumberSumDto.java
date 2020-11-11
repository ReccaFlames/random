package com.recca.flames.random.api.number.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RandomNumberSumDto {
    private Integer numberFromFirstSource;
    private Integer numberFromSecondSource;
    private Integer sumOfNumbers;
}
