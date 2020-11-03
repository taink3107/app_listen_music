package com.example.app_music.ui.home.homecontent.playlist;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.app_music.R;
import com.example.app_music.domain.FireBaseHepler;
import com.google.firebase.database.FirebaseDatabase;

public class PlayListFragment extends Fragment implements FireBaseHepler {

    private PlayListViewModel mViewModel;
    private View view;

    public static PlayListFragment newInstance() {
        return new PlayListFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.play_list_fragment, container, false);
       // FirebaseDatabase.getInstance().getReference().child("xxx").orderByChild()
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(PlayListViewModel.class);
        // TODO: Use the ViewModel
    }

}