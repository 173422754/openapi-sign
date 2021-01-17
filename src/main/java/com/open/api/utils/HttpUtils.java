package com.open.api.utils;

import okhttp3.*;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class HttpUtils {
    private HttpUtils() {
    }
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    private static OkHttpClient httpClient = new OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .hostnameVerifier((s, sslSession) -> true)
            .build();

    public static String postWithHead(String url, Map<String, String> headerMap, String payload) {
        RequestBody body = RequestBody.create(JSON, payload == null ? "" : payload);
        return post(url, headerMap, body);
    }

    public static String post(String url, Map<String, String> headerMap, RequestBody formBody) {

        Request.Builder builder = new Request.Builder()
                .url(url)
                .post(formBody);
        if (headerMap != null) {
            headerMap.forEach(builder::header);
        }

        Request request = builder.build();
        try (Response response = httpClient.newCall(request).execute()) {
            if (response.isSuccessful()) {
                ResponseBody body = response.body();
                return body == null ? "" : body.string();
            } else {
                ResponseBody body = response.body();
                return body == null ? response.message() : body.string();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String get(String url) {
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();
        try {
            Response response = httpClient.newCall(request).execute();
            if (response.isSuccessful()) {
                return response.body().string();
            } else {
                ResponseBody body = response.body();
                return body == null ? response.message() : body.string();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
