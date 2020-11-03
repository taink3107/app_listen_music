package com.example.app_music.ui.home.homecontent.banner;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.app_music.R;
import com.example.app_music.adapter.BannerAdapter;
import com.example.app_music.domain.FireBaseHepler;
import com.example.app_music.domain.Song;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import me.relex.circleindicator.CircleIndicator;

public class BannerFragment extends Fragment implements FireBaseHepler {

    private BannerViewModel mViewModel;
    private CircleIndicator indicator;
    private ViewPager viewPager;
    private BannerAdapter bannerAdapter;
    Runnable runnable;
    Handler handler;
    int currentItem;

    public static BannerFragment newInstance() {
        return new BannerFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.banner_fragment, container, false);

        init(root);
        DATABASE_REFERENCE.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<Song> songList = new ArrayList<>();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Song song = dataSnapshot.getValue(Song.class);
                    songList.add(song);

                }
                Log.i("image_URL", songList.get(0).getImage_URL());
                bannerAdapter = new BannerAdapter(getActivity(), songList);
                viewPager.setAdapter(bannerAdapter);
                indicator.setViewPager(viewPager);
                handler = new Handler();
                runnable = new Runnable() {
                    @Override
                    public void run() {
                        currentItem = viewPager.getCurrentItem();
                        currentItem++;
                        if (currentItem >= viewPager.getAdapter().getCount()) {
                            currentItem = 0;
                        }
                        viewPager.setCurrentItem(currentItem, true);
                        handler.postDelayed(runnable, 4500);
                    }
                };
                handler.postDelayed(runnable, 4500);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return root;
    }

    private void init(View view) {
        viewPager = view.findViewById(R.id.viewPager);
        indicator = view.findViewById(R.id.indicatorFirst);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(BannerViewModel.class);
        // TODO: Use the ViewModel
    }

}