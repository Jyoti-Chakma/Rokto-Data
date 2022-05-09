package com.jyoti.unmesh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GroupList extends AppCompatActivity {

    Button aPositive, aNegative, bPositive, bNegative, abPositive, abNegative, oPositive, oNegative;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_list);

        aPositive = findViewById(R.id.aPositive);
        aNegative = findViewById(R.id.aNegative);
        bPositive = findViewById(R.id.bPositive);
        bNegative = findViewById(R.id.bNegative);
        abPositive = findViewById(R.id.abPositive);
        abNegative = findViewById(R.id.abNegative);
        oPositive = findViewById(R.id.oPositive);
        oNegative = findViewById(R.id.oNegative);


        aPositive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(GroupList.this,APositive.class);
                startActivity(i);
                finish();
            }
        });


        aNegative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(GroupList.this,ANegative.class);
                startActivity(i);
                finish();
            }
        });


        bPositive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(GroupList.this,BPositive.class);
                startActivity(i);
                finish();
            }
        });

        bNegative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(GroupList.this,BNegative.class);
                startActivity(i);
                finish();
            }
        });

        abPositive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(GroupList.this,ABPositive.class);
                startActivity(i);
                finish();
            }
        });


        abNegative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(GroupList.this,ABNegative.class);
                startActivity(i);
                finish();
            }
        });

        oPositive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(GroupList.this,OPositive.class);
                startActivity(i);
                finish();
            }
        });

        oNegative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(GroupList.this,ONegative.class);
                startActivity(i);
                finish();
            }
        });
    }
}