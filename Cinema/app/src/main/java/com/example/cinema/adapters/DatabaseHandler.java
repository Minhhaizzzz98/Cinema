package com.example.cinema.adapters;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.cinema.models.Movie;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {
    public static Context context;
    public  static final String DATABASE_NAME = "movieManager";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "movie";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "title";
    //    private static final String KEY_ADDRESS = "address";
//    private static final String KEY_PHONE_NUMBER = "phone";
    private static final String KEY_IMAGE="hinhanh";
    SQLiteDatabase db1;

    public DatabaseHandler(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create_students_table = String.format("CREATE TABLE %s(%s INTEGER PRIMARY KEY, %s TEXT,%s BLOB)", TABLE_NAME, KEY_ID, KEY_NAME, KEY_IMAGE);
        // Execute Script.
        db.execSQL(create_students_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String drop_students_table = String.format("DROP TABLE IF EXISTS %s", TABLE_NAME);
        db.execSQL(drop_students_table);
        onCreate(db);
    }
    public void addMovie(String name,byte[] hinh)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, name);

        values.put(KEY_IMAGE,hinh);
        long result=db.insert(TABLE_NAME,null,values);
        if(result==-1)
        {
            Toast.makeText(context, "Thất bại", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(context, "Thanh cong", Toast.LENGTH_SHORT).show();
        }
        // Closing database connection
        db.close();
    }
    public int getNotesCount() {
        //Log.i(TAG, "MyDatabaseHelper.getNotesCount ... " );

        String countQuery = "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();

        cursor.close();

        // return count
        return count;
    }

    public List<Movie> getAllStudents() {
//    SQLiteDatabase sqLiteDatabase=getWritableDatabase();
//        String drop_students_table = String.format("DROP TABLE IF EXISTS %s", TABLE_NAME);
//        sqLiteDatabase.execSQL(drop_students_table);
//        String create_students_table = String.format("CREATE TABLE %s(%s INTEGER PRIMARY KEY, %s TEXT,%s BLOB)", TABLE_NAME, KEY_ID, KEY_NAME, KEY_IMAGE);
//        // Execute Script.
//        sqLiteDatabase.execSQL(create_students_table);

        List<Movie>  studentList = new ArrayList<>();
        String query = "SELECT *  from " + TABLE_NAME;


        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if(cursor!=null)
        {
            cursor.moveToFirst();
        }

        if(cursor!=null && cursor.getCount() > 0)
        {
            while(cursor.isAfterLast() == false) {



                Movie student = new Movie(cursor.getString(1), cursor.getBlob(2));
                studentList.add(student);
                cursor.moveToNext();
            }
        }

        return studentList;
    }
}
