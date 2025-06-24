package com.dsena7.short_url.exceptions;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
@Builder
@Data
public class ErrorResponse{

    private String message;
    private LocalDateTime timestamp = LocalDateTime.now();
}
