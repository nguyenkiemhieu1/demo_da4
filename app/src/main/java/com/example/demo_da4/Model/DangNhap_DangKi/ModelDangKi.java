package com.example.demo_da4.Model.DangNhap_DangKi;

import android.util.Log;

import com.example.demo_da4.ConnectInternet.DownloadJSON;
import com.example.demo_da4.Model.ObjectClass.NhanVien;
import com.example.demo_da4.View.TrangChu.TrangChuActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ModelDangKi {
    public Boolean DangKiThanhVien(NhanVien nhanVien){
        boolean kiemtra=false;
        List<HashMap<String, String>> attrs=new ArrayList<>();
        String duongdan= TrangChuActivity.SERVER_NAME;

        HashMap<String,String> hsHam = new HashMap<>();
        hsHam.put("ham","DangKyThanhVien");

        HashMap<String,String> hsTenNV = new HashMap<>();
        hsTenNV.put("tennv",nhanVien.getTenNV());

        HashMap<String,String> hsTenDN = new HashMap<>();
        hsTenDN.put("tendangnhap",nhanVien.getTenDN());

        HashMap<String,String> hsMatKhau = new HashMap<>();
        hsMatKhau.put("matkhau",nhanVien.getMatKhau());

        HashMap<String,String> hsMaLoaiNV = new HashMap<>();
        hsMaLoaiNV.put("maloainv",String.valueOf(nhanVien.getMaLoaiNV()));

        HashMap<String,String> hsEmailDocQuyen = new HashMap<>();
        hsEmailDocQuyen.put("emaildocquyen",nhanVien.getEmailDocQuyen());


        attrs.add(hsHam);
        attrs.add(hsTenNV);
        attrs.add(hsTenDN);
        attrs.add(hsMatKhau);
        attrs.add(hsMaLoaiNV);
        attrs.add(hsEmailDocQuyen);
        DownloadJSON downloadJSON=new DownloadJSON(duongdan, attrs);
        downloadJSON.execute();
        try {
            String dulieuJson=downloadJSON.get();
            JSONObject jsonObject= new JSONObject(dulieuJson);
            String ketqua=jsonObject.getString("ketqua");
            if(ketqua.equals("true")){
                kiemtra = true;
            }
            else {
                kiemtra = false;
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return kiemtra;
    }
}
