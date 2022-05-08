package com.jyoti.unmesh;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.HashMap;
import java.util.Map;

public class MyAdapter extends FirebaseRecyclerAdapter<model,MyAdapter.myviewholder> {
    public MyAdapter(@NonNull FirebaseRecyclerOptions<model> options)
    {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull final myviewholder holder, @SuppressLint("RecyclerView") final int position, @NonNull final model model)
    {
        holder.name.setText(model.getName());
        holder.address.setText(model.getAddress());
        holder.donated.setText(model.getDonated());
        holder.age.setText(model.getAge());
        holder.blood.setText(model.getBlood());


        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DialogPlus dialogPlus=DialogPlus.newDialog(holder.name.getContext())
                        .setContentHolder(new ViewHolder(R.layout.dialogcontent))
                        .setExpanded(true,1500)
                        .create();

                View myview=dialogPlus.getHolderView();
                final EditText name=myview.findViewById(R.id.nameEdit);
                final EditText address=myview.findViewById(R.id.addressEdit);
                final EditText donated=myview.findViewById(R.id.donatedEdit);
                final EditText age=myview.findViewById(R.id.ageEdit);
                final EditText blood=myview.findViewById(R.id.bloodEdit);
                final EditText phone=myview.findViewById(R.id.phoneEdit);
                Button submit=myview.findViewById(R.id.usubmit);


                name.setText(model.getName());
                address.setText(model.getAddress());
                donated.setText(model.getDonated());
                age.setText(model.getAge());
                blood.setText(model.getBlood());
                phone.setText(model.getPhone());

                dialogPlus.show();

                submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Map<String,Object> map=new HashMap<>();
                        map.put("name",name.getText().toString());
                        map.put("address", address.getText().toString());
                        map.put("donated",donated.getText().toString());
                        map.put("age",age.getText().toString());
                        map.put("blood",blood.getText().toString());
                        map.put("phone",phone.getText().toString());

                        FirebaseDatabase.getInstance().getReference().child("donors")
                                .child(getRef(position).getKey()).updateChildren(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        dialogPlus.dismiss();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        dialogPlus.dismiss();
                                    }
                                });
                    }
                });


            }
        });


        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(holder.name.getContext());
                builder.setTitle("Delete Panel");
                builder.setMessage("Delete...?");

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        FirebaseDatabase.getInstance().getReference().child("donors")
                                .child(getRef(position).getKey()).removeValue();
                    }
                });

                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

                builder.show();
            }
        });

    } // End of OnBindViewMethod

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
        return new myviewholder(view);
    }


    class myviewholder extends RecyclerView.ViewHolder
    {

        Button edit,delete;
        ImageButton phone;
        TextView name,address,donated,age,blood;
        public myviewholder(@NonNull View itemView)
        {
            super(itemView);
            name=(TextView)itemView.findViewById(R.id.nameText);
            address=(TextView)itemView.findViewById(R.id.address);
            donated=(TextView)itemView.findViewById(R.id.lastDonated);
            age=(TextView)itemView.findViewById(R.id.tvage);
            blood=(TextView)itemView.findViewById(R.id.bloodgroup);
            phone=(ImageButton) itemView.findViewById(R.id.phone);

            edit=(Button)itemView.findViewById(R.id.editButton);
            delete=(Button)itemView.findViewById(R.id.deleteButton);
        }
    }
}
