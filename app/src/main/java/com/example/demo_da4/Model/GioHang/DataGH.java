package com.example.demo_da4.Model.GioHang;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;


public class DataGH extends SQLiteOpenHelper {
    public  static String TB_GIOHANG = "GIOHANG" ;
    public  static String TB_MASP= "MASP" ;
    public  static String TB_TENSP = "TENSP" ;
    public  static String TB_GIATIEN = "GIATIEN" ;
    public  static String TB_HINHANH = "HINHANH" ;
    public  static String TB_SOLUONG = "SOLUONG" ;
    public  static String TB_SOLUONGTON = "SOLUONGTON" ;

    public  static String TB_YEUTHICH = "YEUTHICH" ;
    public  static String TB_YEUTHICH_MASP= "MASP" ;
    public  static String TB_YEUTHICH_TENSP = "TENSP" ;
    public  static String TB_YEUTHICH_GIATIEN = "GIATIEN" ;
    public  static String TB_YEUTHICH_HINHANH = "HINHANH" ;

     public DataGH(Context context) {
        super(context, "QUANLYSP", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

         String tbgiohang = "CREATE TABLE "+ TB_GIOHANG +"("+ TB_MASP +" INTERGER PRIMARY KEY, "+ TB_TENSP +" TEXT, "+ TB_GIATIEN +" REAL, "+ TB_HINHANH +" BLOB, " + TB_SOLUONG + " INTERGER, " + TB_SOLUONGTON + " INTERGER);";
         String tbYEUTHICH = "CREATE TABLE "+ TB_YEUTHICH +"("+ TB_YEUTHICH_MASP +" INTERGER PRIMARY KEY, "+ TB_YEUTHICH_TENSP +" TEXT, "+ TB_YEUTHICH_GIATIEN +" REAL, "+ TB_YEUTHICH_HINHANH +" BLOB);";

         sqLiteDatabase.execSQL(tbgiohang);
         sqLiteDatabase.execSQL(tbYEUTHICH);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
