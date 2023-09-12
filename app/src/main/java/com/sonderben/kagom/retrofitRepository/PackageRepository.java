package com.sonderben.kagom.retrofitRepository;

import com.sonderben.kagom.data.Package;
import com.sonderben.kagom.data.Shipment;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface PackageRepository {
//GET http://localhost:8080/api/v1/packages/search?shipment_id=1
    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @GET("packages/search")
    Call<List<Package>> findPackageByShipmentId(@Query("shipment_id") Long shipmentId,
                                                @Header("Authorization") String auth );
}
