package com.elsevier.aaharvester.model.enumerator;

import com.fasterxml.jackson.annotation.JsonValue;

public enum MetricPolarityType {

    POSITIVE("positive"),
    NEGATIVE("negative");

    private final String value;

    MetricPolarityType(final String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }
}
