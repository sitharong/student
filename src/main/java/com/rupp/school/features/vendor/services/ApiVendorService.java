package com.rupp.school.features.vendor.services;

import com.rupp.school.app.models.ApiVendorResponseModel.ApiVendorNews;

public interface ApiVendorService {
    /** get news info */
    ApiVendorNews getNewsData();
}