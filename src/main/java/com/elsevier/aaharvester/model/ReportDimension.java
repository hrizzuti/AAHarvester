package com.elsevier.aaharvester.model;

import com.elsevier.aaharvester.model.enumerator.ReportDimensionType;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReportDimension {

    private String id;

    private ReportDimensionType type;
}
