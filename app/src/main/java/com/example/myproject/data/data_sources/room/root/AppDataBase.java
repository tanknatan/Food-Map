package com.example.myproject.data.data_sources.room.root;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.myproject.data.data_sources.room.dao.PointDao;
import com.example.myproject.data.data_sources.room.dao.ProfileDao;
import com.example.myproject.data.data_sources.room.dao.UserDataDao;
import com.example.myproject.data.data_sources.room.entities.PointEntity;
import com.example.myproject.data.data_sources.room.entities.ProfileEntity;
import com.example.myproject.data.data_sources.room.entities.UserDataEntity;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {PointEntity.class, ProfileEntity.class, UserDataEntity.class}, version = 1)
public abstract class AppDataBase extends RoomDatabase {

    public abstract PointDao pointDao();

    public abstract ProfileDao profileDao();

    public abstract UserDataDao userDataDao();

    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    private static volatile AppDataBase INSTANCE;

    public static AppDataBase getDataBase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDataBase.class) {
                if (INSTANCE == null) {

                    INSTANCE = buildDatabase(context);

//                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
//                                    AppDataBase.class, "app_database")
//                            .build();
                }
            }
        }
        return INSTANCE;
    }


    public static AppDataBase buildDatabase(Context context) {
        return Room.databaseBuilder(context.getApplicationContext(), AppDataBase.class, "app_database")
                .fallbackToDestructiveMigration() // Очищает базу данных при изменении. Заменить на миграцию, если будет необходимость.
                .build();

    }
}

