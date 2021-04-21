package com.elsevier.aaharvester;

import com.elsevier.aaharvester.client.AARestClient;
import com.elsevier.aaharvester.dto.FilterDTO;
import com.elsevier.aaharvester.dto.MetricDTO;
import com.elsevier.aaharvester.dto.ReportDTO;
import com.elsevier.aaharvester.model.ReportFilter;
import com.elsevier.aaharvester.model.RowItem;
import com.elsevier.aaharvester.model.enumerator.ReportFiltersType;
import com.elsevier.aaharvester.web.aa.request.ReportRequest;
import com.elsevier.aaharvester.web.aa.response.AnalyticsDimension;
import com.elsevier.aaharvester.web.aa.response.ReportResponse;
import com.elsevier.aaharvester.web.aa.response.SegmentResponse;
import com.elsevier.aaharvester.web.aa.response.SegmentsResponse;
import com.elsevier.aaharvester.web.aa.response.UnhashReportData;
import com.elsevier.aaharvester.web.aa.util.RequestMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import javax.annotation.Resource;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootTest
class AaHarvesterApplicationTests {

    @Resource
    private AARestClient aaRestClient;

    @Resource
    private ObjectMapper objectMapper;

    @Resource
    private RequestMapper mapper;

    private String token = "eyJhbGciOiJSUzI1NiIsIng1dSI6Imltc19uYTEta2V5LTEuY2VyIn0.eyJpZCI6IjE2MTg5MjQ0NDQxNjRfOTk5YjAwNDgtMGE3ZS00ZjU0LWE3MjYtNmUwYTM3MDMwZTNmX3VlMSIsImNsaWVudF9pZCI6IjVhOGRjYzJjZmE3MTQ3MmNiZmE0ZmI1MzY3MWM0NWVkIiwidXNlcl9pZCI6IjY4OEQ0QTQyNUYyMDNGMzAwQTQ5NUM1OEAzNzViMjgyMzVjMDUwNTE2MGE0OTVjNGYiLCJzdGF0ZSI6IiIsInR5cGUiOiJhY2Nlc3NfdG9rZW4iLCJhcyI6Imltcy1uYTEiLCJhYV9pZCI6IjY4OEQ0QTQyNUYyMDNGMzAwQTQ5NUM1OEAzNzViMjgyMzVjMDUwNTE2MGE0OTVjNGYiLCJmZyI6IlZMMlpWNjJPRkxPNU5INEdDTVpMUkhRQUtNPT09PT09Iiwic2lkIjoiMTYxODkyNDQ0MzcxMl9kZDA2MzE4NS1kNDFlLTQ4Y2ItYWVmYy1hYjJlZDllZTZjZTdfdWUxIiwibW9pIjoiYWY4YzE5ZDIiLCJvYyI6InJlbmdhKm5hMXIqMTc4ZWY2YTk3MDgqTVpYQzg5R0IwRDBXQkRTTkpOVjgyRUc1MzAiLCJydGlkIjoiMTYxODkyNDQ0NDE2NF9mNGE3ZThhZC1kNzJmLTRmMzUtODYyYy05YWQ0YTA4YmZhMTdfdWUxIiwicnRlYSI6IjE2MjAxMzQwNDQxNjQiLCJleHBpcmVzX2luIjoiODY0MDAwMDAiLCJzY29wZSI6Im9wZW5pZCxBZG9iZUlELHJlYWRfb3JnYW5pemF0aW9ucyxhZGRpdGlvbmFsX2luZm8ucHJvamVjdGVkUHJvZHVjdENvbnRleHQsYWRkaXRpb25hbF9pbmZvLmpvYl9mdW5jdGlvbiIsImNyZWF0ZWRfYXQiOiIxNjE4OTI0NDQ0MTY0In0.Nzc8upPGxLUpEbgtfFgiSx8dK0YnyEprhpYrnLG02Tc5Bqjmihxd-2HzUEpsXRkfvfTr8aRZk5PDb5kY2AZ2XvG1G0Qm3cjEOK37u5bkIqw5jQZyGgzOMX3wL1w7Cuz9fSoql89-erwd998cCNYrLCVzmp5IZh8XnEHrPBKZiRCnTK9vbzLQWJcrHw0INOMuLSOZ5-b0ZyrcI_74VYPMsLCHiCmodpPFHAC0SVRdwta1je-SI8doK9sdzaWZWg52sy6y6uwRTcIs2ZDEBoxDi4DpRhN9CpimEHQPeUvDUFn1Puzf-dRNIFWdCP8hUZ8LGsAuIn65v0nhYi9V_8IHHA";

    @Test
    public void shouldConvertJsonToRequest() throws IOException {

        final ReportDTO reportDTO = createReportDTO("");

        //final ResponseEntity<ReportResponse> reportResponse = aaRestClient.post("/reports", token, "", ReportResponse.class);

        final Path reportsPath = Path.of(this.getClass().getClassLoader().getResource("json/reportsResponse.json").getPath());
        final String reportsJson = Files.readString(reportsPath, StandardCharsets.UTF_8);
        final ReportResponse reportResponse = objectMapper.readValue(reportsJson, ReportResponse.class);

    }

    private ReportDTO createReportDTO(String reportsJson) throws IOException {

        final Path path = Path.of(this.getClass().getClassLoader().getResource("json/reportsRequest.json").getPath());
        reportsJson = Files.readString(path, StandardCharsets.UTF_8);
        final ReportRequest reportRequest = mapper.toAAReports(reportsJson);

        final List<SegmentResponse> globalFiltersSegments = new ArrayList<>();
        final ReportDTO.ReportDTOBuilder reportDTOBuilder = ReportDTO.builder();

        final Map<String, SegmentResponse> segmentsLookup = getSegmentsLookup(reportRequest);

        reportRequest.getGlobalFilters().forEach(gf -> {
            if (ReportFiltersType.SEGMENT == gf.getType()) {
                globalFiltersSegments.add(segmentsLookup.get(gf.getSegmentId()));
            }
            if (ReportFiltersType.DATE_RANGE == gf.getType()) {
                reportDTOBuilder.globalFiltersDateRange(gf.getDateRange());
            }
        });

        reportDTOBuilder.globalFiltersSegment(globalFiltersSegments);

        final Map<String, FilterDTO> metricFiltersMap = new HashMap<>();
        final Map<String, MetricDTO> metricDTOMap = new HashMap<>();

        final Map<String, String> itemsLookup = getItemLookup(reportRequest);

        reportRequest.getMetricContainer().getMetricFilters().forEach(filter -> {
            if (ReportFiltersType.BREAKDOWN == filter.getType()) {
                metricFiltersMap.put(filter.getId(), FilterDTO.builder()
                                                              .id(filter.getId())
                                                              .dimension(filter.getDimension())
                                                              .itemId(filter.getItemId())
                                                              .itemName(itemsLookup.get(filter.getItemId()))
                                                              .type(filter.getType())
                                                              .build());
            }
            if (ReportFiltersType.SEGMENT == filter.getType()) {
                metricFiltersMap.put(filter.getId(), FilterDTO.builder()
                                                              .id(filter.getId())
                                                              .segmentId(filter.getSegmentId())
                                                              .segmentName(segmentsLookup.get(filter.getSegmentId()).getName())
                                                              .type(filter.getType())
                                                              .build());
            }
            if (ReportFiltersType.DATE_RANGE == filter.getType()) {
                metricFiltersMap.put(filter.getId(), FilterDTO.builder()
                                                              .id(filter.getId())
                                                              .dateRange(filter.getDateRange())
                                                              .type(filter.getType())
                                                              .build());
            }
        });

        reportRequest.getMetricContainer().getMetrics().forEach(metric -> {
            final List<FilterDTO> filters = new ArrayList<>();

            metric.getFilters().forEach(f -> {
                filters.add(metricFiltersMap.get(f));
            });

            metricDTOMap.put(metric.getColumnId(), MetricDTO.builder()
                                                            .columnId(metric.getColumnId())
                                                            .id(metric.getId())
                                                            .filters(filters)
                                                            .build());
        });

        reportDTOBuilder.metricMap(metricDTOMap);
//        final String url = String.format("/dimensions/%s?rsid=%s", StringUtils.substringAfter(reportRequest.getDimension(),"variables/"), reportRequest.getRsId());
//        final ResponseEntity<AnalyticsDimension> dimensionResponse = aaRestClient.get(url, token, AnalyticsDimension.class);

        final Path dimensionPath = Path.of(this.getClass().getClassLoader().getResource("json/dimensionResponse.json").getPath());
        final String dimensionJson = Files.readString(dimensionPath, StandardCharsets.UTF_8);
        final AnalyticsDimension dimensionResponse = objectMapper.readValue(dimensionJson, AnalyticsDimension.class);

        reportDTOBuilder.dimension(dimensionResponse);
        reportDTOBuilder.reportSuiteId(reportRequest.getRsId());

        return reportDTOBuilder.build();
    }

    private Map<String, SegmentResponse> getSegmentsLookup(final ReportRequest reportRequest) throws IOException {

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

        //final String url = String.format("/segments?rsids=%s&segmentFilter=%s", reportRequest.getRsId(), combinedSegmentsIds);
        //final ResponseEntity<SegmentsResponse> segmentResponse = aaRestClient.get(url, token, SegmentsResponse.class);

        final Path segmentsPath = Path.of(this.getClass().getClassLoader().getResource("json/segmentsResponse.json").getPath());
        final String segmentsJson = Files.readString(segmentsPath, StandardCharsets.UTF_8);
        final SegmentsResponse segmentsResponse = objectMapper.readValue(segmentsJson, SegmentsResponse.class);

        return segmentsResponse.getContent()
                               .stream()
                               .collect(Collectors.toMap(s -> s.getId(), s -> s));
    }

    private Map<String, String> getItemLookup(ReportRequest reportRequest) throws IOException {

        final long itemsCount = reportRequest.getMetricContainer()
                                             .getMetricFilters()
                                             .stream()
                                             .filter(mf -> ReportFiltersType.BREAKDOWN == mf.getType())
                                             .count();

        if (itemsCount == 0) {
            return new HashMap<>();
        }

        //final String url = String.format("/topItems?rsid=%s&dimension=%s&limit=100", reportRequest.getRsId(), reportRequest.getDimension());
        //final ResponseEntity<UnhashReportData> unhashReportData = aaRestClient.get(url, token, UnhashReportData.class);

        final Path topItemsPath = Path.of(this.getClass().getClassLoader().getResource("json/topItemsResponse.json").getPath());
        final String topItemsJson = Files.readString(topItemsPath, StandardCharsets.UTF_8);
        final UnhashReportData unhashReportData = objectMapper.readValue(topItemsJson, UnhashReportData.class);

        return unhashReportData.getRows()
                               .stream()
                               .collect(Collectors.toMap(RowItem::getItemId, RowItem::getValue));
    }

}
