package com.android.headyecommerce.Api;

import com.android.headyecommerce.Model.Data;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiCall {

    @GET("json")
   Call<Data>getdata();
}
