package com.sonderben.kagom.retrofitRepository

import com.sonderben.kagom.data.DistributionCenter
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

interface DistributionRetrofitRepository {

    @Headers("Content-Type: application/json;charset=UTF-8")
    @GET("distributions")
    public fun distributions(): Call<List<DistributionCenter>?>?


}