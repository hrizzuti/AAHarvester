package com.elsevier.aaharvester.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class MetricDTO {

    private String id;

    private String columnId;

    private List<FilterDTO> filters;
}
