package com.example.demo_da4.Model.Trangchu.XuLyMenu;

import android.util.Log;

import com.example.demo_da4.ConnectInternet.DownloadJSON;
import com.example.demo_da4.Model.ObjectClass.LoaiSanPham;
import com.example.demo_da4.View.TrangChu.TrangChuActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class XuLyJSONMenu {
    public List<LoaiSanPham> ParserJSONMenu(String dulieujson){
        List<LoaiSanPham> loaiSanPhamList= new ArrayList<>();

        try {
            Log.d("kiemtra", dulieujson);
            JSONObject jsonObject= new JSONObject(dulieujson);
                JSONArray loaisanpham= jsonObject.getJSONArray("LOAISANPHAM");
                int count = loaisanpham.length();
                for(int i=0;i<count;i++){
                    JSONObject value = loaisanpham.getJSONObject(i);

                    LoaiSanPham dataloaiSanPham = new LoaiSanPham();
                    dataloaiSanPham.setMALOAISP(Integer.parseInt(value.getString("MALOAISP")));
                    dataloaiSanPham.setMALOAICHA(Integer.parseInt(value.getString("MALOAI_CHA")));
                    dataloaiSanPham.setTENLOAISP(value.getString("TENLOAISP"));


                    loaiSanPhamList.add(dataloaiSanPham);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return loaiSanPhamList;

    }
    public  List<LoaiSanPham> LayLoaiSanPhamTheoMaLoai(int maloaisp){
        List<LoaiSanPham> loaiSanPhamList =new ArrayList<>();
        List<HashMap<String, String>> attrs=new ArrayList<>();
        String dataJSON="";
//
        String duongdan= TrangChuActivity.SERVER_NAME;

        HashMap<String , String > hsHam= new HashMap<>();
        hsHam.put("ham", "LayDanhSachMenu");

        HashMap<String , String > hsMaLoaiCha= new HashMap<>();
        hsMaLoaiCha.put("maloaicha", String.valueOf(maloaisp));

        attrs.add(hsMaLoaiCha);
        attrs.add(hsHam);
        DownloadJSON downloadJSON= new DownloadJSON(duongdan, attrs);

        downloadJSON.execute();
//        String duongdan = "http://192.168.43.86/weblazada/loaisanpham.php?maloaicha=0";
//        DownloadJSON downloadJSON=new DownloadJSON(duongdan);
//        downloadJSON.execute();


        try {
            dataJSON=downloadJSON.get();
            XuLyJSONMenu xuLyJSONMenu=new XuLyJSONMenu();
            loaiSanPhamList = xuLyJSONMenu.ParserJSONMenu(dataJSON);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return loaiSanPhamList;
    }
}
