package com.forgerock.amdemo.controller;

//import javax.validation.Valid;
import javax.validation.constraints.NotNull;

//import io.swagger.v3.oas.annotations.OpenAPIDefinition;
//import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.beans.factory.annotation.Value;
import com.forgerock.amdemo.service.IdentityProcessor;
import com.forgerock.amdemo.service.IdentityServiceImpl;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

//import io.swagger.annotations.ApiOperation;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/v1")
public class IdentityController {
    @Value("${app.resourceurl}")
    private String resourceUrl;

    private final IdentityProcessor identityProcessor = new IdentityServiceImpl();


    @ApiResponses(value = {
        @ApiResponse( code = 200, message = "Successfully user information has been retrieved"),
        @ApiResponse( code = 400, message = "Bad Request - the request can't be fulfilled due to an error"),
        @ApiResponse( code = 401, message = "Unauthorized"),
        @ApiResponse( code = 403, message = "Forbidden"),
        @ApiResponse( code = 404, message = "Not found"),
        @ApiResponse( code = 500, message = "Internal Server Error")
    })


    @PostMapping(value = "/identity", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> extractIdentity(
        @NotNull @RequestHeader(value = "Username", required = true) String userName,
        @NotNull @RequestHeader(value = "Password", required = true) String password,
        @RequestHeader(value = "Accept-API-version", required = false) String apiVersion) throws Exception {
        System.out.println("resourceUrl ::" + resourceUrl);
        Map<String,String> hashMap = new HashMap<>();
        hashMap.put("Username", userName);
        hashMap.put("Password", password);
        hashMap.put("Accept-API-version", apiVersion);
        hashMap.put("resourceurl", resourceUrl);
        ResponseEntity<String> responseEntity = identityProcessor.process(hashMap);
        return responseEntity;
    }
}
