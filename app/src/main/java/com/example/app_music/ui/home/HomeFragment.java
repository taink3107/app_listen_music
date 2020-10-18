package com.example.app_music.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.app_music.R;
import com.example.app_music.adapter.MainViewPageAdapter;
import com.example.app_music.ui.home.homecontent.HomeContentFragment;
import com.example.app_music.ui.home.search.SearchFragment;
import com.google.android.material.tabs.TabLayout;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        tabLayout = root.findViewById(R.id.myTabLayout);
        viewPager = root.findViewById(R.id.myViewPager);
        MainViewPageAdapter viewPageAdapter = new MainViewPageAdapter(getFragmentManager());
        init();
        return root;
    }

    private void init() {
        MainViewPageAdapter viewPageAdapter = new MainViewPageAdapter(getFragmentManager());
        viewPageAdapter.addFrament(new HomeContentFragment(), "Home");
        viewPageAdapter.addFrament(new SearchFragment(), "Search");
        viewPager.setAdapter(viewPageAdapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_home);
        tabLayout.getTabAt(1).setIcon(R.drawable.iconsearch);
    }
}