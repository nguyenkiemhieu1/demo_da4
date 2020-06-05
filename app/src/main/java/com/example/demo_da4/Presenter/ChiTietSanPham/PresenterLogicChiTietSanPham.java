package com.example.demo_da4.Presenter.ChiTietSanPham;

import android.content.Context;

import com.example.demo_da4.Model.ChiTietSanPham.ModelChiTietSanPham;
import com.example.demo_da4.Model.GioHang.ModelGioHang;
import com.example.demo_da4.Model.ObjectClass.SanPham;
import com.example.demo_da4.View.ChiTietSanPham.ChiTietSanPhamActivity;
import com.example.demo_da4.View.ChiTietSanPham.ViewChiTietSanPham;

import java.util.List;

public class PresenterLogicChiTietSanPham implements IPresenterChiTietSanPham {
    ViewChiTietSanPham viewChiTietSanPham;
    ModelChiTietSanPham modelChiTietSanPham;
    ModelGioHang modelGioHang;
    public PresenterLogicChiTietSanPham(){
        modelGioHang = new ModelGioHang();
    }


    public PresenterLogicChiTietSanPham(ViewChiTietSanPham viewChiTietSanPham) {
        this.viewChiTietSanPham = viewChiTietSanPham;
       modelChiTietSanPham = new  ModelChiTietSanPham();
       modelGioHang = new ModelGioHang();
    }



    @Override
    public void LayChiTietSanPham(int masp) {
        SanPham sanPham = modelChiTietSanPham.layChiTietSanPham("LaySanPhamVaChitietTheoMaSanPham" ,"CHITIETSANPHAM",masp);
        if(sanPham.getMASP() > 0) {
            viewChiTietSanPham.HienThiChiTietSanPham(sanPham);

        }
    }

    @Override
    public void ThemGioHang(SanPham sanPham, Context context) {
        modelGioHang.DatabaseHandler(context);

        boolean kiemtra = modelGioHang.ThemGioHang(sanPham);
        if(kiemtra){
            viewChiTietSanPham.ThemGioHangThanhCong();
        }else {
            viewChiTietSanPham.ThemGioHangThatBai();

        }
    }

    public int DemSanPhamTrongGioHang(Context context){
        modelGioHang.DatabaseHandler(context);
        List<SanPham> sanPhamList = modelGioHang.LaySanPhamTrongGH();

        int dem = sanPhamList.size();

        return dem;

    }

}
