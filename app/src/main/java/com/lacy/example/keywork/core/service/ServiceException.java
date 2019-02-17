package com.lacy.example.keywork.core.service;


/**
 * Created by vantan - nguyenvantan061195@gmail.com
 * HCMC, Vietnam.
 *
 * @version 1.0
 * @since 13, January, 2019 11:56 AM
 */
public class ServiceException extends Exception {
    private int mCode;

    public ServiceException(int code, String message){
        super(message);
        this.mCode = code;
    }

    public int getCode() {
        return mCode;
    }
}
