package com.example.iozhproject.server.ui.hedgehogs;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.iozhproject.R;

public class ListHedgehogModel extends ViewModel {
    private final MutableLiveData<String> mText;

    public ListHedgehogModel(Context context) {
        mText = new MutableLiveData<>();
        mText.setValue(context.getString(R.string.pr_iozh_african));
    }

    public LiveData<String> getText() {
        return mText;
    }
}
