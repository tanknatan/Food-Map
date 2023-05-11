package com.example.myproject.data.data_sources.room.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity
public class PointEntity {

    @PrimaryKey
    public long id;

    @ColumnInfo(name = "icon_pass")
    public String icon;

    public String name;
    public double latitude;
    public double longitude;
    public String description;
    public long radius;

    public PointEntity(){}

    public PointEntity(long id, String name, long radius, double latitude, double longitude){
        this.id = id;
        this.icon = "default_question_mark";
        this.name = name;
        this.radius = radius;
        this.latitude = latitude;
        this.longitude = longitude;
        this.description = "";
    }

    public PointEntity(long id, String name, long radius, String icon, double latitude, double longitude){
        this(id, name, radius,latitude, longitude);
        this.icon = icon;
    }

    public PointEntity(long id, String name, long radius, String icon, double latitude, double longitude, String description) {
        this(id, name, radius, icon, latitude, longitude);
        this.description = description;
    }

//    public Place toDomainModel(){
//        return new Place(name, icon, latitude, longitude);
//    }

}
