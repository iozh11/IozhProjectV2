package com.example.iozhproject.server.ui.common;

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

import com.example.iozhproject.databinding.FragmentCommonHedgehogBinding;

public class CommonFragment extends Fragment {

    private FragmentCommonHedgehogBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        CommonModel africanViewModel =
                new ViewModelProvider(this,
                        new ViewModelProvider.Factory() {
                            @NonNull
                            @Override
                            public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
                                if (modelClass.isAssignableFrom(CommonModel.class)) {
                                    return (T) new CommonModel(requireContext());
                                }
                                throw new IllegalArgumentException();
                            }
                        }).get(CommonModel.class);
        binding = FragmentCommonHedgehogBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Button buttonLifestyle = binding.buttonLifestyleCommon;
        Button buttonLiving = binding.buttonLivingCommon;
        Button buttonFacts = binding.buttonFactsCommon;

        buttonLifestyle.setOnClickListener(v -> toggleSection(binding.textLifestyleCommon));
        buttonLiving.setOnClickListener(v -> toggleSection(binding.textLivingCommon));
        buttonFacts.setOnClickListener(v -> toggleSection(binding.textFactsCommon));

        final TextView textView = binding.textCommonHedgehog;
        africanViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        return root;
    }

    private void toggleSection(TextView textView) {
        binding.textLifestyleCommon.setVisibility(View.GONE);
        binding.textLivingCommon.setVisibility(View.GONE);
        binding.textFactsCommon.setVisibility(View.GONE);

        textView.setVisibility(textView.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}