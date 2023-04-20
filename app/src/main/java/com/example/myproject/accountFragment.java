package com.example.myproject;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

public class accountFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.account_r, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        requireView().findViewById(R.id.map).setOnClickListener(v -> {
            Navigation.findNavController(requireView()).navigate(R.id.action_accountFragment_to_mapFragment);
        });
//        requireView().findViewById(R.id.search).setOnClickListener(v -> {
//            Navigation.findNavController(requireView()).navigate(R.id.action_accountFragment_to_searchFragment);
//        });
//        requireView().findViewById(R.id.review).setOnClickListener(v -> {
//            Navigation.findNavController(requireView()).navigate(R.id.action_mapFragment_to_reviewFragment);
//        });
    }

}