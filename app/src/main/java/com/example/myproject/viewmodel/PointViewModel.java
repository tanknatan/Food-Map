package com.example.myproject.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.example.myproject.data.models.Point;
import com.example.myproject.data.repositories.PointListRepository;

import java.util.List;

public class PointViewModel {
    private PointListRepository repo;
    private LiveData<List<Point>> mPlaces;

    public PointViewModel(@NonNull Application application) {
        this.repo = new PointListRepository(application);
//        mPlaces = repo.getTestData();
        mPlaces = repo.getDataBaseData();
    }

    public LiveData<List<Point>> getPlaces() {return mPlaces;}

    public void pushDataAll(List<Point> places){
        for (int i = 0; i < places.size(); i++){repo.updateData(places.get(i));}
    }

    public void generic(){
        repo.generic();
    }

    public Point findByCoordinates(double latitude, double longitude){
        return repo.findByCoordinates(latitude, longitude);
    }
}
