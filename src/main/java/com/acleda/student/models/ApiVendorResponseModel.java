package com.acleda.student.models;

public interface ApiVendorResponseModel {

    public record ApiVendorNews(int userId, int id, String title, String body) {
    }

}
