package com.elsevier.aaharvester.resource.factory;

import com.elsevier.aaharvester.model.ReportFilter;
import com.elsevier.aaharvester.resource.FilterResource;
import org.springframework.stereotype.Component;

@Component
public class FilterResourceFactory {

    public FilterResource getInstance(final ReportFilter filter) {
        return FilterResource.builder()
                             .id(filter.getId())
                             .dateRange(filter.getDateRange())
                             .type(filter.getType())
                             .build();
    }

    public FilterResource getInstance(final String filterName,
                                      final ReportFilter filter) {
        final FilterResource.FilterResourceBuilder filterResourceBuilder = FilterResource.builder();
        switch (filter.getType()) {
            case SEGMENT:
                filterResourceBuilder.id(filter.getId())
                                     .segmentId(filter.getSegmentId())
                                     .segmentName(filterName)
                                     .type(filter.getType());
                break;
            case BREAKDOWN:
                filterResourceBuilder.id(filter.getId())
                                     .dimension(filter.getDimension())
                                     .itemId(filter.getItemId())
                                     .itemName(filterName)
                                     .type(filter.getType());
                break;
        }

        return filterResourceBuilder.build();
    }
}
