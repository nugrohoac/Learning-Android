package com.example.nac017.crudretrofit.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by nac017 on 30/11/17.
 */

public class RetroServer {
    //private static final String base_url = "http://192.168.56.1/crud_android/";
    private static final String base_url = "https://ph.yippytech.com:5000/operasiPasar/";

    private static Retrofit retrofit;

    public static Retrofit getClient(){
        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(base_url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;
    }
}
