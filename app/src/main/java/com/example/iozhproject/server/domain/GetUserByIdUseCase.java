package com.example.iozhproject.server.domain;

import androidx.annotation.NonNull;

import com.example.iozhproject.server.domain.entites.FullUserEntity;
import com.example.iozhproject.server.domain.entites.Status;

import java.util.function.Consumer;

public class GetUserByIdUseCase {
    private final UserRepository repo;

    public GetUserByIdUseCase(UserRepository repo) {
        this.repo = repo;
    }

    public void execute(@NonNull String id, @NonNull Consumer<Status<FullUserEntity>> callback) {
        repo.getUser(id, callback);
    }
}
