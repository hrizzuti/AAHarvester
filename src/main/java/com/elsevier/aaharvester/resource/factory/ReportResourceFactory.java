package com.elsevier.aaharvester.resource.factory;

import com.elsevier.aaharvester.resource.FilterResource;
import com.elsevier.aaharvester.resource.MetricResource;
import com.elsevier.aaharvester.resource.ReportResource;
import com.elsevier.aaharvester.web.request.ReportRequest;
import com.elsevier.aaharvester.web.response.AnalyticsDimension;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ReportResourceFactory {

    private FilterResourceFactory filterResourceFactory;

    public ReportResourceFactory(FilterResourceFactory filterResourceFactory) {
        this.filterResourceFactory = filterResourceFactory;
    }

    public ReportResource getInstance(final ReportRequest reportRequest,
                                      final Map<String, String> segmentsLookup,
                                      final Map<String, String> itemsLookup,
                                      final AnalyticsDimension dimensionResponse) {

        final ReportResource.ReportResourceBuilder reportResourceBuilder = ReportResource.builder();
        final Map<String, FilterResource> filterMap = new HashMap<>();
        final Map<String, MetricResource> metricDTOMap = new HashMap<>();

        reportRequest.getGlobalFilters().forEach(gf -> {
            switch (gf.getType()) {
                case SEGMENT:
                    reportResourceBuilder.globalFiltersSegment(segmentsLookup.get(gf.getSegmentId()));
                    break;
                case DATE_RANGE:
                    reportResourceBuilder.globalFiltersDateRange(gf.getDateRange());
            }
        });

        reportRequest.getMetricContainer().getMetricFilters().forEach(filter -> {
            switch (filter.getType()) {
                case BREAKDOWN:
                    filterMap.put(filter.getId(), filterResourceFactory.getInstance(itemsLookup.get(filter.getItemId()), filter));
                    break;
                case SEGMENT:
                    filterMap.put(filter.getId(), filterResourceFactory.getInstance(segmentsLookup.get(filter.getSegmentId()), filter));
                    break;
                case DATE_RANGE:
                    filterMap.put(filter.getId(), filterResourceFactory.getInstance(filter));
            }
        });

        reportRequest.getMetricContainer().getMetrics().forEach(metric -> {
            final List<FilterResource> filters = new ArrayList<>();

            metric.getFilters().forEach(f -> {
                filters.add(filterMap.get(f));
            });

            metricDTOMap.put(metric.getColumnId(), MetricResource.builder()
                                                                 .columnId(metric.getColumnId())
                                                                 .name(metric.getId())
                                                                 .filters(filters)
                                                                 .build());
        });

        reportResourceBuilder.metricMap(metricDTOMap);
        reportResourceBuilder.dimension(dimensionResponse);
        reportResourceBuilder.reportSuiteId(reportRequest.getRsId());

        return reportResourceBuilder.build();
    }
}
