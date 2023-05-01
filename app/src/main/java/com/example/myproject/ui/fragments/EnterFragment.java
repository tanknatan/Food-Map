package com.example.myproject.ui.fragments;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myproject.R;
import com.example.myproject.databinding.EnterBinding;
import com.example.myproject.databinding.MapRBinding;
import com.example.myproject.viewmodel.EnterViewModel;

public class EnterFragment extends Fragment {
    private EnterBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = EnterBinding.inflate(inflater);
        binding.signbutton.setOnClickListener(v->{
            Navigation.findNavController(binding.getRoot()).navigate(R.id.action_enterFragment_to_registerFragment);
        });
        binding.signbutton.setOnClickListener(v->{
            Navigation.findNavController(binding.getRoot()).navigate(R.id.action_mapFragment_to_reviewFragment);
        });

        return binding.getRoot();
    }

}