package com.example.iozhproject.server.domain;

import androidx.annotation.NonNull;

import com.example.iozhproject.server.domain.entites.FullUserEntity;
import com.example.iozhproject.server.domain.entites.ItemUserEntity;
import com.example.iozhproject.server.domain.entites.Status;

import java.util.List;
import java.util.function.Consumer;

public interface UserRepository {
    void getAllUsers(@NonNull Consumer<Status<List<ItemUserEntity>>> callback);

    void getUser(@NonNull String id, @NonNull Consumer<Status<FullUserEntity>> callback);
}
