package com.elsevier.aaharvester;

import com.elsevier.aaharvester.model.ReportData;
import com.elsevier.aaharvester.service.MetricServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@SpringBootTest
class AaHarvesterApplicationTests {

    @Resource
    private MetricServiceImpl metricService;

    @Test
    public void shouldConvertJsonToRequest() throws IOException {
        final Path path = Path.of(this.getClass().getClassLoader().getResource("json/reportsRequest.json").getPath());
        final String reportsJson = Files.readString(path, StandardCharsets.UTF_8);
        final List<List<ReportData>> reportRows = metricService.processMetric(reportsJson);
        reportRows.stream().forEach(r -> r.stream().forEach(System.out::println));
    }
}
