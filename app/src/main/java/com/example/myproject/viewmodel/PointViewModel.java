package com.example.myproject.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.myproject.data.models.Point;
import com.example.myproject.data.repositories.PointListRepository;



import java.util.List;

public class PointViewModel extends AndroidViewModel {
    private PointListRepository repo;
    private LiveData<List<Point>> mPlaces;

    public PointViewModel(@NonNull Application application) {
        super(application);
        this.repo = new PointListRepository(application);
//        mPlaces = repo.getTestData();
        mPlaces = repo.getDataBaseData();
    }

    public LiveData<List<Point>> getPoint() {return mPlaces;}

    public void pushDataAll(List<Point> points){
        for (int i = 0; i < points.size(); i++){repo.updateData(points.get(i));}
    }

    public void addPlace(Point point){
        repo.updateData(point);
    }

    public void generic(){
        repo.generic();
    }

    public Point findByCoordinates(double latitude, double longitude){
        return repo.findByCoordinates(latitude, longitude);
    }

    public LiveData<Integer> getLastId(){
        return repo.size();
    }

}
