package com.algo.phantoms.sqlhelper.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.widget.Toast;

import com.algo.phantoms.sqlhelper.Util.Config;
import com.algo.phantoms.sqlhelper.Features.CreateStudent.Profile;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DatabaseQueryClass {

    private Context context;

    public DatabaseQueryClass(Context context){
        this.context = context;
        Logger.addLogAdapter(new AndroidLogAdapter());
    }

    public long insertStudent(Profile profile){

        long id = -1;
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(Config.COLUMN_STUDENT_NAME, profile.getName());
        contentValues.put(Config.COLUMN_STUDENT_DOB, profile.getDob());
        contentValues.put(Config.COLUMN_STUDENT_PROFILE_PICTURE, profile.getProfile_picture());

        try {
            id = sqLiteDatabase.insertOrThrow(Config.TABLE_STUDENT, null, contentValues);
        } catch (SQLiteException e){
            Logger.d("Exception: " + e.getMessage());
            Toast.makeText(context, "Operation failed: " + e.getMessage(), Toast.LENGTH_LONG).show();
        } finally {
            sqLiteDatabase.close();
        }

        return id;
    }

    public List<Profile> getAllStudent(){

        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getReadableDatabase();

        Cursor cursor = null;
        try {

            cursor = sqLiteDatabase.query(Config.TABLE_STUDENT, null, null, null, null, null, null, null);

            if(cursor!=null)
                if(cursor.moveToFirst()){
                    List<Profile> profileList = new ArrayList<>();
                    do {
                        int id = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_STUDENT_ID));
                        String name = cursor.getString(cursor.getColumnIndex(Config.COLUMN_STUDENT_NAME));
                        String dob = cursor.getString(cursor.getColumnIndex(Config.COLUMN_STUDENT_DOB));
                        byte[] profile_image = cursor.getBlob(cursor.getColumnIndex(Config.COLUMN_STUDENT_PROFILE_PICTURE));

                        profileList.add(new Profile(id, name, dob, /*note*/ profile_image));
                    }   while (cursor.moveToNext());

                    return profileList;
                }
        } catch (Exception e){
            Logger.d("Exception: " + e.getMessage());
            Toast.makeText(context, "Operation failed", Toast.LENGTH_SHORT).show();
        } finally {
            if(cursor!=null)
                cursor.close();
            sqLiteDatabase.close();
        }

        return Collections.emptyList();
    }

    /*public Profile getStudentByName(String name){

        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getReadableDatabase();

        Cursor cursor = null;
        Profile profile = null;
        try {

            cursor = sqLiteDatabase.query(Config.TABLE_STUDENT, null,
                    Config.COLUMN_STUDENT_NAME + " = ? ", new String[]{name},
                    null, null, null);

            if(cursor.moveToFirst()){
                int id = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_STUDENT_ID));
                String name1 = cursor.getString(cursor.getColumnIndex(Config.COLUMN_STUDENT_NAME));
                String dob = cursor.getString(cursor.getColumnIndex(Config.COLUMN_STUDENT_DOB));
                byte[] profile_image = cursor.getBlob(cursor.getColumnIndex(Config.COLUMN_STUDENT_PROFILE_PICTURE));

                profile = new Profile(id, name, dob, profile_image);
            }
        } catch (Exception e){
            Logger.d("Exception: " + e.getMessage());
            Toast.makeText(context, "Operation failed", Toast.LENGTH_SHORT).show();
        } finally {
            if(cursor!=null)
                cursor.close();
            sqLiteDatabase.close();
        }

        return profile;
    }*/

    /*public long updateStudentInfo(Profile profile){

        long rowCount = 0;
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(Config.COLUMN_STUDENT_NAME, profile.getName());
        contentValues.put(Config.COLUMN_STUDENT_DOB, profile.getDob());
        contentValues.put(Config.COLUMN_STUDENT_PROFILE_PICTURE, profile.getProfile_picture());

        try {
            rowCount = sqLiteDatabase.update(Config.TABLE_STUDENT, contentValues,
                    Config.COLUMN_STUDENT_ID + " = ? ",
                    new String[] {String.valueOf(profile.getId())});
        } catch (SQLiteException e){
            Logger.d("Exception: " + e.getMessage());
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
        } finally {
            sqLiteDatabase.close();
        }

        return rowCount;
    }*/

    /*public long deleteStudentByName(String name) {
        long deletedRowCount = -1;
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        try {
            deletedRowCount = sqLiteDatabase.delete(Config.TABLE_STUDENT,
                    Config.COLUMN_STUDENT_NAME + " = ? ",
                    new String[]{name});
        } catch (SQLiteException e){
            Logger.d("Exception: " + e.getMessage());
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
        } finally {
            sqLiteDatabase.close();
        }

        return deletedRowCount;
    }*/

   /* public boolean deleteAllStudents(){
        boolean deleteStatus = false;
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        try {
            sqLiteDatabase.delete(Config.TABLE_STUDENT, null, null);

            long count = DatabaseUtils.queryNumEntries(sqLiteDatabase, Config.TABLE_STUDENT);

            if(count==0)
                deleteStatus = true;

        } catch (SQLiteException e){
            Logger.d("Exception: " + e.getMessage());
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
        } finally {
            sqLiteDatabase.close();
        }

        return deleteStatus;
    }*/

}