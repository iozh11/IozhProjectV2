package com.example.iozhproject.server.ui.african;

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

public class AfricanFragment extends Fragment {

    private FragmentAfricanHedgehogBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        AfricanModel africanViewModel =
                new ViewModelProvider(this,
                        new ViewModelProvider.Factory() {
                            @NonNull
                            @Override
                            public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
                                if (modelClass.isAssignableFrom(AfricanModel.class)) {
                                    return (T) new AfricanModel(requireContext());
                                }
                                throw new IllegalArgumentException();
                            }
                        }).get(AfricanModel.class);
        binding = FragmentAfricanHedgehogBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Button buttonLifestyle = binding.buttonLifestyleAfrican;
        Button buttonLiving = binding.buttonLivingAfrican;
        Button buttonFacts = binding.buttonFactsAfrican;

        buttonLifestyle.setOnClickListener(v -> toggleSection(binding.textLifestyleAfrican));
        buttonLiving.setOnClickListener(v -> toggleSection(binding.textLivingAfrican));
        buttonFacts.setOnClickListener(v -> toggleSection(binding.textFactsAfrican));

        final TextView textView = binding.textAfricanHedgehog;
        africanViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        return root;
    }

    private void toggleSection(TextView textView) {
        binding.textLifestyleAfrican.setVisibility(View.GONE);
        binding.textLivingAfrican.setVisibility(View.GONE);
        binding.textFactsAfrican.setVisibility(View.GONE);

        textView.setVisibility(textView.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}