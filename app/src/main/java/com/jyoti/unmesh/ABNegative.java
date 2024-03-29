package com.jyoti.unmesh;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class ABNegative extends AppCompatActivity {

    RecyclerView abNegativeView;
    MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abnegative);
        setTitle("AB- Blood Donors");

        abNegativeView = findViewById(R.id.abNegativeView);
        abNegativeView.setLayoutManager(new LinearLayoutManager(this));

        // Filtering AB- Blood Group from the List
        FirebaseRecyclerOptions<model> options =
                new FirebaseRecyclerOptions.Builder<model>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("donors").orderByChild("blood").equalTo("AB-"), model.class)
                        .build();

        adapter = new MyAdapter(options);
        abNegativeView.setAdapter(adapter);

    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onStart() {
        super.onStart();
        abNegativeView.getRecycledViewPool().clear();
        adapter.startListening();
        abNegativeView.getRecycledViewPool().clear();
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, GroupList.class);
        startActivity(intent);
    }
}