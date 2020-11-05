package com.example.app_music.ui.uploadmusic.step2;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.app_music.R;
import com.example.app_music.domain.Song;
import com.example.app_music.ui.uploadmusic.step3.Step3;
import com.squareup.picasso.Picasso;

import java.util.Optional;

public class Step2 extends Fragment {

    private static final int PICK_IMG_FILE_REQUEST = 1;
    //  private TextView button;
    private ImageView view;
    private Button btn_choose_image_song;
    private EditText txt_name_song_upload;
    private EditText txt_name_singer_upload;
    private EditText txt_lyrics_upload;
    private View root;
    private Song song;
    private Button btn_next_step2;
    private Uri uri_img;
    private ImageView imageView_upload;

    public static Step2 newInstance() {
        return new Step2();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        root = inflater.inflate(R.layout.step2_fragment, container, false);
        Bundle bundle = getArguments();
        if (bundle != null) {
            song = (Song) bundle.getSerializable("new_song");
            Log.i("data_song", song.toString());
        }
        init();
        btn_choose_image_song.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFileChooser("image/*");
            }
        });
        btn_next_step2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendDataToStep3();
            }
        });
        return root;
    }

    private void sendDataToStep3() {

        String name_song_upload = txt_name_song_upload.getText().toString();
        String singer_upload = txt_name_singer_upload.getText().toString();
        Optional.of(txt_lyrics_upload.getText().toString()).ifPresent(temp -> song.setLyrics(temp));
        if (name_song_upload.isEmpty()) {
            Toast.makeText(this.getContext(), "Name of song is required", Toast.LENGTH_LONG).show();
        } else if (singer_upload.isEmpty()) {
            Toast.makeText(this.getContext(), "Name of singer is required", Toast.LENGTH_LONG).show();
        } else {
            song.setSong_name(name_song_upload);
            song.setSingers(singer_upload);
            Bundle bundle = new Bundle();
            bundle.putSerializable("new_song_step2", song);
            FragmentManager manager = getActivity().getSupportFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            Step3 step3 = new Step3();
            step3.setArguments(bundle);
            transaction.replace(R.id.frame_container, step3);
            transaction.commit();
        }


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == PICK_IMG_FILE_REQUEST) {
            if (resultCode == AppCompatActivity.RESULT_OK && data != null) {
                uri_img = data.getData();
                Picasso.get().load(uri_img).centerCrop().fit().into(imageView_upload);
                song.setImage_URL(String.valueOf(uri_img));
            } else {
                Toast.makeText(getContext(), "No image is choose", Toast.LENGTH_LONG).show();
            }
        }
    }

    private void init() {
        btn_choose_image_song = root.findViewById(R.id.btn_submit_image_URL);
        txt_name_song_upload = root.findViewById(R.id.edit_text_name_song2);
        txt_name_singer_upload = root.findViewById(R.id.edit_text_name_singer_upload);
        txt_lyrics_upload = root.findViewById(R.id.edit_text_lyrics_upload);
        btn_next_step2 = root.findViewById(R.id.btn_next_stepp2);
        imageView_upload = root.findViewById(R.id.img_view_upload_sucess);
    }

    public void openFileChooser(String type) {
        Intent intent = new Intent();
        intent.setType(type);
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMG_FILE_REQUEST);
    }


}