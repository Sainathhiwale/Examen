package com.sainath.examen.data.network;

import com.sainath.examen.data.model.user.SignInInfo;
import com.sainath.examen.data.model.user.SignUpDto;
import com.sainath.examen.data.model.user.SignUpInfo;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ExamenApiInterface {
    @Headers("Content-Type: application/json")
    @POST("api/user/save")
    Call<SignUpInfo>RegisterUser(@Body SignUpInfo signUpInfo);

    @POST("api/user/login")
    Call<SignInInfo>loginUser(@Body SignInInfo signInInfo);
}
