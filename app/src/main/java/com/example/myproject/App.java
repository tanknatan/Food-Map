package com.example.myproject;

import android.app.Application;

import com.yandex.mapkit.MapKitFactory;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        MapKitFactory.setApiKey("c05592b5-812d-43c0-be28-bb57c88aa534");
    }
}
