package com.event.msalearningproject.example.controller;

import com.event.msalearningproject.example.dto.SampleDTO;
import com.event.msalearningproject.example.service.ExampleService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/msa/v1")
public class ExampleController {

    final private ExampleService exampleService;

    @GetMapping("/sample")
    @Operation(summary = "Example API", description = "Example API  to Select Sample Data")
    public ResponseEntity<String> sampleSelect(Long id) {
        log.info("sampleSelect API Start");
        return ResponseEntity.ok(exampleService.sampleSelect(id));
    }

    @Operation(summary = "Example API", description = "Example API to Save Sample Data")
    @PostMapping("/sample")
    public ResponseEntity<String> sampleSave() {
        log.info("sampleTest API Start");
        return ResponseEntity.ok(
                exampleService.sampleSave(
                        SampleDTO.builder()
                                 .id(1L)
                                 .content("This is a save test")
                                 .build()
                ));
    }

    @DeleteMapping("/sample")
    @Operation(summary = "Example API", description = "Example API to Delete Sample Data")
    public ResponseEntity<String> sampleDelete(Long id) {
        log.info("sampleDelete API Start");
        exampleService.sampleDelete(id);
        return ResponseEntity.ok("OK");
    }
}
