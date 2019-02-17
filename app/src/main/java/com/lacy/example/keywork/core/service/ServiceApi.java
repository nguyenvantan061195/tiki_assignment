package com.lacy.example.keywork.core.service;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;

/**
 * Created by vantan - nguyenvantan061195@gmail.com
 * HCMC, Vietnam.
 *
 * @version 1.0
 * @since 13, January, 2019 11:55 AM
 */

public interface ServiceApi {
    String SERVER_PATH = "https://raw.githubusercontent.com/";

    @GET("tikivn/android-home-test/v2/keywords.json")
    Observable<ResponseBody> getAllKeyword();
}
