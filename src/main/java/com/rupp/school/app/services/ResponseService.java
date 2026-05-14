package com.rupp.school.app.services;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rupp.school.core.models.ResponseModel;

import lombok.RequiredArgsConstructor;

/**
 * Service for building standardized API response models.
 * Provides helper methods to create success and error responses
 * using the ResponseModel record.
 */
@Service
@RequiredArgsConstructor
public class ResponseService {
    private final ObjectMapper mapper;

    private String serialize(ResponseModel res) {
        try {
            return mapper.writeValueAsString(res);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public String success(Object data) {
        return success(data, "");
    }

    public String success(Object data, String msg) {
        var res = new ResponseModel(HttpStatus.OK, true, msg, data);
        return serialize(res);
    }

    public String error(HttpStatus status, Object error) {
        var res = new ResponseModel(status, false, error.toString(), null);
        return serialize(res);
    }
}