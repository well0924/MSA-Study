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
    public String sampleUpdate(SampleDTO sampleDto) {
        log.info("sampleUpdate data : {}", sampleDto.toString());
        sampleRepository.save(SampleEntity.builder()
                        .id(sampleDto.getId())
                        .content(sampleDto.getContent())
                        .build());
        return "update success";
    }

    @Transactional
    public String sampleInsert(SampleDTO sampleDto) {
        log.info("sampleInsert data : {}", sampleDto.toString());
        sampleRepository.save(SampleEntity.builder()
                .content(sampleDto.getContent())
                .build());
        return "insert success";
    }

    @Transactional
    public void sampleDelete(Long id) {
        sampleRepository.deleteById(id);
    }
}
