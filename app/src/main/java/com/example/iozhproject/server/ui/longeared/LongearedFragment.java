package com.example.iozhproject.server.ui.longeared;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.iozhproject.databinding.FragmentAfricanHedgehogBinding;
import com.example.iozhproject.databinding.FragmentLongearedBinding;
import com.example.iozhproject.server.ui.african.AfricanModel;


public class LongearedFragment extends Fragment {

    private FragmentLongearedBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        LongearedModel longearedModel =
                new ViewModelProvider(this,
                        new ViewModelProvider.Factory() {
                            @NonNull
                            @Override
                            public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
                                if (modelClass.isAssignableFrom(LongearedModel.class)) {
                                    return (T) new LongearedModel(requireContext());
                                }
                                throw new IllegalArgumentException();
                            }
                        }).get(LongearedModel.class);
        binding = FragmentLongearedBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Button buttonLifestyle = binding.buttonLifestyleLongeared;
        Button buttonLiving = binding.buttonLivingLongeared;
        Button buttonFacts = binding.buttonFactsLongeared;

        buttonLifestyle.setOnClickListener(v -> toggleSection(binding.textLifestyleLongeared));
        buttonLiving.setOnClickListener(v -> toggleSection(binding.textLivingLongeared));
        buttonFacts.setOnClickListener(v -> toggleSection(binding.textFactsLongeared));

        final TextView textView = binding.textLongearedHedgehog;
        longearedModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        return root;
    }

    private void toggleSection(TextView textView) {
        binding.textLifestyleLongeared.setVisibility(View.GONE);
        binding.textLivingLongeared.setVisibility(View.GONE);
        binding.textFactsLongeared.setVisibility(View.GONE);

        textView.setVisibility(textView.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}