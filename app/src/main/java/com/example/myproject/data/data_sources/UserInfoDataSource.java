package com.example.myproject.data.data_sources;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.myproject.data.models.UserData;

public class UserInfoDataSource {
    public LiveData<UserData> createUserInfo(){
        MutableLiveData<UserData> result = new MutableLiveData<>();
        result.postValue(new UserData("","0"));
        return result;
    }
}
