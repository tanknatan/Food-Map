package com.example.myproject;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import com.yandex.mapkit.Animation;
import com.yandex.mapkit.MapKitFactory;
import com.yandex.mapkit.geometry.Point;
import com.yandex.mapkit.map.CameraPosition;
import com.yandex.mapkit.mapview.MapView;

public class MapFragment extends Fragment {

    private MapView mapView;
    private AppCompatImageButton buttonUser;
    private AppCompatImageButton buttonReview;
    private AppCompatImageButton buttonSearch;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.map_r, container, false);
        MapKitFactory.initialize(requireContext());

        mapView = view.findViewById(R.id.mapview);
        buttonUser = view.findViewById(R.id.user);
        buttonReview = view.findViewById(R.id.review);
        buttonSearch = view.findViewById(R.id.search);
        mapView.getMap().move(
                new CameraPosition(new Point(55.751574, 37.573856), 11.0f, 0.0f, 0.0f),
                new Animation(Animation.Type.SMOOTH, 0),
                null);
        buttonUser.setOnClickListener(v->{
            Navigation.findNavController(view).navigate(R.id.action_mapFragment_to_accountFragment);
        });
        buttonReview.setOnClickListener(v->{
            Navigation.findNavController(view).navigate(R.id.action_mapFragment_to_reviewFragment);
        });
        buttonSearch.setOnClickListener(v->{
            Navigation.findNavController(view).navigate(R.id.action_mapFragment_to_searchFragment);
        });
        return view;
    }

    @Override
    public void onStop() {
        mapView.onStop();
        MapKitFactory.getInstance().onStop();
        super.onStop();
    }

    @Override
    public void onStart() {
        super.onStart();
        MapKitFactory.getInstance().onStart();
        mapView.onStart();
    }
}