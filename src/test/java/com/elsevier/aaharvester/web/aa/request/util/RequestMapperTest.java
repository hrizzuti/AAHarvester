package com.elsevier.aaharvester.web.aa.request.util;

import com.elsevier.aaharvester.web.aa.request.ReportRequest;
import com.elsevier.aaharvester.web.aa.util.RequestMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RequestMapperTest {

    @Test
    public void shouldConvertJsonToRequest() throws IOException {
        final RequestMapper mapper = new RequestMapper(new ObjectMapper());
        final Path path = Path.of(this.getClass().getClassLoader().getResource("json/reportsRequest.json").getPath());
        final String jsonRequest = Files.readString(path, StandardCharsets.UTF_8);
        ReportRequest reportRequest = mapper.toAAReports(jsonRequest);
        assertEquals(2, reportRequest.getGlobalFilters().size());
        assertEquals(6, reportRequest.getMetricContainer().getMetrics().size());
        assertEquals(12, reportRequest.getMetricContainer().getMetricFilters().size());
    }

}
