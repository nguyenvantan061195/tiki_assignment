package com.lacy.example.keywork.data.api;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.lacy.example.keywork.common.exception.ErrorException;
import com.lacy.example.keywork.data.response.BaseResponse;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * @author Created by tannv@imt-soft.com on 10/23/18.
 */
public class ApiResponseBodyConverter<T> implements Converter<ResponseBody, T> {

    private final Gson gson;
    private final TypeAdapter<T> adapter;

    ApiResponseBodyConverter(Gson gson, TypeAdapter<T> adapter) {
        this.gson = gson;
        this.adapter = adapter;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        JsonReader jsonReader = gson.newJsonReader(value.charStream());
        try {
            T result = adapter.read(jsonReader);

            if (jsonReader.peek() != JsonToken.END_DOCUMENT) {
                throw new JsonIOException("JSON document was not fully consumed.");
            }

            if (result instanceof BaseResponse) {
                BaseResponse item = (BaseResponse) result;
                int code = item.getErrorCode();
                if (code == 200) {
                    return result;
                }

                throw new ErrorException(code, item.getErrorMessage());
            } else {
                return result;
            }

        } finally {
            value.close();
        }
    }
}
