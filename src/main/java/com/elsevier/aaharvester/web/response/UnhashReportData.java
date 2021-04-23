package com.elsevier.aaharvester.web.response;

import com.elsevier.aaharvester.model.RowItem;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class UnhashReportData {

    private int totalPages;

    private boolean firstPage;

    private boolean lastPage;

    private int numberOfElements;

    private int number;

    private int totalElements;

    private String message;

    private String reportId;

    private String searchAnd;

    private String searchOr;

    private String searchNot;

    private String searchPhrase;

    private String oberonRequestXML;

    private String oberonResponseXML;

    private List<RowItem> rows;
}
