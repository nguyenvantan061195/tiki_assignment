package com.lacy.example.keywork.common;

import android.content.SharedPreferences;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by vantan - nguyenvantan061195@gmail.com
 * HCMC, Vietnam.
 *
 * @version 1.0
 * @since 13, January, 2019 12:16 PM
 */
public class AppPreferences {
    private static final String CHAR_SET = "UTF-8";
    private static AppPreferences sInstance;
    private SharedPreferences mSecurePreferences;

    public static AppPreferences getInstance() {
        if (sInstance == null) {
            sInstance = new AppPreferences();
        }

        return sInstance;
    }

    private AppPreferences() {
        mSecurePreferences = GlobalInfo.getInstance().getSharedPreferences(
                Constants.SHARED_PREFERENCES_FILE_NAME, GlobalInfo.MODE_PRIVATE);
    }

    public void clear() {
        mSecurePreferences.edit().clear().apply();
    }

    public void setCookie(List<String> cookies) {
        if (!cookies.isEmpty()) {
            Set<String> cookieSet = new HashSet<>(cookies);
            mSecurePreferences.edit().putStringSet(AppConfig.APP_COOKIE, cookieSet).apply();
        }
    }

    public Set<String> getCookie() {
        return mSecurePreferences.getStringSet(AppConfig.APP_COOKIE, new HashSet<>());
    }

    public void clearCookie() {
        mSecurePreferences.edit().remove(AppConfig.APP_COOKIE).apply();
    }

}
