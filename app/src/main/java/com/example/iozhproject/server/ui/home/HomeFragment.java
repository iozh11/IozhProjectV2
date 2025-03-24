package com.example.iozhproject.server.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.iozhproject.R;
import com.example.iozhproject.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_content_main);
        binding.aboutProjectButton.setOnClickListener(view -> {
            navController.navigate(R.id.action_homeFragment_to_aboutFragment);
        });
        binding.createPostButton.setOnClickListener(view ->{
            navController.navigate(R.id.action_homeFragment_to_createPostFragment);
        });
        binding.newsButton.setOnClickListener(view ->{
            navController.navigate(R.id.action_homeFragment_to_listPostFragment);
        });
        binding.homePageButton.setOnClickListener(view -> {
            Toast.makeText(getContext(), "Вы на главной странице", Toast.LENGTH_SHORT).show();
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}