package com.example.demo_da4.Model.ThanhToan;

import android.util.Log;

import com.example.demo_da4.ConnectInternet.DownloadJSON;
import com.example.demo_da4.Model.ObjectClass.ChiTietHoaDon;
import com.example.demo_da4.Model.ObjectClass.DanhGia;
import com.example.demo_da4.Model.ObjectClass.DonHang;
import com.example.demo_da4.View.TrangChu.TrangChuActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class Model_ThanhToan {
    public boolean ThemHoaDon(DonHang donHang){
        String duongdan= TrangChuActivity.SERVER_NAME;
        boolean kiemtra=false;
        List<HashMap<String, String>> attrs=new ArrayList<>();

        HashMap<String,String> hsHam = new HashMap<>();
        hsHam.put("ham","ThemDonHang");

        List<ChiTietHoaDon> chiTietHoaDonList = donHang.getChiTietHoaDons();
        String chuoijson = "{\"DANHSACHSANPHAM\" :[";
        for(int i = 0; i< chiTietHoaDonList.size(); i++){
            chuoijson += "{";
            chuoijson += "\"masp\" : "+ chiTietHoaDonList.get(i).getMaSP() +    " , ";
            chuoijson += "\"soluong\":"+chiTietHoaDonList.get(i).getSoLuong() ;


            if (i == chiTietHoaDonList.size()-1){
                chuoijson += "}";
            }else{
                chuoijson += "},";
            }


        }
        chuoijson += "]}";
        Log.d("chuoi", chuoijson);

        HashMap<String,String> hsDanhSachSanPham = new HashMap<>();
        hsDanhSachSanPham.put("danhsachsanpham",chuoijson);

        HashMap<String,String> hsTenNguoiNhan = new HashMap<>();
        hsTenNguoiNhan.put("tennguoinhan",donHang.getTenNguoiNhan());

        HashMap<String,String> hsSoST = new HashMap<>();
        hsSoST.put("sodt",donHang.getSoDT());

        HashMap<String,String> hsDiaChi = new HashMap<>();
        hsDiaChi.put("diachi",donHang.getDiaChi());

        HashMap<String,String> hsChuyenKhoan = new HashMap<>();
        hsChuyenKhoan.put("chuyenkhoan","0");


        attrs.add(hsHam);
        attrs.add(hsDanhSachSanPham);
        attrs.add(hsTenNguoiNhan);
        attrs.add(hsSoST);
        attrs.add(hsDiaChi);
        attrs.add(hsChuyenKhoan);

        DownloadJSON downloadJSON=new DownloadJSON(duongdan, attrs);
        downloadJSON.execute();
        try {
            String dulieuJson=downloadJSON.get();
            JSONObject jsonObject= new JSONObject(dulieuJson);
            String ketqua=jsonObject.getString("ketqua");
            Log.d(  "t", ketqua);
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
