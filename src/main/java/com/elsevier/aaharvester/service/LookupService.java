package com.elsevier.aaharvester.service;

import com.elsevier.aaharvester.web.request.ReportRequest;

import java.io.IOException;
import java.util.Map;

public interface LookupService {

    Map<String, String> getLookup(final ReportRequest reportRequest) throws IOException;
}
