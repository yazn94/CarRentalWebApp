package com.atypon.showresults.secondaryClasses;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

@Controller
public class AuthenticationService {
    public boolean authenticate(UserInfo userInfo) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://auth:8080/authentication"; // the actual URL of the authentication service endpoint
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<UserInfo> request = new HttpEntity<>(userInfo, headers);
        ResponseEntity<Boolean> response = restTemplate.postForEntity(url, request, Boolean.class);
        return response.getBody();
    }
}