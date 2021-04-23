package com.elsevier.aaharvester.client;

import com.elsevier.aaharvester.AaHarvesterApplication;
import com.elsevier.aaharvester.web.response.ReportResponse;
import com.elsevier.aaharvester.web.response.SegmentResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.annotation.Resource;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK,
                classes = {AaHarvesterApplication.class})
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AARestClientTest {

    @Resource
    private AARestClient aaRestClient;

    @Resource
    private ObjectMapper objectMapper;

    @Test
    public void shouldCallAAReport() throws IOException {
        final Path path = Path.of(this.getClass().getClassLoader().getResource("json/reportsRequest.json").getPath());
        final String request = Files.readString(path, StandardCharsets.UTF_8);
        final ResponseEntity<ReportResponse> result = aaRestClient.post("/reports", request, ReportResponse.class);
        //final Path path = Path.of(this.getClass().getClassLoader().getResource("json/reportsResponse.json").getPath());
        //final String response = Files.readString(path, StandardCharsets.UTF_8);
        //final ReportsResponse result  = objectMapper.readValue(response, ReportsResponse.class);
        assertNotNull(result);
    }

    @Test
    public void shouldCallAASegment() {
        final ResponseEntity<SegmentResponse> result = aaRestClient.get("/segments/s300000520_5c4fa6584618f618e5cdbe32", SegmentResponse.class);
        assertNotNull(result.getBody());
    }
}
