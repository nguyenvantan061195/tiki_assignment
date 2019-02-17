package com.lacy.example.keywork.data.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by vantan - nguyenvantan061195@gmail.com
 * HCMC, Vietnam.
 *
 * @version 1.0
 * @since 13, January, 2019 12:22 PM
 */
public class BaseEntity implements Parcelable {
    @SerializedName("id")
    private long id;

    public BaseEntity() {
    }

    public BaseEntity(Parcel in) {
        id = in.readLong();
    }

    public static final Creator<BaseEntity> CREATOR = new Creator<BaseEntity>() {
        @Override
        public BaseEntity createFromParcel(Parcel in) {
            return new BaseEntity(in);
        }

        @Override
        public BaseEntity[] newArray(int size) {
            return new BaseEntity[size];
        }
    };

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
    }
}
