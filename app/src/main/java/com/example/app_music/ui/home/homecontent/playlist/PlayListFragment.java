package com.example.app_music.ui.home.homecontent.playlist;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.app_music.R;
import com.example.app_music.adapter.PlaylistAdapter;
import com.example.app_music.domain.FireBaseHepler;
import com.example.app_music.domain.Playlist;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class PlayListFragment extends Fragment implements FireBaseHepler {

    private PlayListViewModel mViewModel;
    private View view;
    PlaylistAdapter adapter;
    List<Playlist> playlists;
    ListView listView;
    TextView title_view_playlist;
    TextView txt_view_more;

    public static PlayListFragment newInstance() {
        return new PlayListFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.play_list_fragment, container, false);
        listView = view.findViewById(R.id.list_view_playlist);
        title_view_playlist = view.findViewById(R.id.title_view_playlist);
        txt_view_more = view.findViewById(R.id.txt_view_more_playlist);
        DATABASE_REFERENCE_PLAYLIST.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                playlists = new ArrayList<>();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Log.i("NNN", String.valueOf(dataSnapshot.getValue()));
                    Playlist playlist = dataSnapshot.getValue(Playlist.class);
                    playlists.add(playlist);
                }
                adapter = new PlaylistAdapter(getActivity(), android.R.layout.simple_list_item_1, playlists);
                listView.setAdapter(adapter);
                setListViewHeightBasedOnChildren(listView);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return view;
    }

    public void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }

        int totalHeight = listView.getPaddingTop() + listView.getPaddingBottom();
        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.AT_MOST);
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);

            if (listItem != null) {
                // This next line is needed before you call measure or else you won't get measured height at all. The listitem needs to be drawn first to know the height.
                listItem.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT));
                listItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
                totalHeight += listItem.getMeasuredHeight();

            }
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
        listView.requestLayout();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(PlayListViewModel.class);
        // TODO: Use the ViewModel
    }

}