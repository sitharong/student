package com.acleda.student.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.acleda.student.response.ResponseModel;
import com.acleda.student.response.ResponseService;
import com.acleda.student.services.ApiVendorService;

/**
 * REST controller for handling API vendor-related endpoints.
 */
@RestController
@RequestMapping("/api/v1/apivendor")
public class ApiVendorController {

    @Autowired
    private ApiVendorService apiVendorService;

    @Autowired
    private ResponseService responseService;

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