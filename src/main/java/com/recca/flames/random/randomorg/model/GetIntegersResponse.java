package com.recca.flames.random.randomorg.model;

import lombok.Data;

@Data
public class GetIntegersResponse {
    private Random random;
    private Integer bitsUsed;
    private Integer bitsLeft;
    private Integer requestsLeft;
    private Integer advisoryDelay;
}
