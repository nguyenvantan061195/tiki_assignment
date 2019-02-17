package com.lacy.example.keywork.common;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.multidex.MultiDexApplication;

import com.lacy.example.keywork.BuildConfig;

import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by vantan - nguyenvantan061195@gmail.com
 * HCMC, Vietnam.
 *
 * @version 1.0
 * @since 13, January, 2019 11:55 AM
 */
public class GlobalInfo extends MultiDexApplication {
    private static volatile GlobalInfo mInstance;
    private static Context context;
    private Scheduler mScheduler;
    public static boolean isDebugMode;

    public static GlobalInfo getInstance() {
        return mInstance;
    }

    public static void setInstance(GlobalInfo mInstance) {
        GlobalInfo.mInstance = mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        GlobalInfo.context = getApplicationContext();
        setInstance(this);
        if (BuildConfig.DEBUG) {
            isDebugMode = true;
        }
    }

    public static Context getAppContext() {
        return GlobalInfo.context;
    }

    public Scheduler subscribeScheduler() {
        if (mScheduler == null) {
            mScheduler = Schedulers.io();
        }

        return mScheduler;
    }

    public boolean isNetworkConnected() {
        ConnectivityManager connectivityManager =
                (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = null;
        if (connectivityManager != null) {
            networkInfo = connectivityManager.getActiveNetworkInfo();
        }

        return (networkInfo == null || !networkInfo.isConnected());
    }
}
