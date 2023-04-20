package com.jyoti.unmesh;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class AddDonor extends AppCompatActivity
{
    EditText name,address,donated,age,blood,phone;
    Button submit,back;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_donor);

        name=(TextInputEditText)findViewById(R.id.addName);
        address=(TextInputEditText)findViewById(R.id.addAddress);
        donated=(TextInputEditText)findViewById(R.id.addDonated);
        age=(TextInputEditText)findViewById(R.id.addAge);
        blood=(TextInputEditText)findViewById(R.id.addBlood);
        phone=(TextInputEditText)findViewById(R.id.addPhone);

        back=(Button)findViewById(R.id.add_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), DonorList.class);
                startActivity(intent);
            }
        });

        submit=(Button)findViewById(R.id.add_submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                processinsert();
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, DonorList.class);
        startActivity(intent);
    }

    private void processinsert()
    {
        Map<String,Object> map=new HashMap<>();
        map.put("name",name.getText().toString());
        map.put("address",address.getText().toString());
        map.put("donated",donated.getText().toString());
        map.put("age",age.getText().toString());
        map.put("blood",blood.getText().toString());
        map.put("phone",phone.getText().toString());
        FirebaseDatabase.getInstance().getReference().child("donors").push()
                .setValue(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        name.setText("");
                        address.setText("");
                        donated.setText("");
                        age.setText("");
                        blood.setText("");
                        phone.setText("");
                        Toast.makeText(getApplicationContext(),"Added Successfully",Toast.LENGTH_LONG).show();
                        startActivity(new Intent(getApplicationContext(), DonorList.class));
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e)
                    {
                        Toast.makeText(getApplicationContext(),"Could not insert",Toast.LENGTH_LONG).show();
                    }
                });

    }
}