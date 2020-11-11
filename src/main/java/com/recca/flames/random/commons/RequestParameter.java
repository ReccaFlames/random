package com.recca.flames.random.commons;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class RequestParameter {

    private final String name;
    private final Object value;

    public RequestParameter(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public String toString() {
        return name + '=' + value;
    }
}
