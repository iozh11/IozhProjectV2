package com.example.iozhproject.server.data.network;

import com.example.iozhproject.server.data.source.CredentialsDataSource;
import com.example.iozhproject.server.data.source.UserApi;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitFactory {
    private static RetrofitFactory INSTANCE;

    private RetrofitFactory() {
    }

    public static synchronized RetrofitFactory getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new RetrofitFactory();
        }
        return INSTANCE;
    }

    private final OkHttpClient.Builder client = new OkHttpClient.Builder()
            .addInterceptor(chain -> {
                        String authData = CredentialsDataSource.getInstance().getAuthData();
                        if (authData == null) {
                            return chain.proceed(chain.request());
                        } else {
                            Request request = chain.request()
                                    .newBuilder()
                                    .addHeader("Authorization", authData)
                                    .build();
                            return chain.proceed(request);
                        }

                    }
            );

    private final Retrofit retrofit = new Retrofit.Builder()
//            .baseUrl("http://192.168.1.12:8080/")
            .baseUrl("http://192.168.245.114:8080/")
            .client(client.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public UserApi getUserApi() {
        return retrofit.create(UserApi.class);
    }
}
