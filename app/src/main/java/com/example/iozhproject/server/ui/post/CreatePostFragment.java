package com.example.iozhproject.server.ui.post;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.iozhproject.databinding.FragmentCreatePostBinding;

public class CreatePostFragment extends Fragment {

    private FragmentCreatePostBinding binding;
    private EditText titleP;
    private EditText describleP;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentCreatePostBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        titleP = binding.titlePost;
        describleP = binding.describlePost;


        setupEditText();
        binding.pushPost.setOnClickListener(v -> sendDataPost());


        return root;
    }

    private void sendDataPost() {
        String title = titleP.getText().toString().trim();
        String description = describleP.getText().toString().trim();

//        PostListFragment postListFragment = new PostListFragment();
//        postListFragment.loadSamplePosts(title, description);

        Toast.makeText(requireContext(), "Успешно", Toast.LENGTH_SHORT).show();
        titleP.setText("");
        describleP.setText("");
    }


    private void setupEditText() {
        describleP.setVerticalScrollBarEnabled(true);
        describleP.setHorizontallyScrolling(false);

        describleP.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                describleP.setHeight(700);
            } else {
                describleP.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
            }
        });

        describleP.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_UP) {
                describleP.scrollTo(0, describleP.getHeight());
            }
            return false;
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}