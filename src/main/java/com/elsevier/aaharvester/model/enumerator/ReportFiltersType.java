package com.elsevier.aaharvester.model.enumerator;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ReportFiltersType {

    DATE_RANGE("dateRange"),
    BREAKDOWN("breakdown"),
    SEGMENT("segment"),
    EXCLUDE_ITEM_IDS("excludeItemIds");

    private final String value;

    ReportFiltersType(final String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }
}
