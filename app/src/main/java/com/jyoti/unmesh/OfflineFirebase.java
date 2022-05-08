package com.jyoti.unmesh;

import android.app.Application;

import com.google.firebase.database.FirebaseDatabase;

public class OfflineFirebase extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        //Extra COde
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }
}
