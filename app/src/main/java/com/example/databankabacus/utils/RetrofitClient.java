package com.example.databankabacus.utils;

import android.content.Context;
import android.database.Cursor;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static Retrofit retrofit = null;
    private static SqliteUsuario sqliteUsuario;

    public static Retrofit getClient(Context context, String baseUrl) {
        sqliteUsuario = new SqliteUsuario(context);
        Cursor cursor = sqliteUsuario.getUsuario();

        if (cursor.moveToFirst()) {
            OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request newRequest  = chain.request().newBuilder()
                            .addHeader("Authorization", "Bearer " + cursor.getString(3))
                            .build();
                    return chain.proceed(newRequest);
                }
            }).build();

            if (retrofit==null) {
                retrofit = new Retrofit.Builder()
                        .client(client)
                        .baseUrl(baseUrl)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
            }
        }

        return retrofit;
    }
}