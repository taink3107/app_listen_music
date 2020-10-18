package com.example.app_music.ui.home;

import android.widget.TableLayout;

import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.viewpager.widget.ViewPager;

import com.example.app_music.adapter.MainViewPageAdapter;

public class HomeViewModel extends ViewModel {


    private MutableLiveData<String> mText;

    public HomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
    /*private void init(){
        FragmentManager manager = new FragmentManager() {
        }
        MainViewPageAdapter viewPageAdapter = new MainViewPageAdapter(getFragmentManager())
    }*/
}