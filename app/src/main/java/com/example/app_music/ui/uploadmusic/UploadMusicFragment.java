package com.example.app_music.ui.uploadmusic;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import com.example.app_music.R;
import com.example.app_music.adapter.MainViewPageAdapter;
import com.example.app_music.domain.Song;
import com.example.app_music.ui.uploadmusic.step1.Step1;
import com.example.app_music.ui.uploadmusic.step2.Step2;
import com.example.app_music.ui.uploadmusic.step3.Step3;
import com.google.android.material.tabs.TabLayout;

public class UploadMusicFragment extends Fragment {

    private UploadMusicModel uploadMusicModel;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        uploadMusicModel =
                ViewModelProviders.of(this).get(UploadMusicModel.class);
        View root = inflater.inflate(R.layout.fragment_slideshow, container, false);
        init();
        return root;
    }

    public void init() {
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.frame_container, new Step1());
        transaction.commit();
    }

}