package com.example.myproject.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.myproject.data.models.Place;
import com.example.myproject.data.repositories.PointListRepository;

import java.util.List;

public class PointViewModel extends AndroidViewModel {
    private PointListRepository repo;
    private LiveData<List<Place>> mPoint;

    public PointViewModel(@NonNull Application application) {
        super(application);
        this.repo = new PointListRepository(application);
//        mPlaces = repo.getTestData();
        mPoint = repo.getDataBaseData();
    }

    public LiveData<List<Place>> getPlaces() {return mPoint;}

    public void pushDataAll(List<Place> places){
        for (int i = 0; i < places.size(); i++){repo.updateData(places.get(i));}
    }

    public void addPlace(Place point){
        repo.updateData(point);
    }

    public void generic(){
        repo.generic();
    }

    public Place findByCoordinates(double latitude, double longitude){
        return repo.findByCoordinates(latitude, longitude);
    }

    public LiveData<Integer> getLastId(){
        return repo.size();
    }

}