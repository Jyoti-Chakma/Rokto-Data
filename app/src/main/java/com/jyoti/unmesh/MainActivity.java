package com.jyoti.unmesh;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    Button recyclerViewBtn, frequency, groupList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Objects.requireNonNull(getSupportActionBar()).hide();

        recyclerViewBtn = findViewById(R.id.recyclerviewbtn);
        frequency = findViewById(R.id.frequency);
        groupList = findViewById(R.id.groupList);

        recyclerViewBtn.setOnClickListener(v -> {
            Intent i = new Intent(MainActivity.this, DonorList.class);
            startActivity(i);
            finish();
        });


        frequency.setOnClickListener(view -> {
            Intent i = new Intent(MainActivity.this, APositive.class);
            startActivity(i);
            finish();
        });


        groupList.setOnClickListener(view -> {
            Intent i = new Intent(MainActivity.this, GroupList.class);
            startActivity(i);
            finish();
        });


    }
}