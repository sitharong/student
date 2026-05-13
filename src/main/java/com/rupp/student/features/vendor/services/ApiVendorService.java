package com.rupp.student.features.vendor.services;

import com.rupp.student.app.models.ApiVendorResponseModel.ApiVendorNews;

public interface ApiVendorService {
    /** get news info */
    ApiVendorNews getNewsData();
}