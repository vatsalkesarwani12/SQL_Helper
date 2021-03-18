package com.algo.phantoms.sqlhelper.Features.ShowStudentList;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.algo.phantoms.sqlhelper.R;


public class CustomViewHolder extends RecyclerView.ViewHolder {

    TextView nameTextView;
    TextView dobTextView;
    ImageView profileImageView;

    public CustomViewHolder(View itemView) {
        super(itemView);

        nameTextView = itemView.findViewById(R.id.nameTextView);
        dobTextView = itemView.findViewById(R.id.dobTextView);
        profileImageView = itemView.findViewById(R.id.profileImageView);
    }
}
