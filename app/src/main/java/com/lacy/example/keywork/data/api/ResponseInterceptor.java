package com.lacy.example.keywork.data.api;

import android.support.annotation.NonNull;

import com.lacy.example.keywork.common.AppPreferences;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;
/**
 * @author Created by tannv@imt-soft.com on 10/23/18.
 */
public class ResponseInterceptor implements Interceptor {
    @NonNull
    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Response originalResponse = chain.proceed(chain.request());
        AppPreferences.getInstance().setCookie(originalResponse.headers("Set-Cookie"));
        return originalResponse;
    }
}
