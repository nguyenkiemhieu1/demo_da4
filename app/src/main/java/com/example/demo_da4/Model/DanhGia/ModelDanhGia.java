package com.example.demo_da4.Model.DanhGia;

import com.example.demo_da4.ConnectInternet.DownloadJSON;
import com.example.demo_da4.Model.ObjectClass.DanhGia;
import com.example.demo_da4.View.TrangChu.TrangChuActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ModelDanhGia {
    public boolean ThemDanhGia(DanhGia danhGia){
        boolean kiemtra=false;
        List<HashMap<String, String>> attrs=new ArrayList<>();
        String duongdan= TrangChuActivity.SERVER_NAME;

        HashMap<String,String> hsHam = new HashMap<>();
        hsHam.put("ham","ThemDanhGia");

        HashMap<String,String> hsMaSp = new HashMap<>();
        hsMaSp.put("masp",String.valueOf(danhGia.getMASP()));

        HashMap<String,String> hsTieuDe = new HashMap<>();
        hsTieuDe.put("tieude",danhGia.getTIEUDE());

        HashMap<String,String> hsNoiDung = new HashMap<>();
        hsNoiDung.put("noidung",danhGia.getNOIDUNG());


        HashMap<String,String> hsTenThietBi = new HashMap<>();
        hsTenThietBi.put("tenthietbi",danhGia.getTENTHIETBI());


        attrs.add(hsHam);
        attrs.add(hsMaSp);
        attrs.add(hsTieuDe);
        attrs.add(hsNoiDung);
        attrs.add(hsTenThietBi);
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