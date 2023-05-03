package com.example.myproject.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.myproject.R;
import com.example.myproject.databinding.MapRBinding;
import com.example.myproject.databinding.ReviewRBinding;


public class ReviewFragment extends Fragment {
    private ReviewRBinding binding;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {
        binding = ReviewRBinding.inflate(inflater);
        binding.map.setOnClickListener(v->{
            Navigation.findNavController(binding.getRoot()).navigate(ReviewFragmentDirections.actionReviewFragmentToMapFragment());
        });
        binding.search.setOnClickListener(v->{
            Navigation.findNavController(binding.getRoot()).navigate(ReviewFragmentDirections.actionReviewFragmentToSearchFragment());
        });
        binding.user.setOnClickListener(v->{
            Navigation.findNavController(binding.getRoot()).navigate(ReviewFragmentDirections.actionReviewFragmentToAccountFragment());
        });

        return binding.getRoot();
    }
}