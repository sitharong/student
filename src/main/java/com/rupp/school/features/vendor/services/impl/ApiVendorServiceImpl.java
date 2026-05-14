package com.rupp.school.features.vendor.services.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.rupp.school.core.models.ApiVendorResponseModel.ApiVendorNews;
import com.rupp.school.features.vendor.services.ApiVendorService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ApiVendorServiceImpl implements ApiVendorService {
    private final WebClient webClient;

    public ApiVendorNews getNewsData() {
        return webClient.get()
                .uri("https://jsonplaceholder.typicode.com/posts/1")
                .retrieve()
                .bodyToMono(ApiVendorNews.class)
                .block(); // For synchronous call, use .block()
    }
}