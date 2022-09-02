package com.samansepahvand.paginationlibrarysimple.retrofitSample.api;

import com.samansepahvand.paginationlibrarysimple.retrofitSample.model.UserModalResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {


    String BASE_URL = "https://reqres.in/api/";


//    @GET("users?page=1")
//    Call<UserModalList> GetDataList();


    @GET("users")
    Call<UserModalResult> GetDataList(@Query("page") int page);


}


