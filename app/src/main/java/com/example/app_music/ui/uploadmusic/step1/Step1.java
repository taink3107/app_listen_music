package com.example.app_music.ui.uploadmusic.step1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.OpenableColumns;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.app_music.R;
import com.example.app_music.domain.Song;
import com.example.app_music.ui.uploadmusic.step2.Step2;

import java.util.Arrays;

public class Step1 extends Fragment {

    private static final int PICK_MUSIC_FILE_REQUEST = 1;
    private ImageView imageView;
    private Uri uriData;
    private Step1ViewModel mViewModel;
    private Button button;


    public static Step1 newInstance() {
        return new Step1();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.step1_fragment, container, false);
        imageView = root.findViewById(R.id.img_upload);
        button = root.findViewById(R.id.btnNext);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFileChooser("*/*");
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendUri();
            }
        });

        return root;
    }

    public void openFileChooser(String type) {
        Intent intent = new Intent();
        intent.setType(type);
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_MUSIC_FILE_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_MUSIC_FILE_REQUEST && resultCode == AppCompatActivity.RESULT_OK && data != null && data.getData() != null) {
            uriData = data.getData();

        } else {
        }
    }

    public void sendUri() {
        if (uriData != null) {
            Song song = new Song();
            song.processUri(getFileName(uriData));
            song.setMp3_URI(uriData.toString());
            Bundle bundle = new Bundle();
            bundle.putSerializable("new_song", song);
            FragmentManager manager = getActivity().getSupportFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            Step2 step2 = new Step2();
            step2.setArguments(bundle);
            transaction.replace(R.id.frame_container, step2);
            transaction.commit();
        } else {
            Toast.makeText(this.getContext(), "No file is choose", Toast.LENGTH_LONG).show();
        }

    }


    public String getFileName(Uri uri) {
        String result = null;
        if (uri.getScheme().equals("content")) {
            Cursor cursor = getActivity().getContentResolver().query(uri, null, null, null, null);
            try {
                if (cursor != null && cursor.moveToFirst()) {
                    result = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                }
            } finally {
                cursor.close();
            }
        }
        if (result == null) {
            result = uri.getPath();
            int cut = result.lastIndexOf('/');
            if (cut != -1) {
                result = result.substring(cut + 1);
            }
        }
        return result;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(Step1ViewModel.class);
        // TODO: Use the ViewModel
    }

}