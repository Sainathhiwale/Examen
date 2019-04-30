package com.sainath.examen.data.network;

import com.sainath.examen.data.model.user.AddUser;
import com.sainath.examen.data.model.user.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ExamenApiInterface {
    @Headers("Content-Type: application/json")
    @POST("api/user/save")
    Call<AddUser>RegisterUser(@Body AddUser addUser);
}
