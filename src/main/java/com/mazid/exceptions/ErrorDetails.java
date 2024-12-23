package com.mazid.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

// ErrorDetails class represents the structure of the error response to be sent to the client
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDetails {

    // A message describing the error
    private String message;

    // A specific error code or type
    private String error;

    // Timestamp indicating when the error occurred
    private LocalDateTime timestamp;
}
