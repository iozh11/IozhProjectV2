package com.example.iozhproject.server.ui.african;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import android.content.Context;

import com.example.iozhproject.R;

public class AfricanModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public AfricanModel(Context context) {
        mText = new MutableLiveData<>();
        mText.setValue(context.getString(R.string.ab_iozh_african));
    }

    public LiveData<String> getText() {
        return mText;
    }
}