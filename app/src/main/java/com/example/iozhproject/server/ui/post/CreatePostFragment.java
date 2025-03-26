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

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class CreatePostFragment extends Fragment {

    private FragmentCreatePostBinding binding;
    private EditText titleP;
    private EditText describleP;

    private static final String API_URL = "http://192.168.245.114:8080/edu/v1/post/new";

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

        JSONObject postData = new JSONObject();
        try {
            postData.put("title", title);
            postData.put("description", description);
            postData.put("photoURL", "ваше_значение");
        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(requireContext(), "Ошибка формирования данных", Toast.LENGTH_SHORT).show();
            return;
        }

        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(postData.toString(), MediaType.parse("application/json; charset=utf-8"));
        Request request = new Request.Builder()
                .url(API_URL)
                .post(body)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                requireActivity().runOnUiThread(() -> Toast.makeText(getContext(), "Ошибка отправки данных", Toast.LENGTH_SHORT).show());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    requireActivity().runOnUiThread(() -> {
                        Toast.makeText(getContext(), "Пост успешно добавлен", Toast.LENGTH_SHORT).show();
                        titleP.setText("");
                        describleP.setText("");
                    });
                } else {
                    requireActivity().runOnUiThread(() -> Toast.makeText(getContext(), "Ошибка ответа сервера", Toast.LENGTH_SHORT).show());
                }
            }
        });
    }

    private void setupEditText() {
        describleP.setVerticalScrollBarEnabled(true);
        describleP.setHorizontallyScrolling(false);


        titleP.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                titleP.setHeight(200);
            } else {
                titleP.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
            }
        });

        titleP.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_UP) {
                titleP.scrollTo(0, titleP.getHeight());
            }
            return false;
        });

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