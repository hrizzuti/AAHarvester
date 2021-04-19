package com.elsevier.aaharvester.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class Row {

    private String itemId;

    private String value;

    private String rowId;

    private List<Integer> data;

}
