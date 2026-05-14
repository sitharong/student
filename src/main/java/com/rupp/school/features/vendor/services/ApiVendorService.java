package com.rupp.school.features.vendor.services;

import com.rupp.school.core.models.ApiVendorResponseModel.ApiVendorNews;

public interface ApiVendorService {
    /** get news info */
    ApiVendorNews getNewsData();
}