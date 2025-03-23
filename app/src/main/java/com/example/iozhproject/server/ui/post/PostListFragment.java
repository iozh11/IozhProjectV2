package com.example.iozhproject.server.ui.post;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.iozhproject.R;
import com.example.iozhproject.databinding.FragmentPostListBinding;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class PostListFragment extends Fragment {

    private FragmentPostListBinding binding;
    private List<Post> posts;

    private static final String API_URL = "http://192.168.1.12:8080/edu/v1/post";

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentPostListBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        posts = new ArrayList<>();
        loadSamplePosts();

        return root;
    }

    private void loadSamplePosts() {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(API_URL)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                requireActivity().runOnUiThread(() -> Toast.makeText(getContext(), "Ошибка загрузки", Toast.LENGTH_SHORT).show());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String jsonResponse = response.body().string();
                    Type listType = new TypeToken<ArrayList<Post>>() {}.getType();
                    posts = new Gson().fromJson(jsonResponse, listType);
                    requireActivity().runOnUiThread(() -> populatePosts());
                } else {
                    requireActivity().runOnUiThread(() -> Toast.makeText(getContext(), "Ошибка ответа", Toast.LENGTH_SHORT).show());
                }
            }
        });
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

        // Добавьте другие поля, если нужно
        private final String imageUrl;

        public Post(String title, String description, String imageUrl) {
            this.title = title;
            this.description = description;
            this.imageUrl = imageUrl;
        }

        public String getTitle() {
            return title;
        }

        public String getDescription() {
            return description;
        }

        public String getImageUrl() {
            return imageUrl; // добавлено для использования, если нужно
        }
    }
}