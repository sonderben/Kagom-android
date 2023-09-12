package com.sonderben.kagom.retrofitRepository

import com.sonderben.kagom.data.ShipmentTracking
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface ShipmentTrackingRepository {


    @Headers("Content-Type: application/json;charset=UTF-8")
    @GET("shipments/tracking/{id}")
    fun shipmentTracking(@Path("id") trackingId:String): Call<ShipmentTracking?>?
}