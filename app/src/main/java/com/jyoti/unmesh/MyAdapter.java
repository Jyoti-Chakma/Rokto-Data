package com.jyoti.unmesh;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class MyAdapter extends FirebaseRecyclerAdapter<model, MyAdapter.myViewHolder> {
    public MyAdapter(@NonNull FirebaseRecyclerOptions<model> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull final myViewHolder holder, @SuppressLint("RecyclerView") final int position, @NonNull final model model) {
        holder.name.setText(model.getName());
        holder.address.setText(model.getAddress());
        holder.donated.setText(model.getDonated());
        holder.age.setText(model.getAge());
        holder.blood.setText(model.getBlood());

        holder.call.setOnClickListener(v -> {
            String call = model.getPhone();
            String s = "tel:" + call;
            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse(s));
            v.getContext().startActivity(intent);

        });

        holder.edit.setOnClickListener(view -> {
            final DialogPlus dialogPlus = DialogPlus.newDialog(holder.name.getContext())
                    .setContentHolder(new ViewHolder(R.layout.dialogcontent))
                    .setExpanded(true, 1500)
                    .create();

            View myView = dialogPlus.getHolderView();
            final TextInputEditText name = myView.findViewById(R.id.nameEdit);
            final TextInputEditText address = myView.findViewById(R.id.addressEdit);
            final TextInputEditText donated = myView.findViewById(R.id.donatedEdit);
            final TextInputEditText age = myView.findViewById(R.id.ageEdit);
            final TextInputEditText blood = myView.findViewById(R.id.bloodEdit);
            final TextInputEditText phone = myView.findViewById(R.id.phoneEdit);
            Button submit = myView.findViewById(R.id.usubmit);

            name.setText(model.getName());
            address.setText(model.getAddress());
            donated.setText(model.getDonated());
            age.setText(model.getAge());
            blood.setText(model.getBlood());
            phone.setText(model.getPhone());

            dialogPlus.show();

            submit.setOnClickListener(view1 -> {
                Map<String, Object> map = new HashMap<>();
                map.put("name", Objects.requireNonNull(name.getText()).toString());
                map.put("address", Objects.requireNonNull(address.getText()).toString());
                map.put("donated", Objects.requireNonNull(donated.getText()).toString());
                map.put("age", Objects.requireNonNull(age.getText()).toString());
                map.put("blood", Objects.requireNonNull(blood.getText()).toString());
                map.put("phone", Objects.requireNonNull(phone.getText()).toString());

                FirebaseDatabase.getInstance().getReference().child("donors")
                        .child(Objects.requireNonNull(getRef(position).getKey())).updateChildren(map)
                        .addOnSuccessListener(aVoid -> dialogPlus.dismiss())
                        .addOnFailureListener(e -> dialogPlus.dismiss());
            });

        });

        holder.delete.setOnClickListener(view -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(holder.name.getContext());
            builder.setTitle("Delete Donors?");
            builder.setMessage("Are you sure?");

            builder.setPositiveButton("Yes", (dialogInterface, i) -> FirebaseDatabase.getInstance().getReference().child("donors")
                    .child(Objects.requireNonNull(getRef(position).getKey())).removeValue());

            builder.setNegativeButton("No", (dialogInterface, i) -> {

            });

            builder.show();
        });

    } // End of OnBindVi// ewMethod

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new myViewHolder(view);
    }

    static class myViewHolder extends RecyclerView.ViewHolder {

        final Button edit;
        final Button delete;
        final ImageButton call;
        TextView name;
        final TextView address;
        final TextView donated;
        final TextView age;
        TextView blood;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.nameText);
            address = itemView.findViewById(R.id.address);
            donated = itemView.findViewById(R.id.lastDonated);
            age = itemView.findViewById(R.id.tvage);
            blood = itemView.findViewById(R.id.bloodgroup);
            call = itemView.findViewById(R.id.phone);
            edit = itemView.findViewById(R.id.editButton);
            delete = itemView.findViewById(R.id.deleteButton);
        }
    }
}
