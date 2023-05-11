package com.example.myproject.data.data_sources.mappers;

import com.example.myproject.data.data_sources.room.entities.UserDataEntity;
import com.example.myproject.data.models.UserData;

public class UserMapper {
    public static UserData toDomainModel(UserDataEntity entity){
        //  return new UserData(entity.name, entity.userId, entity.latitude, entity.longitude);
        return new UserData("name", "12", 12 , 12);
    }
}
