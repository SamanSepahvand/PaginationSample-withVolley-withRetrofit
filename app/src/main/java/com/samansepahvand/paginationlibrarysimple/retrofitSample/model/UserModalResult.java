package com.samansepahvand.paginationlibrarysimple.retrofitSample.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class UserModalResult {

    @SerializedName("page")
    @Expose
    public int page;

    @SerializedName("per_page")
    @Expose
    public int per_page;

    @SerializedName("total")
    @Expose
    public int total;

    @SerializedName("total_pages")
    @Expose
    public int total_pages;

    @SerializedName("data")
    @Expose
    public List<UserModal> data;

    @SerializedName("support")
    @Expose
    public Support support;



    public class Support{
        public String url;
        public String text;
    }

}
