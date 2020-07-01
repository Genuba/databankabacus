package com.example.databankabacus.interfaces;

import com.example.databankabacus.data.model.AutenticarUsuario;
import com.example.databankabacus.data.model.Login;
import com.example.databankabacus.data.model.Token;
import com.example.databankabacus.data.model.RespuestaAutenticarUsuario;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface JsonPlaceHolderApi {

    @POST("api/Cuentas/Login")
    Call<Token> postLogin(@Body Login post);

    @POST("api/Inventarios/AutenticarUsuario")
    Call<RespuestaAutenticarUsuario> postAutenticarUsuario(@Body AutenticarUsuario post);
}
