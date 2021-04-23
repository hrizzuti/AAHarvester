package com.elsevier.aaharvester.resource;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class MetricResource {

    private String name;

    private String columnId;

    private List<FilterResource> filters;
}
