package com.unosquare.cvgenerator.exception;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ControllerAdvice
@Order
public class GlobalExceptionHandler {

    /**
     * Catch all exception handler
     */
    @ExceptionHandler(Exception.class)
    private ResponseEntity<ErrorResponse> handleException(Exception ex) {

        if (ex.getCause() != null) {
            log.error("An error occurred: " + ex.getMessage(), ex);
        } else {
            log.error("An error occurred: " + ex.getMessage());
        }

        Class<?> responseStatusAnnotation = AnnotationUtils.findAnnotationDeclaringClass(ResponseStatus.class, ex.getClass());
        if (responseStatusAnnotation != null && responseStatusAnnotation.getAnnotations().length > 0) {
            ResponseStatus responseStatus = (ResponseStatus) responseStatusAnnotation.getAnnotations()[0];

            return ResponseEntity
                    .status(responseStatus.code())
                    .body(new ErrorResponse(ex.getMessage()));
        }

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorResponse(ex.getMessage()));
    }

    @Data
    private class ErrorResponse {
        private final String message;
    }

}
