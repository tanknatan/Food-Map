package com.example.myproject.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.myproject.R;
import com.example.myproject.databinding.SinginRBinding;

public class singinFragment extends Fragment {

    private SinginRBinding binding;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = SinginRBinding.inflate(inflater);
        binding.enter.setOnClickListener(v->{
            Navigation.findNavController(binding.getRoot()).navigate(R.id.action_singinFragment_to_enterFragment);
        });
        binding.map.setOnClickListener(v -> {
            Navigation.findNavController(binding.getRoot()).navigate(R.id.action_singinFragment_to_mapFragment);
        });
        binding.review.setOnClickListener(v -> {
            Navigation.findNavController(binding.getRoot()).navigate(R.id.action_singinFragment_to_reviewFragment);
        });
        binding.search.setOnClickListener(v -> {
            Navigation.findNavController(binding.getRoot()).navigate(R.id.action_singinFragment_to_searchFragment);
        });
        return binding.getRoot();
    }
}