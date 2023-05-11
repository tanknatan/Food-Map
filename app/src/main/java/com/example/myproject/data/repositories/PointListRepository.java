package com.example.myproject.data.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;

import com.example.myproject.data.data_sources.PointListDataSource;
import com.example.myproject.data.data_sources.mappers.PointMapper;
import com.example.myproject.data.data_sources.room.entities.PointEntity;
import com.example.myproject.data.data_sources.room.root.AppDataBase;
import com.example.myproject.data.models.Point;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PointListRepository {
    private PointListDataSource mDataSource;
    private AppDataBase dataBaseSource;

    public PointListRepository(Application application){
        this.mDataSource = new PointListDataSource();
        this.dataBaseSource = AppDataBase.getDataBase(application);
    }

    public LiveData<List<Point>> getTestData(){
        return mDataSource.points();
    }

    public LiveData<List<Point>> getDataBaseData(){
        return Transformations.map(
                dataBaseSource.pointDao().getAllPlaces(),
                (value) -> value.stream().map(PointMapper::toDomainModel).collect(Collectors.toList()));
    }

    public void updateData(Point data){
        AppDataBase.databaseWriteExecutor.execute(()->{
            dataBaseSource.pointDao().addPlace(new PointEntity(data.getId(), data.getName(), data.getRadius(), data.getIcon(), data.getLatitude(), data.getLongitude(), data.getDescription()));
        });
    }

    public void generic(){
        AppDataBase.databaseWriteExecutor.execute(()->{
            List<Point> placeList = new ArrayList<>();

            placeList.add(new Point(1, "Вкус востока", 50000000,""  ,55.71, 37.545));
            for (Point point: placeList){
                dataBaseSource.pointDao().addPlace(new PointEntity(point.getId(), point.getName(),point.getRadius(),point.getIcon(),point.getLatitude(),point.getLongitude()));
            }

        });
    }

    public LiveData<Integer> size(){
        return dataBaseSource.pointDao().count();
    }

    public Point findByCoordinates(double latitude, double longitude){
        return PointMapper.toDomainModel(dataBaseSource.pointDao().findNameByLatitude(latitude, longitude));
    }

}
