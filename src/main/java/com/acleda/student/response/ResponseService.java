package com.acleda.student.response;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class ResponseService {

    public ResponseModel ok(Object data) {
        return new ResponseModel(HttpStatus.OK, data, null);
    }

    public ResponseModel error(Object error, HttpStatus status) {
        return new ResponseModel(status, null, error);
    }

}
// This service class provides a method to create a successful response model.
// It uses the `ResponseModel` class to encapsulate the response data and
// status.