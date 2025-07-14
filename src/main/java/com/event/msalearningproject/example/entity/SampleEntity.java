package com.event.msalearningproject.example.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Table(name = "sample_test")
@Entity
@Builder
@RequiredArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor
public class SampleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;
}
