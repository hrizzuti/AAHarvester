package com.elsevier.aaharvester.service;

import com.elsevier.aaharvester.client.AARestClient;
import com.elsevier.aaharvester.model.ReportFilter;
import com.elsevier.aaharvester.model.enumerator.ReportFiltersType;
import com.elsevier.aaharvester.web.request.ReportRequest;
import com.elsevier.aaharvester.web.response.SegmentResponse;
import com.elsevier.aaharvester.web.response.SegmentsResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class SegmentLookupServiceImpl implements LookupService {

//    private AARestClient aaRestClient;
//
//    public SegmentLookupServiceImpl(final AARestClient aaRestClient) {
//        this.aaRestClient = aaRestClient;
//    }

    private ObjectMapper objectMapper;

    public SegmentLookupServiceImpl(final ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public Map<String, String> getLookup(final ReportRequest reportRequest) throws IOException {

        final List<String> globalFilterSegmentIds = reportRequest.getGlobalFilters()
                                                                 .stream()
                                                                 .filter(g -> ReportFiltersType.SEGMENT == g.getType())
                                                                 .map(ReportFilter::getSegmentId)
                                                                 .collect(Collectors.toList());

        final List<String> metricFilterSegmentIds = reportRequest.getMetricContainer()
                                                                 .getMetricFilters()
                                                                 .stream()
                                                                 .filter(g -> ReportFiltersType.SEGMENT == g.getType())
                                                                 .map(ReportFilter::getSegmentId)
                                                                 .collect(Collectors.toList());
        if (globalFilterSegmentIds.size() == 0 && metricFilterSegmentIds.size() == 0) {
            return new HashMap<>();
        }

        final String combinedSegmentsIds = String.join(",", Stream.of(globalFilterSegmentIds, metricFilterSegmentIds)
                                                                  .flatMap(Collection::stream)
                                                                  .collect(Collectors.toList()));

//        final String url = String.format("/segments?rsid=%s&segmentFilter=%s", reportRequest.getRsId(), combinedSegmentsIds);
//        final ResponseEntity<SegmentsResponse> segmentsResponseResponseEntity = aaRestClient.get(url, SegmentsResponse.class);
//        final SegmentsResponse segmentsResponse = segmentsResponseResponseEntity.getBody();

        final Path segmentsPath = Path.of(this.getClass().getClassLoader().getResource("json/segmentsResponse.json").getPath());
        final String segmentsJson = Files.readString(segmentsPath, StandardCharsets.UTF_8);
        final SegmentsResponse segmentsResponse = objectMapper.readValue(segmentsJson, SegmentsResponse.class);

        return segmentsResponse.getContent()
                               .stream()
                               .collect(Collectors.toMap(s -> s.getId(), s -> s.getName()));
    }
}
