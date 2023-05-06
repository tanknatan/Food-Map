package com.example.myproject.ui.fragments;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.PointF;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import com.example.myproject.R;
import com.example.myproject.databinding.MapRBinding;
import com.yandex.mapkit.Animation;
import com.yandex.mapkit.MapKit;
import com.yandex.mapkit.MapKitFactory;
import com.yandex.mapkit.geometry.Point;
import com.yandex.mapkit.layers.ObjectEvent;
import com.yandex.mapkit.map.CameraPosition;
import com.yandex.mapkit.map.IconStyle;
import com.yandex.mapkit.map.MapObjectCollection;
import com.yandex.mapkit.map.RotationType;
import com.yandex.mapkit.mapview.MapView;
import com.yandex.mapkit.user_location.UserLocationLayer;
import com.yandex.mapkit.user_location.UserLocationObjectListener;
import com.yandex.mapkit.user_location.UserLocationView;
import com.yandex.runtime.image.ImageProvider;

public class MapFragment extends Fragment {

    private UserLocationLayer userLocationLayer;
    private MapObjectCollection mapObjects;
    private double userLatitude = 55.7515;
    private double userLongitude = 37.64;

    private MapKit mapKit;
    private float zoomOnUser = 9.5f;
    private MapView mapView;
    private static final int REQUEST_LOCATION_PERMISSION = 1;

    private MapRBinding binding;
    private Point userPoint;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = MapRBinding.inflate(inflater);
//        PlaceViewModel =  new ViewModelProvider(getActivity()).get(PlaceViewModel.class);
        MapKitFactory.initialize(requireContext());

        mapView = binding.mapview;


        mapView.getMap().move(
                new CameraPosition(new Point(55.751574, 37.573856), 11.0f, 0.0f, 0.0f),
                new Animation(Animation.Type.SMOOTH, 0),
                null);
        binding.user.setOnClickListener(v -> {
            Navigation.findNavController(binding.getRoot()).navigate(MapFragmentDirections.actionMapFragmentToSinginFragment());
        });
        binding.review.setOnClickListener(v -> {
            Navigation.findNavController(binding.getRoot()).navigate(MapFragmentDirections.actionMapFragmentToReviewFragment());
        });
        binding.search.setOnClickListener(v -> {
            Navigation.findNavController(binding.getRoot()).navigate(MapFragmentDirections.actionMapFragmentToSearchFragment());
        });

        mapKit = MapKitFactory.getInstance();
        userLocationLayer = mapKit.createUserLocationLayer(mapView.getMapWindow());

        userLocationLayer.setHeadingEnabled(true);

        mapObjects = mapView.getMap().getMapObjects().addCollection();

        userLocationLayer.setVisible(false);
        userLocationLayer.setObjectListener(locationObjectListener);

        binding.zoomUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mapView.getMap().move(new CameraPosition(mapView.getMap().getCameraPosition().getTarget(),
                                mapView.getMap().getCameraPosition().getZoom() + 1, 0.0f, 0.0f),
                        new Animation(Animation.Type.SMOOTH, 1),
                        null);
            }
        });
        binding.zoomDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mapView.getMap().move(new CameraPosition(mapView.getMap().getCameraPosition().getTarget(),
                                mapView.getMap().getCameraPosition().getZoom() - 1, 0.0f, 0.0f),
                        new Animation(Animation.Type.SMOOTH, 1),
                        null);
            }
        });
        binding.positionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mapView.getMap().move(
                        new CameraPosition(userLocationLayer.cameraPosition().getTarget(), 15.0f, 0.0f, 0.0f),
                        new Animation(Animation.Type.SMOOTH, 1),
                        null);
            }
        });
        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mapView.getMap().move(
                new CameraPosition(new Point(55.7515, 37.64), 9, 0.0f, 0.0f));


        if (!checkAvaibleUserLocationAccess()) {
            requestUserLocation();
            mapView.getMap().move(
                    new CameraPosition(new Point(userLatitude, userLongitude), zoomOnUser, 0.0f, 0.0f));
        } else {
            userLocationLayer.setVisible(true);
            jumpToUser(1.5f);
        }

        binding.positionButton.setOnClickListener((v) -> {
            jumpToUser(2);
        });

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


    private boolean checkAvaibleUserLocationAccess() {
        return ActivityCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED;
    }

    private boolean updateUserLocation() { // Возвращает true - если обновлена, false - если нет возможности и установила по умолчанию

        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return false;
        }

        userPoint = new Point(userLatitude, userLongitude);

        if (userLongitude == 0 || userLatitude == 0) {
            userPoint = new Point(55.7515, 37.64);
            zoomOnUser = 9.5f;
            return false;
        } else {
            return true;
        }
    }

    private void jumpToUser(float cameraDuration) {
        if (!checkAvaibleUserLocationAccess()) {
            requestUserLocation();
        } else {
            if (!updateUserLocation()) {
                Toast.makeText(getActivity(), "Please, turn on your location", Toast.LENGTH_SHORT).show();
            }
            userLocationLayer.setVisible(true);
            mapView.getMap().move(
                    new CameraPosition(userPoint, zoomOnUser, 0.0f, 45f),
                    new Animation(Animation.Type.SMOOTH, cameraDuration), null);
        }
    }

    UserLocationObjectListener locationObjectListener = new UserLocationObjectListener() {
        @Override
        public void onObjectAdded(@NonNull UserLocationView userLocationView) {
            userLocationLayer.setAnchor(
                    new PointF((float) (mapView.getWidth() * 0.5), (float) (mapView.getHeight() * 0.5)),
                    new PointF((float) (mapView.getWidth() * 0.5), (float) (mapView.getHeight() * 0.83)));

            userLocationView.getAccuracyCircle().setFillColor(Color.argb(200, 233, 249, 228));

            userLocationView.getArrow().setIcon(ImageProvider.fromResource(getActivity(), R.drawable.free_icon_location_pin_8509029_1),
                    new IconStyle().setAnchor(new PointF(0.5f, 0.7f))
                            .setRotationType(RotationType.NO_ROTATION)
                            .setScale(0.065f));

            userLocationView.getPin().setIcon(ImageProvider.fromResource(getActivity(), R.drawable.free_icon_location_pin_8509029_1),
                    new IconStyle().setAnchor(new PointF(0.5f, 0.7f))
                            .setRotationType(RotationType.NO_ROTATION)
                            .setScale(0.065f));

        }

        @Override
        public void onObjectRemoved(@NonNull UserLocationView userLocationView) {
        }

        @Override
        public void onObjectUpdated(@NonNull UserLocationView userLocationView, @NonNull ObjectEvent objectEvent) {
        }
    };
    private void requestUserLocation() {
        ActivityCompat
                .requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION_PERMISSION);
        ActivityCompat
                .requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_BACKGROUND_LOCATION}, REQUEST_LOCATION_PERMISSION);
    }
}