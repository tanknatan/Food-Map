package com.example.myproject;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;






public class accountFragment extends Fragment {

    private AppCompatImageButton buttonMap;
    private AppCompatImageButton buttonReview;
    private AppCompatImageButton buttonSearch;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.account_r, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        buttonMap = view.findViewById(R.id.map);
        buttonReview = view.findViewById(R.id.review);
        buttonSearch = view.findViewById(R.id.search);
        buttonMap.setOnClickListener(v->{
            Navigation.findNavController(view).navigate(R.id.map);
        });
        buttonReview.setOnClickListener(v->{
            Navigation.findNavController(view).navigate(R.id.action_accountFragment_to_mapFragment);
        });
        buttonSearch.setOnClickListener(v->{
            Navigation.findNavController(view).navigate(R.id.action_mapFragment_to_searchFragment);
        });
    }


}