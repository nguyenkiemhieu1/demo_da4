package com.example.demo_da4.Model.ObjectClass;

import java.util.List;

public class LoaiSanPham {
    int MALOAISP, MALOAICHA;
    String TENLOAISP;
    List<LoaiSanPham> listcon;

    public int getMALOAISP() {
        return MALOAISP;
    }

    public void setMALOAISP(int MALOAISP) {
        this.MALOAISP = MALOAISP;
    }

    public int getMALOAICHA() {
        return MALOAICHA;
    }

    public void setMALOAICHA(int MALOAICHA) {
        this.MALOAICHA = MALOAICHA;
    }

    public String getTENLOAISP() {
        return TENLOAISP;
    }

    public void setTENLOAISP(String TENLOAISP) {
        this.TENLOAISP = TENLOAISP;
    }

    public List<LoaiSanPham> getListcon() {
        return listcon;
    }

    public void setListcon(List<LoaiSanPham> listcon) {
        this.listcon = listcon;
    }
}