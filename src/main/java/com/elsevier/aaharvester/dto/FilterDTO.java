package com.elsevier.aaharvester.dto;

import com.elsevier.aaharvester.model.enumerator.ReportFiltersType;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class FilterDTO {

    private String id;

    private ReportFiltersType type;

    private String dimension;

    private String itemId;

    private String itemName;

    private String segmentId;

    private String segmentName;

    private String dateRange;
}
