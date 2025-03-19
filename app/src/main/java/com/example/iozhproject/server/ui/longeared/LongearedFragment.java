package com.example.iozhproject.server.ui.longeared;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.iozhproject.databinding.FragmentLongearedBinding;


public class LongearedFragment extends Fragment {

    private FragmentLongearedBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        LongearedModel longearedViewModel =
                new ViewModelProvider(this).get(LongearedModel.class);

        binding = FragmentLongearedBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textLongeared;
        longearedViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}