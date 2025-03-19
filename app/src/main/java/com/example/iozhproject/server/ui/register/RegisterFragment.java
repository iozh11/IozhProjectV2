package com.example.iozhproject.server.ui.register;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.iozhproject.databinding.FragmentRegisterBinding;


public class RegisterFragment extends Fragment {

    private FragmentRegisterBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        RegisterModel registerViewModel =
                new ViewModelProvider(this).get(RegisterModel.class);

        binding = FragmentRegisterBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textRegister;
        registerViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}