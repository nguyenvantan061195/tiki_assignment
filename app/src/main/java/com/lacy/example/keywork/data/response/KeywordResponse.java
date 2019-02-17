package com.lacy.example.keywork.data.response;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by vantan - nguyenvantan061195@gmail.com
 * HCMC, Vietnam.
 *
 * @version 1.0
 * @since 16, February, 2019 10:13 PM
 */
public class KeywordResponse implements Parcelable {

    private List<String> keywords;

    protected KeywordResponse(Parcel in) {
        keywords = in.createStringArrayList();
    }

    public KeywordResponse() {
    }

    public static final Creator<KeywordResponse> CREATOR = new Creator<KeywordResponse>() {
        @Override
        public KeywordResponse createFromParcel(Parcel in) {
            return new KeywordResponse(in);
        }

        @Override
        public KeywordResponse[] newArray(int size) {
            return new KeywordResponse[size];
        }
    };

    public List<String> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringList(keywords);
    }
}
