package com.example.myproject.data.data_sources.room.root;

import android.content.Context;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import androidx.annotation.NonNull;
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

@Database(entities = {PointEntity.class, ProfileEntity.class, UserDataEntity.class}, version = 10)
public abstract class AppDataBase extends RoomDatabase {

    public abstract PointDao placeDao();

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

                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    AppDataBase.class, "app_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }


    public static AppDataBase buildDatabase(Context context) {
        return Room.databaseBuilder(context.getApplicationContext(), AppDataBase.class, "app_database")
                .addCallback(new RoomDatabase.Callback() {
                    @Override
                    public void onOpen(@NonNull SupportSQLiteDatabase db) {
                        super.onCreate(db);
                        databaseWriteExecutor.execute(() -> {
                            synchronized (AppDataBase.class) {
                                for (int i = 0; i < 10; i++) {
                                    getDataBase(context).profileDao().addProfile(new ProfileEntity("profile_" + String.valueOf(i), 1, i));
                                    getDataBase(context).placeDao().addPoint(new PointEntity("default_" + String.valueOf(i), 1, i));
                                }
                            }
                        });
                    }
                })
                .fallbackToDestructiveMigration() // Очищает базу данных при изменении. Заменить на миграцию, если будет необходимость.
                .build();

    }
}

//    private static RoomDatabase.Callback creatingFillDataBase = new RoomDatabase.Callback() {
//        @Override
//        public void onCreate(@NonNull SupportSQLiteDatabase db) {
//            super.onCreate(db);
//            for (int i = 1; i < 15; i++){
//                synchronized (AppDataBase.class) {
//                    if (INSTANCE == null) {
//                        INSTANCE.profileDao().addProfile(new ProfileEntity("profile_" + String.valueOf(i), 1, i));
//                        INSTANCE.placeDao().addPlace(new PlaceEntity("default_" + String.valueOf(i), 1, i));
//                    }
//                }
//
//            }
//        }
//    };
//
//            Room.databaseBuilder(context.applicationContext,
//    DataDatabase::class.java, "Sample.db")
//            // prepopulate the database after onCreate was called
//            .addCallback(object : Callback() {
//        override fun onCreate(db: SupportSQLiteDatabase) {
//            super.onCreate(db)
//            // insert the data on the IO Thread
//            ioThread {
//                getInstance(context).dataDao().insertData(PREPOPULATE_DATA)
//            }
//        }
//    })
//            .build()
//
//    private void RoomDatabase.Callback builDataBase = Room.databaseBuilder();