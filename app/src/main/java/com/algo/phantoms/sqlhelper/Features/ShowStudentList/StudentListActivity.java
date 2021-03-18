package com.algo.phantoms.sqlhelper.Features.ShowStudentList;

import android.content.Intent;
import android.os.Bundle;

import com.algo.phantoms.sqlhelper.Database.DatabaseQueryClass;
import com.algo.phantoms.sqlhelper.Features.CreateStudent.MainActivity;
import com.algo.phantoms.sqlhelper.Features.CreateStudent.Profile;
import com.algo.phantoms.sqlhelper.Features.CreateStudent.StudentCreateListener;
import com.algo.phantoms.sqlhelper.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.TextView;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

public class StudentListActivity extends AppCompatActivity implements StudentCreateListener {

    private DatabaseQueryClass databaseQueryClass = new DatabaseQueryClass(this);

    private List<Profile> profileList = new ArrayList<>();

    private TextView studentListEmptyTextView;
    private RecyclerView recyclerView;
    private StudentListRecyclerViewAdapter studentListRecyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list);
        Logger.addLogAdapter(new AndroidLogAdapter());

        recyclerView = findViewById(R.id.studentRecyclerView);
        studentListEmptyTextView = findViewById(R.id.emptyStudentListTextView);

        profileList.addAll(databaseQueryClass.getAllStudent());

        studentListRecyclerViewAdapter = new StudentListRecyclerViewAdapter(this, profileList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(studentListRecyclerViewAdapter);

        viewVisibility();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> addProfile());
    }

    public void viewVisibility() {
        if(profileList.isEmpty())
            studentListEmptyTextView.setVisibility(View.VISIBLE);
        else
            studentListEmptyTextView.setVisibility(View.GONE);
    }

    private void addProfile() {
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }

    @Override
    public void onStudentCreated(Profile profile) {
        profileList.add(profile);
        viewVisibility();
        Logger.d(profile.getName());
    }

}
