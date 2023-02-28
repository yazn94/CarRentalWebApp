package com.atypon.authenticationservice;


import org.springframework.web.bind.annotation.*;

@RestController
public class AuthenticationServiceController {
    @PostMapping("/authentication")
    public boolean authenticate(@RequestBody UserInfo userInfo) {
        return userInfo.getEmail().equals("admin1@gmail.com") && userInfo.getPassword().equals("123456");
    }
}