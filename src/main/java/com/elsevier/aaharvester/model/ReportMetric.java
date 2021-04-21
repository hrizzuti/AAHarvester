package com.elsevier.aaharvester.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class ReportMetric {

    private String id;

    private String columnId;

    private List<String> filters;

    //private String sort;

    //private MetricDefinition metricDefinition;
}
