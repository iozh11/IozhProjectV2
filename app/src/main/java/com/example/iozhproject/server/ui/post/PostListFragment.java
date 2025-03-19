package com.example.iozhproject.server.ui.post;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.iozhproject.R;
import com.example.iozhproject.databinding.FragmentPostListBinding;

import java.util.ArrayList;
import java.util.List;

public class PostListFragment extends Fragment {

    private FragmentPostListBinding binding;
    private List<Post> posts;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentPostListBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        posts = new ArrayList<>();
//        loadSamplePosts("new", "iozhik");
//        loadSamplePosts("kha", "nya");
        populatePosts();

        return root;
    }

    private void loadSamplePosts(String title, String describe) {
        posts.add(new Post(title, describe));
        populatePosts();
    }

    private void populatePosts() {
        LinearLayout postsContainer = binding.postsContainer;
        postsContainer.removeAllViews();

        for (Post post : posts) {
            View postView = LayoutInflater.from(getContext()).inflate(R.layout.post_item, postsContainer, false);

            TextView titleTextView = postView.findViewById(R.id.titleTextView);
            TextView descriptionTextView = postView.findViewById(R.id.descriptionTextView);

            titleTextView.setText(post.getTitle());
            descriptionTextView.setText(post.getDescription());

            postsContainer.addView(postView);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private static class Post {
        private final String title;
        private final String description;

        public Post(String title, String description) {
            this.title = title;
            this.description = description;
        }

        public String getTitle() {
            return title;
        }

        public String getDescription() {
            return description;
        }
    }
}