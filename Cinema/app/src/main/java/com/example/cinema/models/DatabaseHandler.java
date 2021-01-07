package com.example.cinema.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DatabaseHandler extends SQLiteOpenHelper {
    public static Context context;
    public  static final String DATABASE_NAME = "cinemaManager";
    public static final int DATABASE_VERSION = 3;
    public static final String TABLE_NAME = "ves";
    SQLiteDatabase sql;
    private static final String KEY_ID = "id";
    private static final String KEY_SUAT_CHIEU_ID = "suatchieu_id";
    private static final String KEY_GHE_ID = "ghe_id";
    private static final String KEY_KHACH_HANG_ID = "kh_id";
    private static final String KEY_GIA_VE = "GiaVe";
    private static final String KEY_TRANG_THAI = "TrangThai";
    // Rap
    public static final String TABLE_NAME_RAP = "raps";

    private static final String KEY_RAP_ID = "id";
    private static final String KEY_RAP_NAME = "TenRap";
    private static final String KEY_RAP_DIA_CHI = "DiaChi";
    private static final String KEY_RAP_SDT = "SDT";
    private static final String KEY_RAP_TRANG_THAI = "TrangThai";
    public DatabaseHandler(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        context.deleteDatabase(DATABASE_NAME);
        this.context=context;

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create_students_table = String.format("CREATE TABLE %s(%s INTEGER PRIMARY KEY,%s INTEGER,%s INTEGER,%s INTEGER,%s REAL,%s INTEGER )",
                TABLE_NAME, KEY_ID,KEY_SUAT_CHIEU_ID,KEY_GHE_ID,KEY_KHACH_HANG_ID,KEY_GIA_VE,KEY_TRANG_THAI);
        // Execute Script.
        db.execSQL(create_students_table);
        String create_students_table1 = String.format("CREATE TABLE %s(%s INTEGER PRIMARY KEY,%s TEXT UNIQUE,%s TEXT,%s TEXT,%s INTEGER)",
                TABLE_NAME_RAP, KEY_RAP_ID,KEY_RAP_NAME,KEY_RAP_DIA_CHI,KEY_RAP_SDT,KEY_RAP_TRANG_THAI);
        // Execute Script.
        db.execSQL(create_students_table1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String drop_students_table = String.format("DROP TABLE IF EXISTS %s", TABLE_NAME);
        db.execSQL(drop_students_table);
        onCreate(db);
        drop_students_table = String.format("DROP TABLE IF EXISTS %s", TABLE_NAME_RAP);
        db.execSQL(drop_students_table);
        onCreate(db);
    }
    public long addVeValue(int id_suatchieu,int id_ghe,int id_kh,double gia,int trangthai)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_SUAT_CHIEU_ID,id_suatchieu);
        values.put(KEY_GHE_ID,id_ghe);
        values.put(KEY_KHACH_HANG_ID,id_kh);
        values.put(KEY_GIA_VE,gia);
        values.put(KEY_TRANG_THAI,1);
        long result=db.insert(TABLE_NAME,null,values);

        // Closing database connection
        db.close();
        return  result;
    }
    public void addVe(int id_suatchieu,int id_ghe,int id_kh,double gia,int trangthai)
    {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_SUAT_CHIEU_ID,id_suatchieu);
        values.put(KEY_GHE_ID,id_ghe);
        values.put(KEY_KHACH_HANG_ID,id_kh);
        values.put(KEY_GIA_VE,gia);
        values.put(KEY_TRANG_THAI,1);
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
    public void updateVe(Ve ve) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_SUAT_CHIEU_ID,ve.getSuat_chieu_id());
        values.put(KEY_GHE_ID,ve.getGhe_id());
        values.put(KEY_KHACH_HANG_ID,ve.getKhach_hang_id());
        values.put(KEY_GIA_VE,ve.getGia_ve());
        values.put(KEY_TRANG_THAI,1);
//        values.put(KEY_NAME, student.getName());
//        values.put(KEY_ADDRESS, student.getAddress());
//        values.put(KEY_PHONE_NUMBER, student.getPhone_number());

        db.update(TABLE_NAME, values, KEY_ID + " = ?", new String[] { String.valueOf(ve.getId()) });
        db.close();
    }
    public Ve getStudent(int studentId) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME, null, KEY_ID + " = ?", new String[] { String.valueOf(studentId) },null, null, null);
        if(cursor != null)
            cursor.moveToFirst();
        Ve student = new Ve(cursor.getInt(0),
                cursor.getInt(1)
                ,cursor.getInt(2),cursor.getInt(3),cursor.getDouble(4),cursor.getInt(5));
        return student;
    }
    //rap

    public void addRapObj(Rap rap)
    {


        // String create_students_table = String.format("CREATE TABLE %s(%s INTEGER PRIMARY KEY,%s TEXT UNIQUE,%s TEXT,%s TEXT,%s INTEGER)",
        // TABLE_NAME_RAP, KEY_RAP_ID,KEY_RAP_NAME,KEY_RAP_DIA_CHI,KEY_RAP_SDT,KEY_RAP_TRANG_THAI);
        // Execute Script.
        // sql.execSQL(create_students_table);
        SQLiteDatabase db = this.getWritableDatabase();
        //db.execSQL(create_students_table);
        ContentValues values = new ContentValues();
        values.put(KEY_RAP_ID,rap.id);
        values.put(KEY_RAP_NAME,rap.TenRap);
        values.put(KEY_RAP_DIA_CHI,rap.getAddress());
        values.put(KEY_RAP_SDT,rap.SDT);
        values.put(KEY_RAP_TRANG_THAI,1);
        //values.put(KEY_RAP_TRANG_THAI,1);
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
        values.put(KEY_RAP_ID,id);
        values.put(KEY_RAP_NAME,ten);
        values.put(KEY_RAP_DIA_CHI,diachi);
        values.put(KEY_RAP_SDT,sdt);
        values.put(KEY_RAP_TRANG_THAI,trangthai);
        //values.put(KEY_RAP_TRANG_THAI,1);
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
    public Rap getRap(int studentId) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, null, KEY_RAP_ID + " = ?", new String[] { String.valueOf(studentId) },null, null, null);
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
