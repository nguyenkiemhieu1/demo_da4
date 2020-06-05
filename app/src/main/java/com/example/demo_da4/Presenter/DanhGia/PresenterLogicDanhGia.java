package com.example.demo_da4.Presenter.DanhGia;

import com.example.demo_da4.Model.DanhGia.ModelDanhGia;
import com.example.demo_da4.Model.ObjectClass.DanhGia;
import com.example.demo_da4.View.DanhGia.ViewDanhGia;

public class PresenterLogicDanhGia implements IPresenterDanhGia{
    ViewDanhGia viewDanhGia;
    ModelDanhGia modelDanhGia;
    public  PresenterLogicDanhGia(ViewDanhGia viewDanhGia){
        this.viewDanhGia = viewDanhGia;
        modelDanhGia = new ModelDanhGia();
    }
    @Override
    public void ThemDanhGia(DanhGia danhGia) {
        boolean kiemtra = modelDanhGia.ThemDanhGia(danhGia);
        if(true){
            viewDanhGia.DanhGiaThanhCong();
        }else {
            viewDanhGia.DanhGiaThatBai();
        }

    }
}
