package com.example.myproject.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.myproject.R;
import com.example.myproject.databinding.MapRBinding;
import com.yandex.mapkit.Animation;
import com.yandex.mapkit.MapKitFactory;
import com.yandex.mapkit.geometry.Point;
import com.yandex.mapkit.map.CameraPosition;
import com.yandex.mapkit.mapview.MapView;

public class MapFragment extends Fragment {

    private MapView mapView;

    private MapRBinding binding;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = MapRBinding.inflate(inflater);
        MapKitFactory.initialize(requireContext());

        mapView = binding.mapview;

        mapView.getMap().move(
                new CameraPosition(new Point(55.751574, 37.573856), 11.0f, 0.0f, 0.0f),
                new Animation(Animation.Type.SMOOTH, 0),
                null);
        binding.user.setOnClickListener(v->{
            Navigation.findNavController(binding.getRoot()).navigate(R.id.action_mapFragment_to_singinFragment);
        });
        binding.review.setOnClickListener(v->{
            Navigation.findNavController(binding.getRoot()).navigate(R.id.action_mapFragment_to_reviewFragment);
        });
        binding.search.setOnClickListener(v->{
            Navigation.findNavController(binding.getRoot()).navigate(R.id.action_mapFragment_to_searchFragment);
        });
        return binding.getRoot();
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