package com.acleda.student.response;

import org.springframework.http.HttpStatus;

public record ResponseModel(HttpStatus status, Object data, Object error) {
}
// This class is a model for responses, typically used in REST APIs.
// It contains fields for status, data, and error, which can be used to
// standardize the structure of responses sent back to clients.