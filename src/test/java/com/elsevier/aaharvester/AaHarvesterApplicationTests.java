package com.elsevier.aaharvester;

import com.elsevier.aaharvester.client.AARestClient;
import com.elsevier.aaharvester.model.enumerator.ReportFiltersType;
import com.elsevier.aaharvester.web.aa.request.ReportsRequest;
import com.elsevier.aaharvester.web.aa.response.SegmentResponse;
import com.elsevier.aaharvester.web.aa.util.RequestMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import javax.annotation.Resource;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

@SpringBootTest
class AaHarvesterApplicationTests {

    @Resource
    private AARestClient aaRestClient;

    @Test
    public void shouldConvertJsonToRequest() throws IOException {
        final RequestMapper mapper = new RequestMapper(new ObjectMapper());
        final Path path = Path.of(this.getClass().getClassLoader().getResource("json/reportsRequest.json").getPath());
        final String jsonRequest = Files.readString(path, StandardCharsets.UTF_8);
        final ReportsRequest reportsRequest = mapper.toAAReports(jsonRequest);
        reportsRequest.getGlobalFilters().forEach(g -> {
            if (ReportFiltersType.SEGMENT == g.getType()) {
                final String token = "eyJhbGciOiJSUzI1NiIsIng1dSI6Imltc19uYTEta2V5LTEuY2VyIn0.eyJpZCI6IjE2MTg4MzYzMjQxNjdfZWVlNWY2NmItZTgxNy00M2ZhLWFjOGEtZjNkMjI5NGFkZjgzX3VlMSIsImNsaWVudF9pZCI6IjVhOGRjYzJjZmE3MTQ3MmNiZmE0ZmI1MzY3MWM0NWVkIiwidXNlcl9pZCI6IjY4OEQ0QTQyNUYyMDNGMzAwQTQ5NUM1OEAzNzViMjgyMzVjMDUwNTE2MGE0OTVjNGYiLCJzdGF0ZSI6IiIsInR5cGUiOiJhY2Nlc3NfdG9rZW4iLCJhcyI6Imltcy1uYTEiLCJhYV9pZCI6IjY4OEQ0QTQyNUYyMDNGMzAwQTQ5NUM1OEAzNzViMjgyMzVjMDUwNTE2MGE0OTVjNGYiLCJmZyI6IlZMWDUyT0lQRkxPNU5INEdDTVpMUkhRQUtNPT09PT09Iiwic2lkIjoiMTYxODgzNjMyMzY5OF9lMDJiMzcxNS1kMzY4LTRkNTEtOGFjZS0zNzlmYWQ0YmYwN2JfdWUxIiwibW9pIjoiZjVhZjYxMDgiLCJydGVhIjoiMTYyMDA0NTkyNDE2NyIsIm9jIjoicmVuZ2EqbmExcioxNzhlYTI5ZmM0MSpCU1lQTldXRzUxMlhaM0M3WkFTM1ZONlJETSIsInJ0aWQiOiIxNjE4ODM2MzI0MTY3XzliMmI5NWY3LWY4YzAtNDA3OS1iNTU3LTcwNmE4NDE4YzIyOF91ZTEiLCJleHBpcmVzX2luIjoiODY0MDAwMDAiLCJjcmVhdGVkX2F0IjoiMTYxODgzNjMyNDE2NyIsInNjb3BlIjoib3BlbmlkLEFkb2JlSUQscmVhZF9vcmdhbml6YXRpb25zLGFkZGl0aW9uYWxfaW5mby5wcm9qZWN0ZWRQcm9kdWN0Q29udGV4dCxhZGRpdGlvbmFsX2luZm8uam9iX2Z1bmN0aW9uIn0.cWvhDJ0OGReJT3EaFA179--eSekjZUs56Rg67EJi-WliYTTUcowds9gfddqQpw5KTMq9WUQvnT7EQefVXGJDYBTFYpxNrRWdeAVV9n6v_-eZsIb5kYlbJ7z-Dkw8wX5NE-3c6fa-OYpJ2n1APpqeE3hhzz2G6t_UokTZKxC6HwRQ4AbfGOuln8yyz8n74tQb6dSCPpYpJDI__DIh9lOnxlPXkIdNZvG_BeL0oacjIg2J16kDH6x9UIN7mjjb8wNq_E1ahwg-EIH3wwa9m_XRZkUZMD0NnuclbnAhrQ4-aT1g0sHrxaxu0XyB8Xa35GmQOJX9rdWIb-1EIuOfUOAIfw";
                final ResponseEntity<SegmentResponse> segmentResponses = aaRestClient.get("/segments/" + g.getSegmentId(), token, SegmentResponse.class);
                segmentResponses.getBody();
            }
        });
    }

}
