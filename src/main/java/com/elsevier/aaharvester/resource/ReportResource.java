package com.elsevier.aaharvester.resource;

import com.elsevier.aaharvester.web.response.AnalyticsDimension;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
@Builder
public class ReportResource {

    private String reportSuiteId;

    private AnalyticsDimension dimension;

    private String globalFiltersSegment;

    private String globalFiltersDateRange;

    private Map<String, MetricResource> metricMap;

}
