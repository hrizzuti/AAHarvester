package com.elsevier.aaharvester.service;

import com.elsevier.aaharvester.client.AARestClient;
import com.elsevier.aaharvester.model.ReportFilter;
import com.elsevier.aaharvester.model.enumerator.ReportFiltersType;
import com.elsevier.aaharvester.web.request.ReportRequest;
import com.elsevier.aaharvester.web.response.UnhashReportData;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Stream;

@Service
public class ItemLookupServiceImpl implements LookupService {

    private AARestClient aaRestClient;

    public ItemLookupServiceImpl(final AARestClient aaRestClient) {
        this.aaRestClient = aaRestClient;
    }

//    private ObjectMapper objectMapper;
//
//    public ItemLookupServiceImpl(final ObjectMapper objectMapper) {
//        this.objectMapper = objectMapper;
//    }

    public Map<String, String> getLookup(final ReportRequest reportRequest) throws IOException {

        final Map<String, String> itemsLookup = new HashMap<>();

        final Supplier<Stream<ReportFilter>> reportFilterStream = () -> reportRequest.getMetricContainer()
                                                                                     .getMetricFilters()
                                                                                     .stream()
                                                                                     .filter(mf -> ReportFiltersType.BREAKDOWN == mf.getType());

        final long itemsCount = reportFilterStream.get().count();


        if (itemsCount == 0) {
            return itemsLookup;
        }

        final Stream<String> dimensions = reportFilterStream.get().map(f -> f.getDimension()).distinct();

        dimensions.forEach(d -> {
            final String url = String.format("/reports/topItems?rsid=%s&dimension=%s&limit=100", reportRequest.getRsId(), d);
            final ResponseEntity<UnhashReportData> unhashReportDataResponseEntity = aaRestClient.get(url, UnhashReportData.class);
            final UnhashReportData unhashReportData = unhashReportDataResponseEntity.getBody();
            unhashReportData.getRows()
                            .stream().forEach(r -> {
                itemsLookup.put(r.getItemId(), r.getValue());
            });
        });


//        final Path topItemsPath = Path.of(this.getClass().getClassLoader().getResource("json/topItemsResponse.json").getPath());
//        final String topItemsJson = Files.readString(topItemsPath, StandardCharsets.UTF_8);
//        final UnhashReportData unhashReportData = objectMapper.readValue(topItemsJson, UnhashReportData.class);

        return itemsLookup;
    }
}
