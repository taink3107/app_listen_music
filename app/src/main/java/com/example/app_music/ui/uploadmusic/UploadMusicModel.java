package com.example.app_music.ui.uploadmusic;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class UploadMusicModel extends ViewModel {

    private MutableLiveData<String> mText;

    public UploadMusicModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is slideshow fragment");
    }



    public LiveData<String> getText() {
        return mText;
    }
}