package com.rupp.student.services.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.rupp.student.models.ApiVendorResponseModel.ApiVendorNews;
import com.rupp.student.services.ApiVendorService;

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