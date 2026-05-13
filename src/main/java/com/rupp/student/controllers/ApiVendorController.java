package com.rupp.student.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rupp.student.response.ResponseModel;
import com.rupp.student.response.ResponseService;
import com.rupp.student.services.ApiVendorService;

import lombok.RequiredArgsConstructor;

/**
 * REST controller for handling API vendor-related endpoints.
 */
@RestController
@RequestMapping("/api/v1/apivendor")
@RequiredArgsConstructor
public class ApiVendorController {
    private final ApiVendorService apiVendorService;
    private final ResponseService responseService;

    /**
     * Retrieves news data from the API vendor service.
     * 
     * @return a ResponseModel containing the news data
     */
    @GetMapping("/news")
    public ResponseModel getNews() {
        var res = apiVendorService.getNewsData();
        return responseService.ok(res);
    }
}