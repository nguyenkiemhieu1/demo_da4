package com.example.demo_da4.Presenter.ChiTietSanPham;

import android.content.Context;

import com.example.demo_da4.Model.ObjectClass.SanPham;

public interface IPresenterChiTietSanPham {
    void LayChiTietSanPham(int masp);
    void ThemGioHang(SanPham sanPham, Context context);
//    void DemSoLuongSanPhamTrongGioHang();
}
