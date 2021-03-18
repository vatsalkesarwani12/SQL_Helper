package com.algo.phantoms.sqlhelper.Features.CreateStudent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.algo.phantoms.sqlhelper.Database.DatabaseQueryClass;
import com.algo.phantoms.sqlhelper.Features.ShowStudentList.StudentListActivity;
import com.algo.phantoms.sqlhelper.R;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity  implements  StudentCreateListener{

    EditText name,dob;
    ImageView profilePicture;
    Button save;
    String n,d;

    private static final int SELECT_PHOTO = 100;
    private byte[] profile_picture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.name);
        dob = findViewById(R.id.dob);
        profilePicture = findViewById(R.id.profilePicture);

        save = findViewById(R.id.button);
        save.setEnabled(false);

        save.setOnClickListener(v -> {
            //assumed all data is not null
            fetchData();
        });

        findViewById(R.id.fabProfile).setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), StudentListActivity.class));
        });

        profilePicture.setOnClickListener(v -> fetchImage());

    }

    private void fetchData() {

        n= name.getText().toString();
        d= dob.getText().toString();

        sqlFunction();

    }

    private void fetchImage(){

        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, SELECT_PHOTO);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);

        if (requestCode == SELECT_PHOTO) {
            if (resultCode == RESULT_OK) {
                Uri selectedImage = imageReturnedIntent.getData();
                InputStream imageStream = null;
                try {
                    imageStream = getContentResolver().openInputStream(selectedImage);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                Bitmap yourSelectedImage = BitmapFactory.decodeStream(imageStream);

                if(yourSelectedImage.getByteCount() != 0){
                    save.setEnabled(true);
                }

                profilePicture.setImageBitmap(yourSelectedImage);

                insertImg(yourSelectedImage);
            }
        }
    }

    private void sqlFunction() {

        Profile profile = new Profile(-1, n, d, profile_picture);

        DatabaseQueryClass databaseQueryClass = new DatabaseQueryClass(this);

        long id = databaseQueryClass.insertStudent(profile);

        if(id>0){
            profile.setId(id);
            onStudentCreated(profile);

            Toast.makeText(this, "Data added", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onStudentCreated(Profile profile) {

    }

    public void insertImg(Bitmap img ) {

        profile_picture = getBitmapAsByteArray(img);
    }

    public static byte[] getBitmapAsByteArray(Bitmap bitmap) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, outputStream);
        return outputStream.toByteArray();
    }
}