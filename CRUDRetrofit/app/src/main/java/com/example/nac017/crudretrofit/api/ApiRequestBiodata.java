package com.example.nac017.crudretrofit.api;

import com.example.nac017.crudretrofit.model.DataModel;
import com.example.nac017.crudretrofit.model.ResponsModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by nac017 on 30/11/17.
 */

public interface ApiRequestBiodata {
    @FormUrlEncoded
    @POST("create_data.php")
    Call<ResponsModel> sendData(@Field("npm") String npm,
                                @Field("nama") String nama,
                                @Field("prodi") String prodi,
                                @Field("fakultas") String fakultas);

    @GET("view_data.php")
    Call<ResponsModel> getData();

    @FormUrlEncoded
    @POST("update_data.php")
    Call<ResponsModel> updating(@Field("npm") String npm,
                                @Field("nama") String nama,
                                @Field("prodi") String prodi,
                                @Field("fakultas") String fakultas);

    @FormUrlEncoded
    @POST("delete_data.php")
    Call<ResponsModel> deleteData(@Field("npm") String npm);

    @GET("get")
    Call<ResponsModel> getLaporan(@Header("Authorization") String authorization);
}
