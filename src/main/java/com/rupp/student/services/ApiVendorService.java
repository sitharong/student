package com.rupp.student.services;

import com.rupp.student.models.ApiVendorResponseModel.ApiVendorNews;

public interface ApiVendorService {

    /** get news info */
    ApiVendorNews getNewsData();

}
