package com.elsevier.aaharvester.web.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SegmentResponse {

    private String id;

    private String name;

    private String description;

    @JsonProperty("rsid")
    private String rsId;

}
