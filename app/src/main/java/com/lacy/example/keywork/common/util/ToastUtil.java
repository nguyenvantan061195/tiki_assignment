package com.lacy.example.keywork.common.util;

import android.content.Context;
import android.os.CountDownTimer;
import android.widget.Toast;

/**
 * Created by vantan - nguyenvantan061195@gmail.com
 * HCMC, Vietnam.
 *
 * @version 1.0
 * @since 10, December, 2018 4:04 PM
 */
public class ToastUtil {
    /**
     * show toast message
     *
     * @param message
     */
    public static void showToastMessage(Context activity, String message) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show();
    }

    /**
     * show toast message with duration
     *
     * @param activity
     * @param message
     * @param duration
     */
    public static void showToastMessage(Context activity, String message, int duration) {
        final Toast toast = Toast.makeText(activity, message, Toast.LENGTH_SHORT);
        toast.show();
        //Create the count down timer here
        new CountDownTimer(duration, 1000) {
            public void onTick(long millisUntilFinished) {
                toast.show();
            }

            public void onFinish() {
                toast.cancel();
            }
        }.start();
    }
}
