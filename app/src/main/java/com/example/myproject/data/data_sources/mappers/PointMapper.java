package com.example.myproject.data.data_sources.mappers;

import com.example.myproject.data.data_sources.room.entities.PointEntity;
import com.example.myproject.data.models.Place;


public class PointMapper {

    public static Place toDomainModel(PointEntity entity){
        if (entity != null)
            return new Place(entity.id, entity.name, entity.radius, entity.icon, entity.latitude, entity.longitude);
        return null;
    }

}
