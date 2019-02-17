package com.lacy.example.keywork.data.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by vantan - nguyenvantan061195@gmail.com
 * HCMC, Vietnam.
 *
 * @version 1.0
 * @since 27, January, 2019 8:28 AM
 */
public class BaseResponse<T> {
    @SerializedName("errorCode")
    @Expose
    private int errorCode;
    @SerializedName("errorMessage")
    @Expose
    private String errorMessage;
    @SerializedName("response")
    @Expose
    private T response;

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public T getResponse() {
        return response;
    }

    public void setResponse(T response) {
        this.response = response;
    }
}

