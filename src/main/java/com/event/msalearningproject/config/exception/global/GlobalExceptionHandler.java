package com.event.msalearningproject.config.exception.global;

import com.event.msalearningproject.config.exception.dto.ErrorCode;
import com.event.msalearningproject.config.exception.dto.GlobalErrorResponseDto;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<GlobalErrorResponseDto> handleApiException(ApiException ex) {
        ErrorCode code = ex.getErrorCode();

        GlobalErrorResponseDto response = GlobalErrorResponseDto.builder()
                .status(code.getStatus().value())
                .errorCode(code.getCode())
                .message(code.getMessage())
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.status(code.getStatus()).body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<GlobalErrorResponseDto> handleValidationException(MethodArgumentNotValidException ex) {
        String errorMessage = ex.getBindingResult().getFieldErrors().stream()
                .map(err -> err.getField() + ": " + err.getDefaultMessage())
                .collect(Collectors.joining(", "));

        GlobalErrorResponseDto response = GlobalErrorResponseDto.builder()
                .status(400)
                .errorCode("VALIDATION_ERROR")
                .message(errorMessage)
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<GlobalErrorResponseDto> handleDataAccessException(DataAccessException ex) {
        GlobalErrorResponseDto response = GlobalErrorResponseDto.builder()
                .status(500)
                .errorCode(ErrorCode.DATABASE_ERROR.getCode())
                .message(ErrorCode.DATABASE_ERROR.getMessage())
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.internalServerError().body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<GlobalErrorResponseDto> handleAll(Exception ex) {
        GlobalErrorResponseDto response = GlobalErrorResponseDto.builder()
                .status(500)
                .errorCode(ErrorCode.INTERNAL_SERVER_ERROR.getCode())
                .message(ErrorCode.INTERNAL_SERVER_ERROR.getMessage())
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.internalServerError().body(response);
    }
}
