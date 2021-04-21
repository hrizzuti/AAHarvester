package com.elsevier.aaharvester.dto;

import com.elsevier.aaharvester.web.aa.response.AnalyticsDimension;
import com.elsevier.aaharvester.web.aa.response.SegmentResponse;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.Map;

@Getter
@Builder
public class ReportDTO {

    private String reportSuiteId;

    private AnalyticsDimension dimension;

    private List<SegmentResponse> globalFiltersSegment;

    private String globalFiltersDateRange;

    private Map<String, MetricDTO> metricMap;

}
