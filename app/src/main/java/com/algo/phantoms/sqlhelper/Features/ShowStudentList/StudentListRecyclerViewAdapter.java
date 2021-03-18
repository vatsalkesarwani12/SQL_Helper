package com.algo.phantoms.sqlhelper.Features.ShowStudentList;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.algo.phantoms.sqlhelper.Database.DatabaseQueryClass;
import com.algo.phantoms.sqlhelper.Features.CreateStudent.Profile;
import com.algo.phantoms.sqlhelper.R;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import java.util.List;

public class StudentListRecyclerViewAdapter extends RecyclerView.Adapter<CustomViewHolder> {

    private Context context;
    private List<Profile>profileList;
    private DatabaseQueryClass databaseQueryClass;

    public StudentListRecyclerViewAdapter(Context context, List<Profile> profileList) {
        this.context = context;
        this.profileList = profileList;
        databaseQueryClass = new DatabaseQueryClass(context);
        Logger.addLogAdapter(new AndroidLogAdapter());
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.student_item, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        final int itemPosition = position;
        final Profile student = profileList.get(position);

        holder.nameTextView.setText(student.getName());
        holder.dobTextView.setText(String.valueOf(student.getDob()));
        Bitmap bmp= BitmapFactory.decodeByteArray(student.getProfile_picture(), 0 , student.getProfile_picture().length);
        holder.profileImageView.setImageBitmap(bmp);

    }

    @Override
    public int getItemCount() {
        return profileList.size();
    }
}
