package com.lacy.example.keywork.data.request;

import com.google.gson.annotations.SerializedName;

/**
 * Created by vantan - nguyenvantan061195@gmail.com
 * HCMC, Vietnam.
 *
 * @version 1.0
 * @since 27, January, 2019 9:23 AM
 */
public class BaseRequest {
    @SerializedName("firebase_id_token")
    private String firebase_id_token;

    public String getFirebase_id_token() {
        return firebase_id_token;
    }

    public void setFirebase_id_token(String firebase_id_token) {
        this.firebase_id_token = firebase_id_token;
    }
}
