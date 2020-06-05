package com.example.demo_da4.Presenter.TimKiem;

import com.example.demo_da4.Model.ObjectClass.SanPham;
import com.example.demo_da4.Model.TimKiem.Model_TimKiem;
import com.example.demo_da4.View.TimKiem.ViewTimKiem;

import java.util.ArrayList;
import java.util.List;

public class PresenterLogicTimKiem implements IPresenterTimKiem {
    ViewTimKiem viewTimKiem;
    Model_TimKiem model_timKiem;

    public PresenterLogicTimKiem(ViewTimKiem viewTimKiem){
        this.viewTimKiem = viewTimKiem;
        model_timKiem = new Model_TimKiem();
    }
    @Override
    public void TimKiemSanPhamTheoTenSanPham(String tensp, int limit){
        List<SanPham> sanPhamList = model_timKiem.LayDanhSachSanPhamTheoMaLoai(tensp, "DANHSACHSANPHAM", "timkiemSanPhamTheoTenSanPham", limit);
        if(sanPhamList.size()>0){
            viewTimKiem.ThanhCong(sanPhamList);
        }else {
            viewTimKiem.Thatbai();
        }
    }
}
