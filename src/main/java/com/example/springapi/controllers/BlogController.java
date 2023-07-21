package com.example.springapi.controllers;

import com.example.springapi.models.requests.RegisterRequest;
import com.example.springapi.models.responses.AuthenticationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/blog")
@RequiredArgsConstructor
public class BlogController {

    @GetMapping("/ping")
    public ResponseEntity<String> ping(){
        return ResponseEntity.ok("Ping from secured End Point 🔐");
    }
}
