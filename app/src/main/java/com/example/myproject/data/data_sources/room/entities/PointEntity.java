package com.example.myproject.data.data_sources.room.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;



@Entity
public class PointEntity {

    @PrimaryKey(autoGenerate = true)
    public long id;

    @ColumnInfo(name = "icon_pass")
    public String icon;

    public String name;
    public double latitude;
    public double longitude;
    public String description;

    public PointEntity(){}

    public PointEntity(String name, double latitude, double longitude){
        this.icon = "default_question_mark";
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.description = "";
    }

    public PointEntity(String name, String icon, double latitude, double longitude){
        this(name, latitude, longitude);
        this.icon = icon;
    }

    public PointEntity(String name, String icon, double latitude, double longitude, String description) {
        this(name, icon, latitude, longitude);
        this.description = description;
    }

//    public Place toDomainModel(){
//        return new Place(name, icon, latitude, longitude);
//    }

}
