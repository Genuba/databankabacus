package com.example.databankabacus.utils;

import android.content.Context;

import com.example.databankabacus.interfaces.JsonPlaceHolderApi;
import com.example.databankabacus.ui.login.LoginActivity;

import org.jetbrains.annotations.NotNull;

public class ApiUtils {

    private ApiUtils() {}

    public static final String BASE_URL = "https://servicioapp.azurewebsites.net/";

    public static JsonPlaceHolderApi getAPIService(Context context) {
        return RetrofitClient.getClient(context,BASE_URL).create(JsonPlaceHolderApi.class);
    }
}
