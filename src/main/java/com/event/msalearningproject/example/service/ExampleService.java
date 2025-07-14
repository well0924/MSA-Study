package com.event.msalearningproject.example.service;

import com.event.msalearningproject.example.dto.SampleDTO;
import com.event.msalearningproject.example.entity.SampleEntity;
import com.event.msalearningproject.example.repository.SampleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ExampleService {

    final private SampleRepository sampleRepository;

    public String sampleSelect(Long id) {
        return sampleRepository.findById(id).toString();
    }

    @Transactional
    public String sampleSave(SampleDTO sampleDto) {

        log.info("sampleSave data : {}", sampleDto.toString());

        sampleRepository.save(SampleEntity.builder()
                        .content(sampleDto.getContent())
                        .build());
        return "hello world";
    }

    @Transactional
    public void sampleDelete(Long id) {
        sampleRepository.deleteById(id);
    }
}
