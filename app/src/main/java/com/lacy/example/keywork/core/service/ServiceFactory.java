package com.lacy.example.keywork.core.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lacy.example.keywork.BuildConfig;
import com.lacy.example.keywork.common.Constants;
import com.lacy.example.keywork.data.api.RequestInterceptor;
import com.lacy.example.keywork.data.api.ResponseInterceptor;

import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by vantan - nguyenvantan061195@gmail.com
 * HCMC, Vietnam.
 *
 * @version 1.0
 * @since 13, January, 2019 11:57 AM
 */
public class ServiceFactory {

    ServiceFactory() {
        // Do nothing
    }

    private static volatile OkHttpClient sOkHttpClient = null;
    private static volatile ServiceApi mServiceApi = null;

    public static ServiceApi getServiceInstance() {
        if (mServiceApi == null) {
            mServiceApi = ServiceFactory.create();
        }

        return mServiceApi;
    }

    private static ServiceApi create() {

        Gson gson = new GsonBuilder().serializeNulls().create();
        RxJava2CallAdapterFactory callAdapter = RxJava2CallAdapterFactory.create();

        OkHttpClient client = createHttpClient();

        Retrofit.Builder retrofitBuilder = new Retrofit.Builder();
        retrofitBuilder.baseUrl(ServiceApi.SERVER_PATH);
        retrofitBuilder.client(client);

        retrofitBuilder.addConverterFactory(GsonConverterFactory.create(gson));
        retrofitBuilder.addCallAdapterFactory(callAdapter);

        Retrofit retrofit = retrofitBuilder.build();

        return retrofit.create(ServiceApi.class);
    }

    private static OkHttpClient createHttpClient() {
        if (sOkHttpClient != null) return sOkHttpClient;
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        // OAuth2 Refresh Token
//        TokenAuthenticator authenticator = new TokenAuthenticator();
//        httpClient.authenticator(authenticator);

        httpClient.addInterceptor(new RequestInterceptor());
        httpClient.addInterceptor(new ResponseInterceptor());

        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor interceptorBody = new HttpLoggingInterceptor();
            interceptorBody.setLevel(HttpLoggingInterceptor.Level.BODY);
            httpClient.addInterceptor(interceptorBody);
        }

        httpClient.connectTimeout(Constants.TIME_OUT, TimeUnit.SECONDS);
        httpClient.readTimeout(Constants.TIME_OUT, TimeUnit.SECONDS);
        httpClient.writeTimeout(Constants.TIME_OUT, TimeUnit.SECONDS);

        httpClient.hostnameVerifier((hostname, session) -> true);
        try {
            // Create a trust manager that does not validate certificate chains
            final TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
                @Override
                public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) {

                }

                @Override
                public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) {

                }

                @Override
                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return new java.security.cert.X509Certificate[0];
                }
            }};

            // Install the all-trusting trust manager
            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts,
                    new java.security.SecureRandom());
            // Create an ssl socket factory with our all-trusting manager
            final SSLSocketFactory sslSocketFactory = sslContext
                    .getSocketFactory();
            httpClient = httpClient.sslSocketFactory(sslSocketFactory);
            httpClient = httpClient.hostnameVerifier((hostname, session) -> true);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        sOkHttpClient = httpClient.build();
        return sOkHttpClient;
    }
}

