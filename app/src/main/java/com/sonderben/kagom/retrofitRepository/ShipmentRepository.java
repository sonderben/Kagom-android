package com.sonderben.kagom.retrofitRepository;

import com.sonderben.kagom.data.Customer;
import com.sonderben.kagom.data.Login;
import com.sonderben.kagom.data.LoginResponse;
import com.sonderben.kagom.data.Shipment;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface ShipmentRepository {

    //GET http://localhost:8080/api/v1/shipments/search?paid=true&receiver=1



    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @GET("shipments/search")
    Call<List<Shipment>> queryShipments(@QueryMap Map<String, Object> options,
                              @Header("Authorization") String auth );
}
