package com.example.demo_da4.Presenter.ThanhToan;


import android.content.Context;
import android.view.View;

import com.example.demo_da4.Model.GioHang.DataGH;
import com.example.demo_da4.Model.GioHang.ModelGioHang;
import com.example.demo_da4.Model.ObjectClass.DonHang;
import com.example.demo_da4.Model.ObjectClass.SanPham;
import com.example.demo_da4.Model.ThanhToan.Model_ThanhToan;
import com.example.demo_da4.View.ThanhToan.ViewThanhToan;

import java.util.ArrayList;
import java.util.List;

public class PresenterLogicThanhToan implements IPresenterLogicThanhToan {

    ViewThanhToan viewThanhToan;
    Model_ThanhToan model_thanhToan;
    ModelGioHang modelGioHang;

    public PresenterLogicThanhToan(ViewThanhToan viewThanhToan){
        this.viewThanhToan= viewThanhToan;
        model_thanhToan = new Model_ThanhToan();
        modelGioHang = new ModelGioHang();

    }
    @Override
    public void themhoadon(DonHang donHang) {
        boolean kiemtra = model_thanhToan.ThemHoaDon(donHang);
        if(kiemtra){
            viewThanhToan.DatHangThanhCong();
        }else {
            viewThanhToan.DatHangThatBai();
        }

    }

    @Override
    public void LayDanhSachSanPhamTrongGioHang(Context context) {
        modelGioHang.DatabaseHandler(context);
        List<SanPham>  sanPhamList = modelGioHang.LaySanPhamTrongGH();
        viewThanhToan.LayDanhSachSanPhamTrongGioHang(sanPhamList);
    }


}
