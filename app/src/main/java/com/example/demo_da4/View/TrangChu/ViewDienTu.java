package com.example.demo_da4.View.TrangChu;

import com.example.demo_da4.Model.ObjectClass.DienTu;
import com.example.demo_da4.Model.ObjectClass.SanPham;
import com.example.demo_da4.Model.ObjectClass.ThuongHieu;

import java.util.List;

public interface ViewDienTu {

    void HienThiDanhSach(List<DienTu> dienTus);
    void HienThiLogoThuongHieu(List<ThuongHieu> thuongHieus);
    void LoiLayDuLieu();
    void SanPhamMoiVe(List<SanPham> sanPhamList);
}
