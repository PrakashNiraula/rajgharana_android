package com.devpj.rajgharana.retrofit;

import com.devpj.rajgharana.module.AddToBillResponse;
import com.devpj.rajgharana.module.Bill;
import com.devpj.rajgharana.module.Products;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APIInterface {

    @GET("/bill")
    Call<List<Bill>> getallongoingbills();

    @GET("/myproducts")
    Call<List<Products>> getmyproducts();

    @POST("/bill/addtobill")
    Call<AddToBillResponse> addtobill(@Body JsonObject obj);
}
