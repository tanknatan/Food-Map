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
import com.example.myproject.databinding.RegisterBinding;
import com.example.myproject.viewmodel.RegisterViewModel;

public class RegisterFragment extends Fragment {

    private RegisterBinding binding;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = RegisterBinding.inflate(inflater);
        binding.alreadyHaveAccBtn.setOnClickListener(v->{
            Navigation.findNavController(binding.getRoot()).navigate(R.id.action_registerFragment_to_enterFragment);
        });
        return binding.getRoot();
    }
}