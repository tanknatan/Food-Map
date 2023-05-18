package com.example.myproject.data.data_sources;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;


import com.example.myproject.data.models.Place;
import com.example.myproject.data.models.Place;

import java.util.ArrayList;
import java.util.List;

public class PointListDataSource {

    public LiveData<List<Place>> points(){
        MutableLiveData<List<Place>> result = new MutableLiveData<>();

        new Thread(() -> {
            ArrayList<Place> resultArr = new ArrayList<>();

            for (int i = 0; i < 25; i++){
//                resultArr.add(new Point("Test #" + (i+1), "samurai_image" , i+10, i +10));
            }

            result.postValue(resultArr);
        }).start();

        return result;
    }

}