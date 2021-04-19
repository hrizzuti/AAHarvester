package com.elsevier.aaharvester.web.aa.response;

import com.elsevier.aaharvester.model.Column;
import com.elsevier.aaharvester.model.Row;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReportsResponse {

    private int totalPages;

    private boolean firstPage;

    private boolean lastPage;

    private int numberOfElements;

    private int number;

    private int totalElements;

    private String message;

    private String reportId;

    private Column column;

    private Row row;
}
