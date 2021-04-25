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
        headers.setBearerAuth("eyJhbGciOiJSUzI1NiIsIng1dSI6Imltc19uYTEta2V5LTEuY2VyIn0.eyJpZCI6IjE2MTkzNzk5OTkwNzhfZTlhZTgyOGItYzhlZi00Njg4LWI1YWMtZjUyMzJlNjlkM2RiX3VlMSIsImNsaWVudF9pZCI6IjVhOGRjYzJjZmE3MTQ3MmNiZmE0ZmI1MzY3MWM0NWVkIiwidXNlcl9pZCI6IjY4OEQ0QTQyNUYyMDNGMzAwQTQ5NUM1OEAzNzViMjgyMzVjMDUwNTE2MGE0OTVjNGYiLCJzdGF0ZSI6IiIsInR5cGUiOiJhY2Nlc3NfdG9rZW4iLCJhcyI6Imltcy1uYTEiLCJhYV9pZCI6IjY4OEQ0QTQyNUYyMDNGMzAwQTQ5NUM1OEAzNzViMjgyMzVjMDUwNTE2MGE0OTVjNGYiLCJmZyI6IlZNSlVGNkE2RkxPNU5INEdDTVpMUkhRQUtNPT09PT09Iiwic2lkIjoiMTYxOTM3OTk5ODUxOV80NTcxODFjOS0wMzM3LTQ0NmQtYjdiMS0wNjQzYWM3MTNhYmVfdWUxIiwibW9pIjoiODgyMGY1MjgiLCJydGVhIjoiMTYyMDU4OTU5OTA3OCIsIm9jIjoicmVuZ2EqbmExcioxNzkwYTkxY2RlMCowVldSMVJFSFdYNEREQzZNRTFCVjVYSjM3NCIsInJ0aWQiOiIxNjE5Mzc5OTk5MDc4XzExZTNjN2JlLWIwNmUtNDIwMS1hZjA4LTdmYmI1YmE2YWJiM191ZTEiLCJleHBpcmVzX2luIjoiODY0MDAwMDAiLCJzY29wZSI6Im9wZW5pZCxBZG9iZUlELHJlYWRfb3JnYW5pemF0aW9ucyxhZGRpdGlvbmFsX2luZm8ucHJvamVjdGVkUHJvZHVjdENvbnRleHQsYWRkaXRpb25hbF9pbmZvLmpvYl9mdW5jdGlvbiIsImNyZWF0ZWRfYXQiOiIxNjE5Mzc5OTk5MDc4In0.m5d6Ry2VJoc0DwyWhJNX5_NsmtW0HQdm3NNnuVFIBOIKmREocxJJRkRzkqW-_syFd_RzcoyNnNRLf1TLkTPEmIITJW-wTdCd2Hb4cvNW8RtNlb0uijyN7oQDdjJMTwmdwCn1Nv4N1NmiQSMP_BSr766_GCNdF6VvtiXJCoqcLKiZLuB3b2JlNYUF93BOcVQwrallAmw7-iqkY6g_7pg4_6raV3nQgWFncjkfdpJmZAubNXde_fqKaGnbDjYy-nyvztauDUZvId93DjmcyqN0FS9dkpjRn1f6PWjRC0fDYRmQsZWUplpVs0sVi0n72CwYsGuqeuizk3gy2iAqC-O2zA");
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("x-api-key", "5a8dcc2cfa71472cbfa4fb53671c45ed");
        headers.set("x-proxy-global-company-id", "elsevi3");
        return headers;
    }
}
