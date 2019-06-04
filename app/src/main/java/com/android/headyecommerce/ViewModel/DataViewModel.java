package com.android.headyecommerce.ViewModel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.android.headyecommerce.Model.Data;
import com.android.headyecommerce.util.DataRepository;

public class DataViewModel extends ViewModel {

    private MutableLiveData<Data> mutableLiveData;
    private DataRepository newsRepository;

    public void init(){
        if (mutableLiveData != null){
            return;
        }
        newsRepository = DataRepository.getInstance();
        mutableLiveData = newsRepository.getNews();

    }

    public LiveData<Data> getNewsRepository() {
        return mutableLiveData;
    }
}
