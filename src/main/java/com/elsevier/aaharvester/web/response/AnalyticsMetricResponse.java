package com.elsevier.aaharvester.web.response;

import com.elsevier.aaharvester.model.enumerator.MetricPolarityType;
import com.elsevier.aaharvester.model.enumerator.ReportDimensionType;
import com.elsevier.aaharvester.model.enumerator.ReportFiltersType;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class AnalyticsMetricResponse {

    private String id;

    private String title;

    private String name;

    private ReportDimensionType type;

    private String category;

    private List<String> categories;

    private List<String> support;

    private boolean allocation;

    private int precision;

    private boolean calculated;

    private boolean segmentable;

    private String description;

    private MetricPolarityType polarity;

    private String helpLink;

    private boolean allowedForReporting;
}
