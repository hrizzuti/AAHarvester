package com.elsevier.aaharvester.web.response;

import com.elsevier.aaharvester.model.enumerator.MetricPolarityType;
import com.elsevier.aaharvester.model.enumerator.ReportDimensionType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class AnalyticsCalculatedMetricResponse {

    private String id;
    private String name;
    private String description;

    @JsonProperty("rsid")
    private String rsId;

    private String reportSuiteName;

    private MetricPolarityType polarity;

    private int precision;

    private ReportDimensionType type;

}
