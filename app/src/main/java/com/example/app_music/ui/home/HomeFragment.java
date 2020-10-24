package com.example.app_music.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import com.example.app_music.R;
import com.example.app_music.adapter.MainViewPageAdapter;
import com.example.app_music.ui.home.homecontent.HomeContentFragment;
import com.example.app_music.ui.home.search.SearchFragment;
import com.google.android.material.tabs.TabLayout;

public class HomeFragment extends Fragment {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private HomeViewModel viewModel;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        tabLayout = root.findViewById(R.id.myTabLayout);
        viewPager = root.findViewById(R.id.myViewPager);

        viewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        viewModel.setDataAdapter(getChildFragmentManager());
        viewModel.getDataAdapter().observe(getViewLifecycleOwner(), new Observer<MainViewPageAdapter>() {
            @Override
            public void onChanged(MainViewPageAdapter mainViewPageAdapter) {
                tabLayout.setupWithViewPager(viewPager);
                viewPager.setAdapter(mainViewPageAdapter);
                tabLayout.getTabAt(0).setIcon(R.drawable.ic_home);
                tabLayout.getTabAt(1).setIcon(R.drawable.iconsearch);
            }
        });
        return root;
    }

}