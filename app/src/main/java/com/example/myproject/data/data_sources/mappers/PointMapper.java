package com.example.myproject.data.data_sources.mappers;

import com.example.myproject.data.data_sources.room.entities.PointEntity;
import com.example.myproject.data.models.Point;

public class PointMapper {

    public static Point toDomainModel(PointEntity entity){
        if (entity != null)
            return new Point(entity.id, entity.name, entity.radius, entity.icon, entity.latitude, entity.longitude);
        return null;
    }

}
