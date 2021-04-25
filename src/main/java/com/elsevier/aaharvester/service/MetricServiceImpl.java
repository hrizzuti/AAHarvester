package com.elsevier.aaharvester.service;

import com.elsevier.aaharvester.client.AARestClient;
import com.elsevier.aaharvester.resource.MetricResource;
import com.elsevier.aaharvester.resource.ReportResource;
import com.elsevier.aaharvester.resource.factory.ReportResourceFactory;
import com.elsevier.aaharvester.model.ReportData;
import com.elsevier.aaharvester.web.request.ReportRequest;
import com.elsevier.aaharvester.web.response.AnalyticsDimension;
import com.elsevier.aaharvester.web.response.ReportResponse;
import com.elsevier.aaharvester.web.util.RequestMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class MetricServiceImpl {

    private AARestClient aaRestClient;

    private ObjectMapper objectMapper;

    private SegmentLookupServiceImpl segmentLookupService;

    private ItemLookupServiceImpl itemLookupService;

    private RequestMapper mapper;

    private ReportResourceFactory reportResourceFactory;

    private MetricLookupServiceImpl metricLookupService;

/*    public MetricServiceImpl(final ObjectMapper objectMapper,
                             final SegmentLookupServiceImpl segmentLookupService,
                             final ItemLookupServiceImpl itemLookupService,
                             final ReportResourceFactory reportResourceFactory,
                             final RequestMapper mapper) {

        this.objectMapper = objectMapper;
        this.segmentLookupService = segmentLookupService;
        this.itemLookupService = itemLookupService;
        this.reportResourceFactory = reportResourceFactory;
        this.mapper = mapper;
    }*/

    public MetricServiceImpl(final AARestClient aaRestClient,
                             final SegmentLookupServiceImpl segmentLookupService,
                             final ItemLookupServiceImpl itemLookupService,
                             final MetricLookupServiceImpl metricLookupService,
                             final ReportResourceFactory reportResourceFactory,
                             final RequestMapper mapper) {

        this.aaRestClient = aaRestClient;
        this.segmentLookupService = segmentLookupService;
        this.itemLookupService = itemLookupService;
        this.metricLookupService = metricLookupService;
        this.reportResourceFactory = reportResourceFactory;
        this.mapper = mapper;
    }

    public List<List<ReportData>> processMetric(final String reportsJson) throws IOException {

        final ReportRequest reportRequest = mapper.toAAReports(reportsJson);
        final Map<String, String> segmentsLookup = segmentLookupService.getLookup(reportRequest);
        final Map<String, String> itemsLookup = itemLookupService.getLookup(reportRequest);
        final Map<String, String> metricLookup = metricLookupService.getLookup(reportRequest);

        final String url = String.format("/dimensions/%s?rsid=%s", StringUtils.substringAfter(reportRequest.getDimension(), "variables/"), reportRequest.getRsId());
        final ResponseEntity<AnalyticsDimension> dimensionResponseEntity = aaRestClient.get(url, AnalyticsDimension.class);
        final AnalyticsDimension dimensionResponse = dimensionResponseEntity.getBody();

//        final Path dimensionPath = Path.of(this.getClass().getClassLoader().getResource("json/dimensionResponse.json").getPath());
//        final String dimensionJson = Files.readString(dimensionPath, StandardCharsets.UTF_8);
//        final AnalyticsDimension dimensionResponse = objectMapper.readValue(dimensionJson, AnalyticsDimension.class);

        final ReportResource reportResource = reportResourceFactory.getInstance(reportRequest, segmentsLookup, itemsLookup, metricLookup, dimensionResponse);

        final ResponseEntity<ReportResponse> reportResponseResponseEntity = aaRestClient.post("/reports", reportsJson, ReportResponse.class);
        final ReportResponse reportResponse = reportResponseResponseEntity.getBody();

//        final Path reportsPath = Path.of(this.getClass().getClassLoader().getResource("json/reportsResponse.json").getPath());
//        final String reportsResponseJson = Files.readString(reportsPath, StandardCharsets.UTF_8);
//        final ReportResponse reportResponse = objectMapper.readValue(reportsResponseJson, ReportResponse.class);

        final List<ReportData> reportDataList = new ArrayList<>();
        final List<List<ReportData>> metrics = new ArrayList<>();
        reportResponse.getRows().forEach(r -> {
            final ReportData.ReportDataBuilder dataBuilder = ReportData.builder();

            final List<Double> data = r.getData();
            IntStream.range(0, r.getData().size())
                     .forEach(index -> {
                         final double value = data.get(index);
                         final String columnId = reportResponse.getColumns().getColumnIds().get(index);
                         final MetricResource metric = reportResource.getMetricMap().get(columnId);

                         dataBuilder.value(value)
                                    .adobeMetric(metric.getName())
                                    .globalSegment(reportResource.getGlobalFiltersSegment())
                                    .reportSuite(reportResource.getReportSuiteId());
                         metric.getFilters().forEach(f -> {
                             switch (f.getType()) {
                                 case SEGMENT:
                                     dataBuilder.segment(f.getSegmentName());
                                     break;
                                 case BREAKDOWN:
                                     dataBuilder.dimension(f.getItemName());
                                     break;
                             }
                         });
                         dataBuilder.dateMonth(r.getValue());
                         dataBuilder.calculatedAt(LocalDateTime.now().toString());
                         reportDataList.add(dataBuilder.build());
                     });
            metrics.add(reportDataList.stream().collect(Collectors.toList()));
            reportDataList.clear();
        });

        return metrics;
    }

}
