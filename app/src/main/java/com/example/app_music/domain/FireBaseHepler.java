package com.example.app_music.domain;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public interface FireBaseHepler {
    DatabaseReference DATABASE_REFERENCE = FirebaseDatabase.getInstance().getReference("music");
}
