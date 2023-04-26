package com.example.myproject.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.myproject.R;
import com.example.myproject.databinding.UserRBinding;
import com.example.myproject.databinding.SearchRBinding;


public class SearchFragment extends Fragment {

private SearchRBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = SearchRBinding.inflate(inflater);

        binding.map.setOnClickListener(v->{
            Navigation.findNavController(binding.getRoot()).navigate(R.id.action_searchFragment_to_mapFragment);
        });
        binding.review.setOnClickListener(v->{
            Navigation.findNavController(binding.getRoot()).navigate(R.id.action_searchFragment_to_reviewFragment);
        });
        binding.user.setOnClickListener(v->{
            Navigation.findNavController(binding.getRoot()).navigate(R.id.action_searchFragment_to_singinFragment);
        });

        return binding.getRoot();
    }
}