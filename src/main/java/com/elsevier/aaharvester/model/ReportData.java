package com.elsevier.aaharvester.model;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Builder
@ToString
public class ReportData {

    private String reportSuite;

    private String globalSegment;

    private String adobeMetric;

    private String dateMonth;

    private double value;

    private String segment;

    private String dimension;

    private String calculatedAt;

}
