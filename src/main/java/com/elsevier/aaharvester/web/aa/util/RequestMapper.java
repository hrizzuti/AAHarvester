package com.elsevier.aaharvester.web.aa.util;

import com.elsevier.aaharvester.web.aa.request.ReportsRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

@Component
public class RequestMapper {

    private ObjectMapper objectMapper;

    public RequestMapper(final ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public ReportsRequest toAAReports(final String json) throws JsonProcessingException {
        return objectMapper.readValue(json, ReportsRequest.class);
    }
}
