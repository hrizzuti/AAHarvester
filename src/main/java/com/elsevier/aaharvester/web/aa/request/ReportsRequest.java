package com.elsevier.aaharvester.web.aa.request;

import com.elsevier.aaharvester.model.MetricContainer;
import com.elsevier.aaharvester.model.ReportFilter;
import com.elsevier.aaharvester.model.Settings;
import com.elsevier.aaharvester.model.Statistic;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class ReportsRequest {

    @JsonProperty("rsid")
    private String rsId;

    private String dimension;

    private List<ReportFilter> globalFilters;

    private MetricContainer metricContainer;

    private Settings settings;

    private Statistic statistics;

}
