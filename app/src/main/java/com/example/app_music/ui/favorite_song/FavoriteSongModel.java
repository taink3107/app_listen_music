package com.example.app_music.ui.favorite_song;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class FavoriteSongModel extends ViewModel {

    private MutableLiveData<String> mText;

    public FavoriteSongModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is gallery fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}