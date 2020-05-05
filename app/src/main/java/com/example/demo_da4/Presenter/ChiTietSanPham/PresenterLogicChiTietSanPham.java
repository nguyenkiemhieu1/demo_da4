package com.example.demo_da4.Presenter.ChiTietSanPham;

import com.example.demo_da4.Model.ChiTietSanPham.ModelChiTietSanPham;
import com.example.demo_da4.Model.ObjectClass.SanPham;
import com.example.demo_da4.View.ChiTietSanPham.ChiTietSanPhamActivity;
import com.example.demo_da4.View.ChiTietSanPham.ViewChiTietSanPham;

public class PresenterLogicChiTietSanPham implements IPresenterChiTietSanPham {
    ViewChiTietSanPham viewChiTietSanPham;
    ModelChiTietSanPham modelChiTietSanPham;


    public PresenterLogicChiTietSanPham(ViewChiTietSanPham viewChiTietSanPham) {
        this.viewChiTietSanPham = viewChiTietSanPham;
       modelChiTietSanPham = new  ModelChiTietSanPham();
    }



    @Override
    public void LayChiTietSanPham(int masp) {
        SanPham sanPham = modelChiTietSanPham.layChiTietSanPham("LaySanPhamVaChitietTheoMaSanPham" ,"CHITIETSANPHAM",masp);
        if(sanPham.getMASP() > 0) {
            viewChiTietSanPham.HienThiChiTietSanPham(sanPham);

        }
    }

}
