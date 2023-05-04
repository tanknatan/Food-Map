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
    private PointListDataSource DataSource;
    private AppDataBase dataBaseSource;

    public PointListRepository(Application application) {
        this.DataSource = new PointListDataSource();
        this.dataBaseSource = AppDataBase.getDataBase(application);
    }


    public LiveData<List<Point>> getTestData(){
        return DataSource.places();
    }

    public LiveData<List<Point>> getDataBaseData(){
        return Transformations.map(
                dataBaseSource.placeDao().getAllPoints(),
                (value) -> value.stream().map(PointMapper::toDomainModel).collect(Collectors.toList()));
    }

    public void updateData(Point data){
        AppDataBase.databaseWriteExecutor.execute(()->{
            dataBaseSource.placeDao().addPoint(new PointEntity(data.getName(), data.getIcon(), data.getLatitude(), data.getLongitude(), data.getDescription()));
        });
    }

    public void generic(){
        AppDataBase.databaseWriteExecutor.execute(()->{
            List<Point> placeList = new ArrayList<>();

            placeList.add(new Point("Донер тайм", "//"  ,55.679860, 37.666440));

            for (Point place: placeList){
                dataBaseSource.placeDao().addPoint(new PointEntity(place.getName(),place.getIcon(),place.getLatitude(),place.getLongitude()));
            }

        });
    }

    public Point findByCoordinates(double latitude, double longitude){
        return PointMapper.toDomainModel(dataBaseSource.placeDao().findNameByLatitude(latitude, longitude));
    }

}
