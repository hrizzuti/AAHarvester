package com.elsevier.aaharvester.service;

import com.elsevier.aaharvester.client.AARestClient;
import com.elsevier.aaharvester.web.request.ReportRequest;
import com.elsevier.aaharvester.web.response.AnalyticsCalculatedMetricResponse;
import com.elsevier.aaharvester.web.response.AnalyticsMetricResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class MetricLookupServiceImpl implements LookupService {

    private AARestClient aaRestClient;

    public MetricLookupServiceImpl(final AARestClient aaRestClient) {
        this.aaRestClient = aaRestClient;
    }

    @Override
    public Map<String, String> getLookup(final ReportRequest reportRequest) throws IOException {

        List<String> metricIds = reportRequest.getMetricContainer()
                                              .getMetrics()
                                              .stream()
                                              .map(m -> m.getId())
                                              .distinct()
                                              .collect(Collectors.toList());

        Map<String, String> metricLookupMap = new HashMap<>();

        metricIds.stream().forEach(id -> {
            if (id.contains("cm")) {
                final AnalyticsCalculatedMetricResponse analyticsCalculatedMetricResponse = getCalculatedMetricResponse(reportRequest, id);
                metricLookupMap.put(id, analyticsCalculatedMetricResponse.getName());
            } else if (id.contains("metrics")) {
                final AnalyticsMetricResponse analyticsMetricResponse = getMetricResponse(reportRequest, id);
                metricLookupMap.put(id, analyticsMetricResponse.getName());
            }
        });

        return metricLookupMap;
    }

    private AnalyticsMetricResponse getMetricResponse(final ReportRequest reportRequest,
                                                      final String id) {
        // since the id is always in this format: metrics/{metricId} then it can be used as the endpoint itself
        final String url = String.format("/%s?rsid=%s", id, reportRequest.getRsId());
        final ResponseEntity<AnalyticsMetricResponse> analyticsMetricResponseEntity = aaRestClient.get(url, AnalyticsMetricResponse.class);
        final AnalyticsMetricResponse analyticsMetricResponse = analyticsMetricResponseEntity.getBody();
        return analyticsMetricResponse;
    }

    private AnalyticsCalculatedMetricResponse getCalculatedMetricResponse(final ReportRequest reportRequest,
                                                                          final String id) {

        final String url = String.format("/calculatedmetrics/%s?rsid=%s", id, reportRequest.getRsId());
        final ResponseEntity<AnalyticsCalculatedMetricResponse> calculatedMetricResponseEntity = aaRestClient.get(url,
                                                                                                                  AnalyticsCalculatedMetricResponse.class);
        final AnalyticsCalculatedMetricResponse analyticsCalculatedMetricResponse = calculatedMetricResponseEntity.getBody();
        return analyticsCalculatedMetricResponse;
    }
}
