package com.elsevier.aaharvester.client;

import com.elsevier.aaharvester.AaHarvesterApplication;
import com.elsevier.aaharvester.web.aa.response.ReportResponse;
import com.elsevier.aaharvester.web.aa.response.SegmentResponse;
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
        final String token = "eyJhbGciOiJSUzI1NiIsIng1dSI6Imltc19uYTEta2V5LTEuY2VyIn0.eyJpZCI6IjE2MTg4MzYzMjQxNjdfZWVlNWY2NmItZTgxNy00M2ZhLWFjOGEtZjNkMjI5NGFkZjgzX3VlMSIsImNsaWVudF9pZCI6IjVhOGRjYzJjZmE3MTQ3MmNiZmE0ZmI1MzY3MWM0NWVkIiwidXNlcl9pZCI6IjY4OEQ0QTQyNUYyMDNGMzAwQTQ5NUM1OEAzNzViMjgyMzVjMDUwNTE2MGE0OTVjNGYiLCJzdGF0ZSI6IiIsInR5cGUiOiJhY2Nlc3NfdG9rZW4iLCJhcyI6Imltcy1uYTEiLCJhYV9pZCI6IjY4OEQ0QTQyNUYyMDNGMzAwQTQ5NUM1OEAzNzViMjgyMzVjMDUwNTE2MGE0OTVjNGYiLCJmZyI6IlZMWDUyT0lQRkxPNU5INEdDTVpMUkhRQUtNPT09PT09Iiwic2lkIjoiMTYxODgzNjMyMzY5OF9lMDJiMzcxNS1kMzY4LTRkNTEtOGFjZS0zNzlmYWQ0YmYwN2JfdWUxIiwibW9pIjoiZjVhZjYxMDgiLCJydGVhIjoiMTYyMDA0NTkyNDE2NyIsIm9jIjoicmVuZ2EqbmExcioxNzhlYTI5ZmM0MSpCU1lQTldXRzUxMlhaM0M3WkFTM1ZONlJETSIsInJ0aWQiOiIxNjE4ODM2MzI0MTY3XzliMmI5NWY3LWY4YzAtNDA3OS1iNTU3LTcwNmE4NDE4YzIyOF91ZTEiLCJleHBpcmVzX2luIjoiODY0MDAwMDAiLCJjcmVhdGVkX2F0IjoiMTYxODgzNjMyNDE2NyIsInNjb3BlIjoib3BlbmlkLEFkb2JlSUQscmVhZF9vcmdhbml6YXRpb25zLGFkZGl0aW9uYWxfaW5mby5wcm9qZWN0ZWRQcm9kdWN0Q29udGV4dCxhZGRpdGlvbmFsX2luZm8uam9iX2Z1bmN0aW9uIn0.cWvhDJ0OGReJT3EaFA179--eSekjZUs56Rg67EJi-WliYTTUcowds9gfddqQpw5KTMq9WUQvnT7EQefVXGJDYBTFYpxNrRWdeAVV9n6v_-eZsIb5kYlbJ7z-Dkw8wX5NE-3c6fa-OYpJ2n1APpqeE3hhzz2G6t_UokTZKxC6HwRQ4AbfGOuln8yyz8n74tQb6dSCPpYpJDI__DIh9lOnxlPXkIdNZvG_BeL0oacjIg2J16kDH6x9UIN7mjjb8wNq_E1ahwg-EIH3wwa9m_XRZkUZMD0NnuclbnAhrQ4-aT1g0sHrxaxu0XyB8Xa35GmQOJX9rdWIb-1EIuOfUOAIfw";
        final Path path = Path.of(this.getClass().getClassLoader().getResource("json/reportsRequest.json").getPath());
        final String request = Files.readString(path, StandardCharsets.UTF_8);
        final ResponseEntity<ReportResponse> result = aaRestClient.post("/reports", token, request, ReportResponse.class);
        //final Path path = Path.of(this.getClass().getClassLoader().getResource("json/reportsResponse.json").getPath());
        //final String response = Files.readString(path, StandardCharsets.UTF_8);
        //final ReportsResponse result  = objectMapper.readValue(response, ReportsResponse.class);
        assertNotNull(result);
    }

    @Test
    public void shouldCallAASegment() {
        final String token = "eyJhbGciOiJSUzI1NiIsIng1dSI6Imltc19uYTEta2V5LTEuY2VyIn0.eyJpZCI6IjE2MTg4MzYzMjQxNjdfZWVlNWY2NmItZTgxNy00M2ZhLWFjOGEtZjNkMjI5NGFkZjgzX3VlMSIsImNsaWVudF9pZCI6IjVhOGRjYzJjZmE3MTQ3MmNiZmE0ZmI1MzY3MWM0NWVkIiwidXNlcl9pZCI6IjY4OEQ0QTQyNUYyMDNGMzAwQTQ5NUM1OEAzNzViMjgyMzVjMDUwNTE2MGE0OTVjNGYiLCJzdGF0ZSI6IiIsInR5cGUiOiJhY2Nlc3NfdG9rZW4iLCJhcyI6Imltcy1uYTEiLCJhYV9pZCI6IjY4OEQ0QTQyNUYyMDNGMzAwQTQ5NUM1OEAzNzViMjgyMzVjMDUwNTE2MGE0OTVjNGYiLCJmZyI6IlZMWDUyT0lQRkxPNU5INEdDTVpMUkhRQUtNPT09PT09Iiwic2lkIjoiMTYxODgzNjMyMzY5OF9lMDJiMzcxNS1kMzY4LTRkNTEtOGFjZS0zNzlmYWQ0YmYwN2JfdWUxIiwibW9pIjoiZjVhZjYxMDgiLCJydGVhIjoiMTYyMDA0NTkyNDE2NyIsIm9jIjoicmVuZ2EqbmExcioxNzhlYTI5ZmM0MSpCU1lQTldXRzUxMlhaM0M3WkFTM1ZONlJETSIsInJ0aWQiOiIxNjE4ODM2MzI0MTY3XzliMmI5NWY3LWY4YzAtNDA3OS1iNTU3LTcwNmE4NDE4YzIyOF91ZTEiLCJleHBpcmVzX2luIjoiODY0MDAwMDAiLCJjcmVhdGVkX2F0IjoiMTYxODgzNjMyNDE2NyIsInNjb3BlIjoib3BlbmlkLEFkb2JlSUQscmVhZF9vcmdhbml6YXRpb25zLGFkZGl0aW9uYWxfaW5mby5wcm9qZWN0ZWRQcm9kdWN0Q29udGV4dCxhZGRpdGlvbmFsX2luZm8uam9iX2Z1bmN0aW9uIn0.cWvhDJ0OGReJT3EaFA179--eSekjZUs56Rg67EJi-WliYTTUcowds9gfddqQpw5KTMq9WUQvnT7EQefVXGJDYBTFYpxNrRWdeAVV9n6v_-eZsIb5kYlbJ7z-Dkw8wX5NE-3c6fa-OYpJ2n1APpqeE3hhzz2G6t_UokTZKxC6HwRQ4AbfGOuln8yyz8n74tQb6dSCPpYpJDI__DIh9lOnxlPXkIdNZvG_BeL0oacjIg2J16kDH6x9UIN7mjjb8wNq_E1ahwg-EIH3wwa9m_XRZkUZMD0NnuclbnAhrQ4-aT1g0sHrxaxu0XyB8Xa35GmQOJX9rdWIb-1EIuOfUOAIfw";
        final ResponseEntity<SegmentResponse> result = aaRestClient.get("/segments/s300000520_5c4fa6584618f618e5cdbe32", token, SegmentResponse.class);
        assertNotNull(result.getBody());
    }
}
