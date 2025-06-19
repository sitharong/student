package com.acleda.student.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.acleda.student.models.ApiVendorResponseModel.ApiVendorNews;
import com.acleda.student.services.ApiVendorService;

@RestController
@RequestMapping("/api/v1/apivendor")
public class ApiVendorController {

    @Autowired
    private ApiVendorService apiVendorService;

    @GetMapping("/news")
    public ApiVendorNews getNews() {
        return apiVendorService.getNewsData();
    }
}
