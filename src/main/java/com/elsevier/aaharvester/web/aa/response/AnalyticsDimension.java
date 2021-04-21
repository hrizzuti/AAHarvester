package com.elsevier.aaharvester.web.aa.response;

import com.elsevier.aaharvester.model.enumerator.ReportDimensionType;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AnalyticsDimension {

    private String id;

    private String title;

    private String name;

    private ReportDimensionType type;

    private String category;
}
