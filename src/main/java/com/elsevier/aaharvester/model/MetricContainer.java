package com.elsevier.aaharvester.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class MetricContainer {

    private List<ReportMetric> metrics;

    private List<ReportFilter> metricFilters;

}
