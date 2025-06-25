package com.acleda.student.response;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

/**
 * Service for building standardized API response models.
 * Provides helper methods to create success and error responses
 * using the ResponseModel record.
 */
@Service
public class ResponseService {

    /**
     * Creates a successful response with HTTP 200 OK status.
     * 
     * @param data the response data to include
     * @return a ResponseModel with status OK and the provided data
     */
    public ResponseModel ok(Object data) {
        return new ResponseModel(HttpStatus.OK, data, null);
    }

    /**
     * Creates an error response with the specified HTTP status.
     * 
     * @param error  the error details to include
     * @param status the HTTP status to set for the response
     * @return a ResponseModel with the given status and error
     */
    public ResponseModel error(Object error, HttpStatus status) {
        return new ResponseModel(status, null, error);
    }

}
// This service class provides a method to create a successful response model.
// It uses the `ResponseModel` class to encapsulate the response data and
// status.