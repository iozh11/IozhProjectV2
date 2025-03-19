package com.example.iozhproject.server.domain.sign;

import androidx.annotation.NonNull;

import com.example.iozhproject.server.domain.entites.Status;

import java.util.function.Consumer;

public interface SignUserRepository {
    void isExistUser(@NonNull String login, Consumer<Status<Void>> callback);
    void createAccount(
            @NonNull String login,
            @NonNull String password,
            Consumer<Status<Void>> callback
    );
    void login(
            @NonNull String login,
            @NonNull String password,
            Consumer<Status<Void>> callback
    );

    void logout();
}
