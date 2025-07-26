package com.event.msalearningproject.config.exception.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GlobalErrorResponseDto {
    private int status;
    private String errorCode;
    private String message;
    private LocalDateTime timestamp;
}
