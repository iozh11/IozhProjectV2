package com.example.iozhproject.server.ui.list;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.iozhproject.server.data.UserRepositoryImpl;
import com.example.iozhproject.server.domain.GetUsersListUseCase;
import com.example.iozhproject.server.domain.entites.ItemUserEntity;
import com.example.iozhproject.server.domain.entites.Status;

import java.util.List;

public class UserListViewModel extends ViewModel {
    private final MutableLiveData<State> mutableStateLiveData = new MutableLiveData<>();

    public final LiveData<State> stateLiveData = mutableStateLiveData;

    private final GetUsersListUseCase getUsersListUseCase = new GetUsersListUseCase(
            UserRepositoryImpl.getInstance()
    );

    public UserListViewModel() {
        update();
    }

    public void update() {
        mutableStateLiveData.setValue(new State(null, null, true));
        getUsersListUseCase.execute(status -> {
            mutableStateLiveData.postValue(fromStatus(status));
        });
    }

    private State fromStatus(Status<List<ItemUserEntity>> status) {
        return new State(
                status.getErrors() != null ? status.getErrors().getLocalizedMessage() : null,
                status.getValue(),
                false
        );
    }

    public class State {
        @Nullable
        private final String errorMessage;

        @Nullable
        private final List<ItemUserEntity> items;

        private final boolean isLoading;

        public State(@Nullable String errorMessage, @Nullable List<ItemUserEntity> items, boolean isLoading) {
            this.errorMessage = errorMessage;
            this.items = items;
            this.isLoading = isLoading;
        }

        @Nullable
        public String getErrorMessage() {
            return errorMessage;
        }

        @Nullable
        public List<ItemUserEntity> getItems() {
            return items;
        }

        public boolean isLoading() {
            return isLoading;
        }
    }
}
