package com.example.app_music.ui.home.homecontent.theme;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.app_music.R;
import com.example.app_music.component.ListThemeMusicActivity;
import com.example.app_music.domain.ThemeSong;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ThemeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ThemeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ThemeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ThemeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ThemeFragment newInstance(String param1, String param2) {
        ThemeFragment fragment = new ThemeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    View view;
    private HorizontalScrollView horizontalScrollView;
    private TextView textView_view_more;
    private DatabaseReference dbReference;
    private List<ThemeSong> themeSongs;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.fragment_theme, container, false);
        dbReference = FirebaseDatabase.getInstance().getReference("theme");
        horizontalScrollView = view.findViewById(R.id.horizontal_scrollview);
        textView_view_more = view.findViewById(R.id.txt_view_more_theme);
        dbReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                themeSongs = new ArrayList<>();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    ThemeSong tm = dataSnapshot.getValue(ThemeSong.class);
                    Log.i("DCM", tm.toString());
                    themeSongs.add(tm);
                }
                LinearLayout.LayoutParams ln1 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                LinearLayout layout = new LinearLayout(getActivity());
                layout.setOrientation(LinearLayout.HORIZONTAL);
                LinearLayout.LayoutParams ln = new LinearLayout.LayoutParams(580, 250);
                ln.setMargins(10, 20, 10, 30);
                for (int i = 0; i < themeSongs.size(); i++) {
                    CardView cardView = new CardView(getActivity());
                    cardView.setRadius(10);
                    ImageView imageView = new ImageView(getActivity());
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                    if (!themeSongs.get(i).getImg_theme_URL().isEmpty()) {
                        Picasso.get().load(themeSongs.get(i).getImg_theme_URL()).fit().centerCrop().into(imageView);
                    }
                    cardView.setLayoutParams(ln);
                    cardView.addView(imageView);

                    TextView textView = new TextView(getActivity());

                    textView.setGravity(1);
                    textView.setLayoutParams(ln1);
                    textView.setTextColor(Color.rgb(255, 255, 255));
                    textView.setText(themeSongs.get(i).getTheme_name());
                    cardView.addView(textView);
                    cardView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                    layout.addView(cardView);

                }
                horizontalScrollView.addView(layout);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        eventSeeMore();
        return view;
    }

    private void eventSeeMore() {
        textView_view_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ListThemeMusicActivity.class);
                startActivity(intent);
            }
        });
    }
}