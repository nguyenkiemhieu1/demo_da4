package com.example.demo_da4.Presenter.DangKi;

import com.example.demo_da4.Model.DangNhap_DangKi.ModelDangKi;
import com.example.demo_da4.Model.ObjectClass.NhanVien;
import com.example.demo_da4.View.DangNhap_DangKi.ViewDangKi;

public class PresenterLogicDangKi implements IPresenterDangKi {
    ViewDangKi viewDangKi;
    ModelDangKi modelDangKi;

    public PresenterLogicDangKi(ViewDangKi viewDangKi) {
        this.viewDangKi = viewDangKi;
        modelDangKi = new ModelDangKi();
    }

    @Override
    public void ThucHienDangKi(NhanVien nhanVien) {
      boolean kiemtra = modelDangKi.DangKiThanhVien(nhanVien);
      if(kiemtra){
          viewDangKi.DangKiThanhCong();
      }else {
          viewDangKi.DangKiThatBai();
      }

    }
}
