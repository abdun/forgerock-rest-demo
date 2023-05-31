package com.forgerock.amdemo.service;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

public class IdentityServiceImpl implements IdentityProcessor {
    private final RestTemplate restTemplate = new RestTemplate();
    public ResponseEntity<String> process(Map<String,String> hashMap) throws Exception {
        String userName = hashMap.get("Username");
        String password = hashMap.get("Password");
        String apiVersion = hashMap.get("Accept-API-version");
        String resourceUrl = hashMap.get("resourceurl");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Username", userName);
        headers.set("Password", password);
        if (apiVersion != null) {
            headers.set("Accept-API-version", apiVersion);
        }
        HttpEntity<String> requestEntity = new HttpEntity<>(null,headers);
        try {
            ResponseEntity<String> responseEntity =
                    restTemplate.exchange(resourceUrl, HttpMethod.POST, requestEntity, String.class);
            return responseEntity;
        } catch (Exception exception) {
            throw new Exception(exception.getMessage());
        }

    }
}
