package com.example.iozhproject.server.ui.common;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import android.content.Context;

import com.example.iozhproject.R;

public class CommonModel extends ViewModel {
    private final MutableLiveData<String> mText;

    public CommonModel(Context context) {
        mText = new MutableLiveData<>();
        mText.setValue(context.getString(R.string.ab_iozh_common));
    }

    public LiveData<String> getText() {
        return mText;
    }
}