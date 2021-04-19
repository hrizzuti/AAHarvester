package com.elsevier.aaharvester.model.enumerator;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ReportDimensionType {

    STRING("string"),
    INT("int"),
    DECIMAL("decimal"),
    CURRENCY("currency"),
    PERCENT("percent"),
    TIME("time"),
    ENUM("enum"),
    ORDERED_ENUM("orderedEnum");

    private final String value;

    ReportDimensionType(final String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }
}
