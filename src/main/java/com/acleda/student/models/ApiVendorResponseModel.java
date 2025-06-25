package com.acleda.student.models;

/**
 * Marker interface for API vendor response models.
 * Can be used to group different response record types from external APIs.
 */
public interface ApiVendorResponseModel {

    /**
     * Record representing news data from the API vendor.
     * 
     * @param userId the user ID associated with the news
     * @param id     the news ID
     * @param title  the news title
     * @param body   the news content/body
     */
    public record ApiVendorNews(int userId, int id, String title, String body) {
    }

}