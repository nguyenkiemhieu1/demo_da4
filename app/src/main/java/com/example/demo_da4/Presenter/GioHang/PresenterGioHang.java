package com.example.demo_da4.Presenter.GioHang;

import android.content.Context;

import com.example.demo_da4.Model.GioHang.ModelGioHang;
import com.example.demo_da4.Model.ObjectClass.SanPham;
import com.example.demo_da4.View.GioHang.ViewGioHang;

import java.util.List;

public class PresenterGioHang implements iPresenterGioHang {
    ModelGioHang modelGioHang;
    ViewGioHang viewGioHang;
    public PresenterGioHang (ViewGioHang viewGioHang){
        modelGioHang = new ModelGioHang();
        this.viewGioHang = viewGioHang;
    }
    @Override
    public void LayDanhSachSanPhamTrongGioHang(Context context) {
        modelGioHang.DatabaseHandler(context);
        List<SanPham> sanPhamList = modelGioHang.LaySanPhamTrongGH();

        if(sanPhamList.size() > 0){
            viewGioHang.HienThiDanhSachSanPhamTrongGIoHang(sanPhamList);
        }
    }
}
