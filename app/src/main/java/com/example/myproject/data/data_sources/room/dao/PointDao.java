package com.example.myproject.data.data_sources.room.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.myproject.data.data_sources.room.entities.PointEntity;
import java.util.List;
@Dao

public interface PointDao {

    @Query("SELECT * FROM PointEntity")
    LiveData<List<PointEntity>> getAllPlaces();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addPlace(PointEntity point);

    @Delete
    void deletePlace(PointEntity point);

    @Query("SELECT * FROM PointEntity WHERE latitude = :latitude AND longitude = :longitude")
    PointEntity findNameByLatitude(double latitude, double longitude);

    @Query("SELECT COUNT(*) FROM PointEntity")
    LiveData<Integer> count();

//    @Query("SELECT * FROM PointEntity WHERE id = :id")
//    LiveData<PointEntity> getById(long id);

}
