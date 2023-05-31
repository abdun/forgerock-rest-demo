package com.forgerock.amdemo.service;

import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface IdentityProcessor {
    public ResponseEntity<String> process(Map<String,String> hashMap) throws Exception;
}
