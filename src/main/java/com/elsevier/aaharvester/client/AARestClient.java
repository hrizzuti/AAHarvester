package com.elsevier.aaharvester.client;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Component
public class AARestClient {

    private RestTemplate restTemplate;

    final String url = "https://analytics.adobe.io/api/elsevi3";

    public AARestClient(final RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public <T> ResponseEntity<T> post(final String endpoint,
                                      final String request,
                                      final Class<T> responseType) {

        final HttpHeaders headers = setHttpHeaders();
        final HttpEntity<String> entity = new HttpEntity(request, headers);
        return restTemplate.exchange(url + endpoint, HttpMethod.POST, entity, responseType);
    }

    public <T> ResponseEntity<T> get(final String endpoint,
                                     final Class<T> responseType) {

        final HttpHeaders headers = setHttpHeaders();
        final HttpEntity<String> entity = new HttpEntity(headers);
        return restTemplate.exchange(url + endpoint, HttpMethod.GET, entity, responseType);
    }

    private HttpHeaders setHttpHeaders() {
        final HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth("eyJhbGciOiJSUzI1NiIsIng1dSI6Imltc19uYTEta2V5LTEuY2VyIn0.eyJpZCI6IjE2MTkwODU2Mzc1NTJfYTU0ODc3NTEtNWQ3NS00MDBjLWFjNTMtODljNjBjMTcwMWM4X3VlMSIsImNsaWVudF9pZCI6IjVhOGRjYzJjZmE3MTQ3MmNiZmE0ZmI1MzY3MWM0NWVkIiwidXNlcl9pZCI6IjY4OEQ0QTQyNUYyMDNGMzAwQTQ5NUM1OEAzNzViMjgyMzVjMDUwNTE2MGE0OTVjNGYiLCJzdGF0ZSI6IiIsInR5cGUiOiJhY2Nlc3NfdG9rZW4iLCJhcyI6Imltcy1uYTEiLCJhYV9pZCI6IjY4OEQ0QTQyNUYyMDNGMzAwQTQ5NUM1OEAzNzViMjgyMzVjMDUwNTE2MGE0OTVjNGYiLCJmZyI6IlZNQUJSNkVPRkxPNU5INEdDTVpMUkhRQUtNPT09PT09Iiwic2lkIjoiMTYxOTA4NTYzNzA2OV84OWQ1MmQ4OC00M2EyLTRhOTItYTY5Yi03MjQ3MTFhOTQxNjNfdWUxIiwibW9pIjoiNTcxOTI0ZTYiLCJvYyI6InJlbmdhKm5hMXIqMTc4ZjkwNjM0OGIqWERCRUdEREs5UzVZWEU0Qks3VEtLS1lFUTAiLCJydGVhIjoiMTYyMDI5NTIzNzU1MiIsInJ0aWQiOiIxNjE5MDg1NjM3NTUyXzA1MzlkZTg0LWUwOWYtNGZkZS04MjUzLTY4NmY3YjdhOTQzNF91ZTEiLCJleHBpcmVzX2luIjoiODY0MDAwMDAiLCJzY29wZSI6Im9wZW5pZCxBZG9iZUlELHJlYWRfb3JnYW5pemF0aW9ucyxhZGRpdGlvbmFsX2luZm8ucHJvamVjdGVkUHJvZHVjdENvbnRleHQsYWRkaXRpb25hbF9pbmZvLmpvYl9mdW5jdGlvbiIsImNyZWF0ZWRfYXQiOiIxNjE5MDg1NjM3NTUyIn0.XjKdzYJSM_aTl_j-LQ4SYbkxQqhN9cLytNdBK-7ekR1DOqgbhXisJz-X9dyxkuoe3c0prTFVZwx90rV7NFnFCx7p3ODlJkCPt93Vw-DPxeTsSR6UDI7-n_7V7WydMXA3OxsZEBaULZKm68HQZ0BntJMR5HFy6m9AuLmoP3bGHlobjEr4XyDDERsvL6TupN2uChq3uL0QUUIgJP6GkVrDRTbRqREYTbtJudRXD9zmJFY8dE6k7ZIzXrqXCUj-d6fDgPYDzEkbvqvtDdoz1UmZrba7Ht-4tKKE8HZB8f2vldb4bowhSgUSdIm6-HkKMfGEmWY_AVbeCtHOBMSuEoboSQ");
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("x-api-key", "5a8dcc2cfa71472cbfa4fb53671c45ed");
        headers.set("x-proxy-global-company-id", "elsevi3");
        return headers;
    }
}
