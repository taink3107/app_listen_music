package com.example.app_music.ui.home;

import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.app_music.adapter.MainViewPageAdapter;
import com.example.app_music.ui.home.homecontent.HomeContentFragment;
import com.example.app_music.ui.home.search.SearchFragment;

public class HomeViewModel extends ViewModel {


    private MutableLiveData<MainViewPageAdapter> dataAdapter;

    public MutableLiveData<MainViewPageAdapter> getDataAdapter() {
        return dataAdapter;
    }

    public HomeViewModel() {
        dataAdapter = new MutableLiveData<>();
    }


    public void setDataAdapter(FragmentManager manager) {
        MainViewPageAdapter adapter = new MainViewPageAdapter(manager);
        adapter.addFrament(new HomeContentFragment(), "Home");
        adapter.addFrament(new SearchFragment(), "Search");
        dataAdapter.setValue(adapter);
    }
}