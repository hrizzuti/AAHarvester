package com.elsevier.aaharvester.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class Column {

    private List<String> columnIds;

    private ReportDimension reportDimension;
}
