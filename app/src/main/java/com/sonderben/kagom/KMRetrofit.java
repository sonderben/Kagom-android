package com.sonderben.kagom;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
/*android:networkSecurityConfig="@xml/network_security_config"*/
public class KMRetrofit {
    private static Retrofit retrofit = null;
    //public final static String ip="10.0.0.189";
    public final static String ip="10.0.2.2";

    public final static String baseurl="http://"+ip+":8080/api/v1/";

    public static Retrofit getInstanceRetrofit(){
        if(retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseurl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

}
