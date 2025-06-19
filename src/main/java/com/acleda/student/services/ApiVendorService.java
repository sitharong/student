package com.acleda.student.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.acleda.student.models.ApiVendorResponseModel.ApiVendorNews;

@Service
public class ApiVendorService {

    @Autowired
    private WebClient webClient;

    public ApiVendorNews getNewsData() {
        return webClient.get()
                .uri("https://jsonplaceholder.typicode.com/posts/1")
                .retrieve()
                .bodyToMono(ApiVendorNews.class)
                .block(); // For synchronous call, use .block()
    }
}
