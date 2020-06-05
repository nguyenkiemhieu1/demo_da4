package com.example.demo_da4.Model.GioHang;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.demo_da4.Model.ObjectClass.SanPham;

import java.util.ArrayList;
import java.util.List;

public class ModelGioHang {
    SQLiteDatabase database;

    public  void DatabaseHandler(Context context){
        DataGH dataGH = new DataGH(context);
        database = dataGH.getWritableDatabase();


    }

    public boolean CapNhatSoLuongGioHang(int masp, int soluong){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DataGH.TB_SOLUONG, soluong);

        int id = database.update(DataGH.TB_GIOHANG, contentValues, DataGH.TB_MASP + " = " + masp, null);

        if(id > 0){
            return true;

        }else {
            return false;
        }

    }
    public  boolean ThemGioHang(SanPham sanPham){
        ContentValues contentValues = new ContentValues();

        contentValues.put(DataGH.TB_MASP,sanPham.getMASP());
        contentValues.put(DataGH.TB_TENSP, sanPham.getTENSANPHAM());
        contentValues.put(DataGH.TB_GIATIEN,sanPham.getGIA());
        contentValues.put(DataGH.TB_HINHANH, sanPham.getHinhgiohang());
        contentValues.put(DataGH.TB_SOLUONG, sanPham.getSOLUONG());
        contentValues.put(DataGH.TB_SOLUONGTON, sanPham.getSoluongtonkho());


        long id = database.insert(DataGH.TB_GIOHANG, null, contentValues);
        if(id > 0){
            return  true;
        }else {
            return  false;
        }

    }

    public boolean XoaSanphamTrongGioHang(int masp){

       int kiemtra = database.delete(DataGH.TB_GIOHANG, DataGH.TB_MASP + "=" +masp, null);
        if(kiemtra > 0){
            return true;
        }else {
            return false;
        }

    }
    public List<SanPham>  LaySanPhamTrongGH(){
        List<SanPham> list = new ArrayList<>();
        String truyvan = "SELECT * FROM " + DataGH.TB_GIOHANG;
        Cursor cursor = database.rawQuery(truyvan, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            int masp = cursor.getInt(cursor.getColumnIndex(DataGH.TB_MASP));
            String tensp = cursor.getString(cursor.getColumnIndex(DataGH.TB_TENSP));
            int giatien = cursor.getInt(cursor.getColumnIndex(DataGH.TB_GIATIEN));
            byte[] hinhanh = cursor.getBlob(cursor.getColumnIndex(DataGH.TB_HINHANH));
            int soluong = cursor.getInt(cursor.getColumnIndex(DataGH.TB_SOLUONG));
            int soluongton = cursor.getInt(cursor.getColumnIndex(DataGH.TB_SOLUONGTON));


            SanPham sanPham = new SanPham();
            sanPham.setMASP(masp);
            sanPham.setTENSANPHAM(tensp);
            sanPham.setGIA(giatien);
            sanPham.setHinhgiohang(hinhanh);
            sanPham.setSOLUONG(soluong);
            sanPham.setSoluongtonkho(soluongton);


            list.add(sanPham);
            cursor.moveToNext();

        }
        return list;
    }
}
