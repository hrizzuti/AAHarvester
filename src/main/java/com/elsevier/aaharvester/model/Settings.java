package com.elsevier.aaharvester.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Settings {

    private int limit;

    private int page;

    private String dimensionSort;

    private boolean countRepeatInstances;

    private boolean reflectRequest;

    private boolean includeAnomalyDetection;

    private boolean includePercentChange;

    private boolean includeLatLong;

    private String nonesBehavior;
}
