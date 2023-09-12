package com.sonderben.kagom.retrofitRepository;

import com.sonderben.kagom.data.Customer;
import com.sonderben.kagom.data.Login;
import com.sonderben.kagom.data.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface CustomerRepository {

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST("customers/login")
    Call<LoginResponse> login(@Body Login login);
    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST("customers/signup")
    Call<Customer> signUp(@Body Customer user);


    @Headers({ "Content-Type: application/json; charset=UTF-8" })
    @PATCH("customers/{id}")
    Call<Customer> update(@Path( "id" ) long id, @Body Customer user,@Header("Authorization") String auth);
}


















