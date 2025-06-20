package com.acleda.student.services;

import com.acleda.student.models.ApiVendorResponseModel.ApiVendorNews;

public interface ApiVendorService {

    /** get news info */
    ApiVendorNews getNewsData();

}
