package com.samansepahvand.paginationlibrarysimple.retrofitSample.api;

import static com.samansepahvand.paginationlibrarysimple.retrofitSample.api.ApiInterface.BASE_URL;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static ApiClient instance = null;
    private ApiInterface myApi;

    private ApiClient() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        myApi = retrofit.create(ApiInterface.class);
    }

    public static synchronized ApiClient getInstance() {
        if (instance == null) {
            instance = new ApiClient();
        }
        return instance;
    }

    public ApiInterface getMyApi() {
        return myApi;
    }


}
