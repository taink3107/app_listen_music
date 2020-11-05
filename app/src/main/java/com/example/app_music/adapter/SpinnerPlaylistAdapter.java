package com.example.app_music.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.app_music.R;
import com.example.app_music.domain.Playlist;

import java.util.List;

public class SpinnerPlaylistAdapter extends ArrayAdapter<Playlist> {

    public SpinnerPlaylistAdapter(@NonNull Context context, int resource, @NonNull List<Playlist> objects) {
        super(context, resource, objects);

    }

    class SpinnerHolder {
        TextView txt_id;
        TextView txt_name;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        SpinnerHolder spinnerHolder = null;
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.content_spinner, null);
            spinnerHolder = new SpinnerHolder();
            spinnerHolder.txt_id = convertView.findViewById(R.id.txt_id_spinner);
            spinnerHolder.txt_name = convertView.findViewById(R.id.txt_name_spinner);
            convertView.setTag(spinnerHolder);
        } else {
            spinnerHolder = (SpinnerHolder) convertView.getTag();
        }
        Playlist playlist = getItem(position);
      //  spinnerHolder.txt_id.setText(playlist.getId());
        spinnerHolder.txt_name.setText(playlist.getName_playlist());
        return convertView;
    }


}
