package com.elsevier.aaharvester.model;

import com.elsevier.aaharvester.model.enumerator.ReportFiltersType;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReportFilter {

    private String id;

    private ReportFiltersType type;

    private String dimension;

    private String itemId;

    //private List<String> itemIds;

    private String segmentId;

    //private List<String> segmentIds;

    private String dateRange;
}
