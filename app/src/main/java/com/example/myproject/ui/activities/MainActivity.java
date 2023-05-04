package com.example.myproject.ui.activities;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.splashscreen.SplashScreen;
import androidx.fragment.app.FragmentContainer;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.NavGraph;

import com.example.myproject.R;
import com.yandex.mapkit.MapKit;
import com.yandex.mapkit.MapKitFactory;
import com.yandex.mapkit.geometry.Point;
import com.yandex.mapkit.layers.ObjectEvent;
import com.yandex.mapkit.map.CameraPosition;
import com.yandex.mapkit.map.CompositeIcon;
import com.yandex.mapkit.map.IconStyle;
import com.yandex.mapkit.map.MapWindow;
import com.yandex.mapkit.map.RotationType;
import com.yandex.mapkit.mapview.MapView;
import com.yandex.mapkit.user_location.UserLocationLayer;
import com.yandex.mapkit.user_location.UserLocationObjectListener;
import com.yandex.mapkit.user_location.UserLocationView;
import com.yandex.runtime.image.ImageProvider;

import java.util.Arrays;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private NavController navController;
    private long back_pressed;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SplashScreen.installSplashScreen(this);
        setContentView(R.layout.activity_main);
    }

    private boolean isStartDestnation(NavDestination destination){
        if (destination == null) return false;
        NavGraph graph = destination.getParent();
        if (graph == null) return false;
        List<Integer> startDestinations = Arrays.asList(R.id.enterFragment, R.id.mapFragment);
        return startDestinations.contains(destination.getId());
    }
//    @Override
//    public void onBackPressed() {
//        if (isStartDestnation(navController.getCurrentDestination()) || navController.getBackQueue().isEmpty()){ //Если не создали navController или пользователь не залогинен
//            if (back_pressed + 2500 > System.currentTimeMillis()) {
//                super.onBackPressed();
//            }
//            else {
//                Toast.makeText(getBaseContext(), "Нажмите ещё раз, чтобы выйти", Toast.LENGTH_SHORT).show();
//            }
//            back_pressed = System.currentTimeMillis();
//        }
//        else {
//
//            navController.popBackStack();
//            Log.d("TakeAndGoDev_onBackPressed_pop", navController.getBackQueue().toString());
//
//        }
//    }
}