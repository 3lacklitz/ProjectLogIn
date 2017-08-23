package com.example.administrator.projectlogin.service;

import com.example.administrator.projectlogin.model.HeroList;
import com.example.administrator.projectlogin.model.Login;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;


public interface LoginService {
    @FormUrlEncoded
    @POST("login_api.php")
    Call<Login> getLoginData(@Field("username") String usernameString,
                             @Field("password") String passwordString);

}
