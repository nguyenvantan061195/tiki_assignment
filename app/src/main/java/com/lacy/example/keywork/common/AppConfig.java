package com.lacy.example.keywork.common;

/**
 * Created by vantan - nguyenvantan061195@gmail.com
 * HCMC, Vietnam.
 *
 * @version 1.0
 * @since 10, December, 2018 3:50 PM
 */
public class AppConfig {

    private static AppConfig instance;

    public static AppConfig getInstance() {
        if (instance == null) {
            instance = new AppConfig();
        }
        return instance;
    }

    public static final String APP_COOKIE = "APP_COOKIE";
}
