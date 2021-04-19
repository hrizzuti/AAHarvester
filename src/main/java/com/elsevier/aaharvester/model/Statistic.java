package com.elsevier.aaharvester.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class Statistic {

    private List<String> functions;

    private boolean ignoreZeroes;

}
