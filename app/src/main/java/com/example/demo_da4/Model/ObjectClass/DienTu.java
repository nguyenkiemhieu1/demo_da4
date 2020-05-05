package com.example.demo_da4.Model.ObjectClass;

import java.util.List;

public class DienTu {

    List<ThuongHieu> thuongHieus;
    List<SanPham> samPhams;
    String tennoibat, tentopnoibat;
    boolean thuonghieu;


    public List<ThuongHieu> getThuongHieus() {
        return thuongHieus;
    }

    public void setThuongHieus(List<ThuongHieu> thuongHieus) {
        this.thuongHieus = thuongHieus;
    }

    public List<SanPham> getSamPhams() {
        return samPhams;
    }

    public void setSamPhams(List<SanPham> samPhams) {
        this.samPhams = samPhams;
    }

    public String getTennoibat() {
        return tennoibat;
    }

    public void setTennoibat(String tennoibat) {
        this.tennoibat = tennoibat;
    }

    public String getTentopnoibat() {
        return tentopnoibat;
    }

    public void setTentopnoibat(String tentopnoibat) {
        this.tentopnoibat = tentopnoibat;
    }


    public boolean isThuonghieu() {
        return thuonghieu;
    }

    public void setThuonghieu(boolean thuonghieu) {
        this.thuonghieu = thuonghieu;
    }
}
