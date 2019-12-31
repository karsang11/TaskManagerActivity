package com.karsang.taskmanageractivity.API;

import com.karsang.taskmanageractivity.Model.User;
import com.karsang.taskmanageractivity.ServerResponse.ImageResponse;
import com.karsang.taskmanageractivity.ServerResponse.SignUpResponse;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface UsersAPI {

    @POST("users/signup")
    Call<SignUpResponse> register(@Body User user);

    @Multipart
    @POST("upload")
    Call<ImageResponse> uploadImage(@Part MultipartBody.Part img);

}
