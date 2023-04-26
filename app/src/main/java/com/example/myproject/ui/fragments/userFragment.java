package com.example.myproject.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.myproject.R;
import com.example.myproject.databinding.UserRBinding;


public class userFragment extends Fragment {


private UserRBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = UserRBinding.inflate(inflater);

        binding.map.setOnClickListener(v->{
            Navigation.findNavController(binding.getRoot()).navigate(R.id.action_accountFragment_to_mapFragment);
        });
        binding.review.setOnClickListener(v->{
            Navigation.findNavController(binding.getRoot()).navigate(R.id.action_accountFragment_to_reviewFragment);
        });
        binding.search.setOnClickListener(v->{
            Navigation.findNavController(binding.getRoot()).navigate(R.id.action_accountFragment_to_searchFragment);
        });

        return binding.getRoot();
    }
}