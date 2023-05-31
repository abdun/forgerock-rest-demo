package com.forgerock.amdemo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/v1")
public class TestController {

    @GetMapping(value = "/hello", produces = "application/json")
    public ResponseEntity<String> getHello() {

        return new ResponseEntity<String>("Hello there", HttpStatus.OK);
    }
    @GetMapping("/login")
    public String login(){
        return "login";
    }
}
