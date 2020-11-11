package com.recca.flames.random.commons;

import java.util.List;
import java.util.stream.Collectors;

public interface RequestParameters {
    static String toString(RequestParameters requestParameters) {
        return toString(requestParameters.getRequestParameters());
    }

    static String toString(List<RequestParameter> requestParameters) {
        if (requestParameters.isEmpty()) {
            return "no request parameters";
        }
        return "request parameters: " + requestParameters.stream()
                .map(RequestParameter::toString)
                .collect(Collectors.joining(", "));
    }

    List<RequestParameter> getRequestParameters();
}
