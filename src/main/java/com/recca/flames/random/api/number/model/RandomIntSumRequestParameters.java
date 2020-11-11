package com.recca.flames.random.api.number.model;

import com.recca.flames.random.commons.RequestParameter;
import com.recca.flames.random.commons.RequestParameters;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@EqualsAndHashCode
public class RandomIntSumRequestParameters implements RequestParameters {

    private final Integer min;
    private final Integer max;

    public Optional<Integer> getMin() {
        return Optional.ofNullable(min);
    }

    public Optional<Integer> getMax() {
        return Optional.ofNullable(max);
    }

    @Override
    public String toString() {
        return RequestParameters.toString(this);
    }

    @Override
    public List<RequestParameter> getRequestParameters() {
        final ArrayList<RequestParameter> requestParameters = new ArrayList<>();
        if (min != null) {
            requestParameters.add(new RequestParameter("min", min));
        }
        if (max != null) {
            requestParameters.add(new RequestParameter("max", max));
        }
        return requestParameters;
    }
}
