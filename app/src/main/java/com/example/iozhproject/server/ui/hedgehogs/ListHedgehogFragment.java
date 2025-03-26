package com.example.iozhproject.server.ui.hedgehogs;

import android.graphics.Color;
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
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.iozhproject.R;
import com.example.iozhproject.databinding.FragmentAfricanHedgehogBinding;
import com.example.iozhproject.databinding.FragmentListHedgehogBinding;

public class ListHedgehogFragment extends Fragment {

    private FragmentListHedgehogBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ListHedgehogModel listHedgehogModel =
                new ViewModelProvider(this,
                        new ViewModelProvider.Factory() {
                            @NonNull
                            @Override
                            public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
                                if (modelClass.isAssignableFrom(ListHedgehogModel.class)) {
                                    return (T) new ListHedgehogModel(requireContext());
                                }
                                throw new IllegalArgumentException();
                            }
                        }).get(ListHedgehogModel.class);
        binding = FragmentListHedgehogBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_content_main);
        binding.moreAfricanAbout.setOnClickListener(view -> {
            navController.navigate(R.id.action_hedgehogsFragment_to_africanFragment);
        });
        binding.moreCommonAbout.setOnClickListener(view ->{
            navController.navigate(R.id.action_hedgehogsFragment_to_commonFragment);
        });
        binding.moreEarsAbout.setOnClickListener(view ->{
            navController.navigate(R.id.action_hedgehogsFragment_to_earsFragment);
        });
//        final TextView textView = binding.detailsAfricanText;
//        listHedgehogModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}