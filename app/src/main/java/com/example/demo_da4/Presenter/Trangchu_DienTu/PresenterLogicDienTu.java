package com.example.demo_da4.Presenter.Trangchu_DienTu;

import com.example.demo_da4.Model.ObjectClass.DienTu;
import com.example.demo_da4.Model.ObjectClass.SanPham;
import com.example.demo_da4.Model.ObjectClass.ThuongHieu;
import com.example.demo_da4.Model.Trangchu_dientu.Model_dientu;
import com.example.demo_da4.View.TrangChu.ViewDienTu;

import java.util.ArrayList;
import java.util.List;

public class PresenterLogicDienTu implements  IPresenterDienTu {
    ViewDienTu viewDienTu;
    Model_dientu model_dientu;

    public PresenterLogicDienTu(ViewDienTu viewDienTu){
        this.viewDienTu = viewDienTu;
        model_dientu = new Model_dientu();
    }
    @Override
    public void LayDanhSachDienTu() {
        List<DienTu> dienTus = new ArrayList<>();

       List<ThuongHieu> thuongHieuList = model_dientu.LayDanhsachThuongHieuLon("LayDanhSachCacThuongHieu", "DANHSACHTHUONGHIEU");
       List<SanPham> sanPhamList=model_dientu.LayDanhSachSanPhamTop("LayDanhSachTopTivi", "TOPTIVI");

        DienTu dienTu = new DienTu();
        dienTu.setThuongHieus(thuongHieuList);
        dienTu.setSamPhams(sanPhamList);
        dienTu.setTennoibat("Danh sách thương hiệu");
        dienTu.setTentopnoibat("Top Tivi bán chạy");
        dienTu.setThuonghieu(true);
        dienTus.add(dienTu);

        List<ThuongHieu> phukienList = model_dientu.LayDanhsachThuongHieuLon("LayDanhSachPhuKien","DANHSACHPHUKIEN");
        List<SanPham> TopphukienList = model_dientu.LayDanhSachSanPhamTop("LayDanhSachTopPhuKien","TOPPHUKIEN");
        DienTu dienTu1 = new DienTu();
        dienTu1.setThuongHieus(phukienList);
        dienTu1.setSamPhams(TopphukienList);

//        xét tiêu đề
        dienTu1.setTennoibat("phụ kiện");
        dienTu1.setTentopnoibat("Top phụ kiện");
        dienTu1.setThuonghieu(false);
        dienTus.add(dienTu1);


        List<ThuongHieu> tienichList = model_dientu.LayDanhsachThuongHieuLon("LayDanhSachTienIch","DANHSACHTIENICH");
        List<SanPham> ToptienichList = model_dientu.LayDanhSachSanPhamTop("LayTopTienIch","TOPTIENICH");
        DienTu dienTu2 = new DienTu();
        dienTu2.setThuongHieus(tienichList);
        dienTu2.setSamPhams(ToptienichList);
        dienTu2.setTennoibat("Tiện ích");
        dienTu2.setTentopnoibat("Top tiện ích");
        dienTu2.setThuonghieu(false);
        dienTus.add(dienTu2);

        if(thuongHieuList.size() > 0 && sanPhamList.size() > 0){
           viewDienTu.HienThiDanhSach(dienTus);
       }
    }

    @Override
    public void layDanhSachLogothuongHieu() {
        List<ThuongHieu> thuongHieuList = model_dientu.LayThuongHieuLon("LayLogoThuongHieuLon","DANHSACHTHUONGHIEUDIENTU");
        if(thuongHieuList.size()>0){
            viewDienTu.HienThiLogoThuongHieu(thuongHieuList);
        }else {
            viewDienTu.LoiLayDuLieu();
        }
    }

    @Override
    public void LayDanhSachSanPhamMoiVe() {
        List<SanPham> sanPhamList = model_dientu.LayDanhSachSanPhamTop("LayDanhSachSanPhamMoiVe","DANHSACHSANPHAMMOIVE");

        if(sanPhamList.size()>0){
            viewDienTu.SanPhamMoiVe(sanPhamList);
        }else {
            viewDienTu.LoiLayDuLieu();
        }

    }
}
