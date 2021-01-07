package com.example.cinema.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class RapHandler extends SQLiteOpenHelper {
    public static Context context;
    public  static final String DATABASE_NAME = "cinemaManagerRap";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "raps";

    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "TenRap";
    private static final String KEY_DIA_CHI = "DiaChi";
    private static final String KEY_SDT = "SDT";
    private static final String KEY_TRANG_THAI = "TrangThai";

    public RapHandler(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create_students_table = String.format("CREATE TABLE %s(%s INTEGER PRIMARY KEY,%s TEXT,%s TEXT  UNIQUE,%s TEXT,%s INTEGER )",
                TABLE_NAME, KEY_ID,KEY_NAME,KEY_DIA_CHI,KEY_SDT,KEY_TRANG_THAI);
        // Execute Script.
        db.execSQL(create_students_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String drop_students_table = String.format("DROP TABLE IF EXISTS %s", TABLE_NAME);
        db.execSQL(drop_students_table);
        onCreate(db);
    }
    public void addRapObj(Rap rap)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_ID,rap.id);
        values.put(KEY_NAME,rap.TenRap);
        values.put(KEY_DIA_CHI,rap.getAddress());
        values.put(KEY_SDT,rap.SDT);
        values.put(KEY_TRANG_THAI,1);
        //values.put(KEY_TRANG_THAI,1);
        long result=db.insert(TABLE_NAME,null,values);
        if(result==-1)
        {
            Toast.makeText(context, "Thất bại", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(context, "Thanh cong"+result, Toast.LENGTH_SHORT).show();
        }
        // Closing database connection
        db.close();
    }
    public void addRap(int id,String ten,String diachi,String sdt,int trangthai)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_ID,id);
        values.put(KEY_NAME,ten);
        values.put(KEY_DIA_CHI,diachi);
        values.put(KEY_SDT,sdt);
        values.put(KEY_TRANG_THAI,trangthai);
        //values.put(KEY_TRANG_THAI,1);
        long result=db.insert(TABLE_NAME,null,values);
        if(result==-1)
        {
            Toast.makeText(context, "Thất bại", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(context, "Thanh cong"+result, Toast.LENGTH_SHORT).show();
        }
        // Closing database connection
        db.close();
    }
    public Rap getStudent(int studentId) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME, null, KEY_ID + " = ?", new String[] { String.valueOf(studentId) },null, null, null);
        if(cursor != null)
            cursor.moveToFirst();
        Rap rap=new Rap();
        rap.setId(cursor.getInt(0));
        rap.setName(cursor.getString(1));
        rap.setAddress(cursor.getString(2));
        rap.setPhone(cursor.getString(3));
        return rap;
    }
}
