package com.elsevier.aaharvester.web.util;

import com.elsevier.aaharvester.web.request.ReportRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

@Component
public class RequestMapper {

    private ObjectMapper objectMapper;

    public RequestMapper(final ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public ReportRequest toAAReports(final String json) throws JsonProcessingException {
        return objectMapper.readValue(json, ReportRequest.class);
    }
}
