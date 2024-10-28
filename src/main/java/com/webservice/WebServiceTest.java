package com.webservice;

import okhttp3.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class WebServiceTest {

    public static void main(String[] args) throws Exception {
        sendRequest(5);
    }

    private static final OkHttpClient client = new OkHttpClient();
    public static void sendRequest(int n) throws Exception {
        int i = 0;
        while(i < n){
            i++;
            String url = "https://www.baidu.com/";
            RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), "");
            Request request = new Request.Builder()
                    .url(url)
                    .post(body)
                    .build();

            try (Response response = client.newCall(request).execute()) {
                System.out.println("第 " + i + " 个请求结果为：" + response.body().string());
            }
        }
    }
}
