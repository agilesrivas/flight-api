package com.utn.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class ApiConnector {

    @Autowired
    RestTemplate restTemplate;

    private static final String BASE_URL = "http://localhost:25100";

}
