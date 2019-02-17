package com.lacy.example.keywork.data.api;

import com.lacy.example.keywork.common.AppPreferences;
import com.lacy.example.keywork.common.GlobalInfo;
import com.lacy.example.keywork.common.exception.ErrorConstants;
import com.lacy.example.keywork.common.exception.ErrorException;

import java.io.IOException;
import java.util.Set;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
/**
 * @author Created by tannv@imt-soft.com on 10/23/18.
 */
public class RequestInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        if (GlobalInfo.getInstance().isNetworkConnected()) {
            throw new ErrorException(ErrorConstants.NETWORK_NOT_AVAILABLE, "Network is not available");
        }

        Request original = chain.request();
        Request.Builder builder = original.newBuilder();
        builder.addHeader("Content-Type", "application/json;charset=UTF-8");
//        OAuthResponse oauthToken = AppPreferences.getInstance().getOAuth2();
        // bearer 165fc393-e258-484d-b7c1-49458bafb51e
//        if (oauthToken != null) {
//            builder.addHeader("Authorization", oauthToken.getTokenType()
//                    + Constants.STR_SPACE + oauthToken.getAccessToken());
//        }
//
        Set<String> cookies = AppPreferences.getInstance().getCookie();
        for (String cookie : cookies) {
            builder.addHeader("Cookie", cookie);
        }

        return chain.proceed(builder.build());
    }
}
