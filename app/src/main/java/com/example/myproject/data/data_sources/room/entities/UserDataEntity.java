package com.example.myproject.data.data_sources.room.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class UserDataEntity {
    @PrimaryKey
    public long id;

    public String name;
    public String userId;
    public double latitude;
    public double longitude;


    public UserDataEntity(){}


}

