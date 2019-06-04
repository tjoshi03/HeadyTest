package com.android.headyecommerce.util;

import android.arch.lifecycle.MutableLiveData;

import com.android.headyecommerce.Api.ApiCall;
import com.android.headyecommerce.Model.Data;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataRepository {

    private static DataRepository newsRepository;

    public static DataRepository getInstance(){
        if (newsRepository == null){
            newsRepository = new DataRepository();
        }
        return newsRepository;
    }

    private ApiCall newsApi;

    public DataRepository(){
        newsApi = RetrofitService.cteateService(ApiCall.class);
    }

    public MutableLiveData<Data> getNews(){
        final MutableLiveData<Data> newsData = new MutableLiveData<>();
        newsApi.getdata().enqueue(new Callback<Data>() {
            @Override
            public void onResponse(Call<Data> call,
                                   Response<Data> response) {
                if (response.isSuccessful()){
                    newsData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<Data> call, Throwable t) {
                newsData.setValue(null);
            }
        });
        return newsData;
    }
}
