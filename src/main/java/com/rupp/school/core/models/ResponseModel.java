package com.rupp.school.core.models;

import org.springframework.http.HttpStatus;

/**
 * Model for standardized API responses.
 * Contains fields for HTTP status, response data, and error details.
 * Used to provide a consistent response structure for REST APIs.
 *
 * @param status the HTTP status of the response
 * @param data   the response data (can be any object)
 * @param error  the error details (can be any object, or null if no error)
 */
public record ResponseModel(HttpStatus status, boolean isSuccess, String msg, Object data) {
}
// This class is a model for responses, typically used in REST APIs.
// It contains fields for status, isSuccess, data, and error, which can be used
// to standardize the structure of responses sent back to clients.