package com.example.app_music.ui.uploadmusic.step2;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.app_music.R;
import com.example.app_music.domain.Song;

public class Step2 extends Fragment {

    //  private TextView button;
    private ImageView view;

    public static Step2 newInstance() {
        return new Step2();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.step2_fragment, container, false);
        Bundle bundle = getArguments();
        // button = root.findViewById(R.id.txt_displaySong1);

        view = root.findViewById(R.id.img_view);
        view.setImageResource(R.drawable.iconloved);
        if (bundle != null) {
            Song song = (Song) bundle.getSerializable("new_song");
            //  button.setText("name " + song.getSong_name() + " singer " + song.getSingers() + " uri " + song.getMp3_URI());
        }
        return root;
    }


}