package com.example.iozhproject.server.ui.profile;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.squareup.picasso.Picasso;

import com.example.iozhproject.R;
import com.example.iozhproject.databinding.FragmentProfileBinding;
import com.example.iozhproject.server.domain.entites.FullUserEntity;
import com.example.iozhproject.server.ui.utils.Utils;

public class ProfileFragment extends Fragment {

    private static final String KEY_ID = "id";

    private FragmentProfileBinding binding;
    private ProfileViewModel viewModel;

    public ProfileFragment() {
        super(R.layout.fragment_profile);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = FragmentProfileBinding.bind(view);
        viewModel = new ViewModelProvider(this).get(ProfileViewModel.class);
        viewModel.stateLiveData.observe(getViewLifecycleOwner(), state -> {
            final FullUserEntity entity = state.getUser();
            if (entity == null) return;
            binding.image.setVisibility(Utils.visibleOrGone(entity.getPhotoUrl() != null));
            binding.email.setVisibility(Utils.visibleOrGone(entity.getEmail() != null));
            binding.phone.setVisibility(Utils.visibleOrGone(entity.getPhone() != null));

            binding.name.setText(entity.getName());
            binding.email.setText(entity.getEmail());
            binding.phone.setText(entity.getPhone());

            if (entity.getPhotoUrl() != null) {
                Picasso.get().load(entity.getPhotoUrl()).into(binding.image);
            }
        });

        String id = getArguments() != null ? getArguments().getString(KEY_ID) : null;
        if (id == null) throw new IllegalStateException("ID пустой");
        viewModel.load(id);

    }

    @Override
    public void onDestroyView() {
        binding = null;
        super.onDestroyView();
    }

    public static Bundle getBundle(@NonNull String id) {
        Bundle bundle = new Bundle();
        bundle.putString(KEY_ID, id);
        return bundle;
    }
}
