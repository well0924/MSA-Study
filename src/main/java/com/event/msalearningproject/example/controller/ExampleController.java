package com.event.msalearningproject.example.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/msa/v1")
public class ExampleController {

    @Operation(summary = "Example API", description = "Example API")
    @GetMapping("/sample")
    public ResponseEntity<String> sampleTest() {
        log.info("sampleTest API Start");
        return ResponseEntity.ok("hello world");
    }
}
