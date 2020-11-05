package com.example.app_music.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.app_music.R;
import com.example.app_music.domain.Song;
import com.squareup.picasso.Picasso;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BannerAdapter extends PagerAdapter {
    Context context;
    List<Song> songs;

    @Override
    public int getCount() {
        return songs.size();
    }

    public BannerAdapter(Context context, List<Song> songs) {
        this.context = context;
        this.songs = songs;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_banner_fragment, null);
        ImageView img_background = view.findViewById(R.id.imageView_temp);
        ImageView img_song_banner = view.findViewById(R.id.img_view_banner);
        TextView txt_title_song_banner = view.findViewById(R.id.txt_title_banner_song);
        TextView txt_content = view.findViewById(R.id.txt_content);
        Picasso.get().load(songs.get(position).getImage_URL()).fit().centerCrop().into(img_background);
        Picasso.get().load(songs.get(position).getImage_URL()).fit().centerCrop().into(img_song_banner);
        txt_title_song_banner.setText(songs.get(position).getSong_name());
        txt_content.setText(songs.get(position).getContent());
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
