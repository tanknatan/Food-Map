package com.example.myproject.data.data_sources.room.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.myproject.data.data_sources.room.entities.PointEntity;
import com.example.myproject.data.data_sources.room.entities.UserDataEntity;
@Dao
public interface UserDataDao {
    @Query("SELECT * FROM UserDataEntity")
    LiveData<UserDataEntity> getData();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addProfile(UserDataEntity profile);

    @Delete
    void deleteUser(UserDataEntity user);

    @Update
    void updateUser(UserDataEntity user);
    @Update
    void updateData(UserDataEntity userData);




    @Query("SELECT * FROM PointEntity WHERE id = :id")
    LiveData<PointEntity> getById(long id);
}
