package com.elsevier.aaharvester.service;

import com.elsevier.aaharvester.client.AARestClient;
import com.elsevier.aaharvester.model.RowItem;
import com.elsevier.aaharvester.model.enumerator.ReportFiltersType;
import com.elsevier.aaharvester.web.request.ReportRequest;
import com.elsevier.aaharvester.web.response.UnhashReportData;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ItemLookupServiceImpl implements LookupService {

//    private AARestClient aaRestClient;
//
//    public ItemLookupServiceImpl(final AARestClient aaRestClient) {
//        this.aaRestClient = aaRestClient;
//    }

    private ObjectMapper objectMapper;

    public ItemLookupServiceImpl(final ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public Map<String, String> getLookup(final ReportRequest reportRequest) throws IOException {

        final long itemsCount = reportRequest.getMetricContainer()
                                             .getMetricFilters()
                                             .stream()
                                             .filter(mf -> ReportFiltersType.BREAKDOWN == mf.getType())
                                             .count();

        if (itemsCount == 0) {
            return new HashMap<>();
        }

//        final String url = String.format("/reports/topItems?rsid=%s&dimension=%s&limit=100", reportRequest.getRsId(), reportRequest.getDimension());
//        final ResponseEntity<UnhashReportData> unhashReportDataResponseEntity = aaRestClient.get(url, UnhashReportData.class);
//        final UnhashReportData unhashReportData = unhashReportDataResponseEntity.getBody();

        final Path topItemsPath = Path.of(this.getClass().getClassLoader().getResource("json/topItemsResponse.json").getPath());
        final String topItemsJson = Files.readString(topItemsPath, StandardCharsets.UTF_8);
        final UnhashReportData unhashReportData = objectMapper.readValue(topItemsJson, UnhashReportData.class);

        return unhashReportData.getRows()
                               .stream()
                               .collect(Collectors.toMap(RowItem::getItemId, RowItem::getValue));
    }
}
