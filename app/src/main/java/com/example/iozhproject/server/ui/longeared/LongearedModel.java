package com.example.iozhproject.server.ui.longeared;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.iozhproject.R;

public class LongearedModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public LongearedModel(Context context) {
        mText = new MutableLiveData<>();
        mText.setValue(context.getString(R.string.ab_iozh_longeared));
    }

    public LiveData<String> getText() {
        return mText;
    }
}