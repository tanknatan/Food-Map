package com.example.myproject.data.data_sources.room.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.myproject.data.data_sources.room.entities.PointEntity;
import com.example.myproject.data.data_sources.room.entities.ProfileEntity;
import java.util.List;
@Dao
public interface ProfileDao {
    @Query("SELECT * FROM ProfileEntity")
    LiveData<List<ProfileEntity>> getAllProfiles();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addProfile(ProfileEntity profile);

    @Delete
    void deleteProfile(ProfileEntity profile);

//    @Query("SELECT * FROM ProfileEntity WHERE id = :id")
//    LiveData<PointEntity> getById(long id);
}
