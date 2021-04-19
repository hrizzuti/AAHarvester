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
                                      final String token,
                                      final String request,
                                      final Class<T> responseType) {

        final HttpHeaders headers = setHttpHeaders(token);
        final HttpEntity<String> entity = new HttpEntity(request, headers);
        return restTemplate.exchange(url + endpoint, HttpMethod.POST, entity, responseType);
    }

    public <T> ResponseEntity<T> get(final String endpoint,
                                     final String token,
                                     final Class<T> responseType) {

        final HttpHeaders headers = setHttpHeaders(token);
        final HttpEntity<String> entity = new HttpEntity(headers);
        return restTemplate.exchange(url + endpoint, HttpMethod.GET, entity, responseType);
    }

    private HttpHeaders setHttpHeaders(final String token) {
        final HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("x-api-key", "5a8dcc2cfa71472cbfa4fb53671c45ed");
        headers.set("x-proxy-global-company-id", "elsevi3");
        return headers;
    }
}
