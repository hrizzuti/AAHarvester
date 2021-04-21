package com.elsevier.aaharvester.web.aa.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class SegmentsResponse {

    private List<SegmentResponse> content;

    private int totalElements;

    private int totalPages;

    private int number;

    private int numberOfElements;

    private boolean firstPage;

    private boolean lastPage;

    private int size;
}
