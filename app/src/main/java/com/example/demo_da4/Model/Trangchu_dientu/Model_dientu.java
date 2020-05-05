package com.example.demo_da4.Model.Trangchu_dientu;

import com.example.demo_da4.ConnectInternet.DownloadJSON;
import com.example.demo_da4.Model.ObjectClass.SanPham;
import com.example.demo_da4.Model.ObjectClass.ThuongHieu;
import com.example.demo_da4.View.TrangChu.TrangChuActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class Model_dientu {
    public List<ThuongHieu> LayThuongHieuLon(String tenham, String tenmang){
        List<ThuongHieu> thuongHieuList = new ArrayList<>();
        List<HashMap<String, String>> attrs=new ArrayList<>();
        String dataJSON="";
//
        String duongdan= TrangChuActivity.SERVER_NAME;

        HashMap<String , String > hsHam= new HashMap<>();
        hsHam.put("ham", tenham);

        attrs.add(hsHam);
        DownloadJSON downloadJSON= new DownloadJSON(duongdan, attrs);

        downloadJSON.execute();
//        String duongdan = "http://192.168.43.86/weblazada/loaisanpham.php?maloaicha=0";
//        DownloadJSON downloadJSON=new DownloadJSON(duongdan);
//        downloadJSON.execute();


        try {
            dataJSON=downloadJSON.get();

            JSONObject jsonObject = new JSONObject(dataJSON);
            JSONArray danhsachthuonghieu = jsonObject.getJSONArray(tenmang);
            int dem = danhsachthuonghieu.length();
            for(int i=0; i<dem; i++){
                ThuongHieu thuongHieu = new ThuongHieu();
                JSONObject object =  danhsachthuonghieu.getJSONObject(i);
                thuongHieu.setMATHUONGHIEU(object.getInt("MASP"));
                thuongHieu.setTENTHUONGHIEU(object.getString("TENSP"));
                thuongHieu.setHINHTHUONGHIEU(object.getString("HINHSANPHAM"));

                thuongHieuList.add(thuongHieu);


            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return thuongHieuList;
    }

//truyền tham số đọộng

    public  List<SanPham> LayDanhSachSanPhamTop(String tenham, String tenmang){
        List<SanPham> sanPhamList = new ArrayList<>();
        List<HashMap<String, String>> attrs=new ArrayList<>();
        String dataJSON="";
//
        String duongdan= TrangChuActivity.SERVER_NAME;

        HashMap<String , String > hsHam= new HashMap<>();
        hsHam.put("ham", tenham );

        attrs.add(hsHam);
        DownloadJSON downloadJSON= new DownloadJSON(duongdan, attrs);

        downloadJSON.execute();
//        String duongdan = "http://192.168.43.86/weblazada/loaisanpham.php?maloaicha=0";
//        DownloadJSON downloadJSON=new DownloadJSON(duongdan);
//        downloadJSON.execute();


        try {
            dataJSON=downloadJSON.get();

            JSONObject jsonObject = new JSONObject(dataJSON);
            JSONArray danhsachSanPham = jsonObject.getJSONArray(tenmang);
            int dem = danhsachSanPham.length();
            for(int i=0; i<dem; i++){
                SanPham sanPham = new SanPham();
                JSONObject object =  danhsachSanPham.getJSONObject(i);
                sanPham.setMASP(object.getInt("MASP"));
                sanPham.setTENSANPHAM(object.getString("TENSP"));
                sanPham.setGIA(object.getInt("GIATIEN"));
                sanPham.setANHLON(object.getString("HINHSANPHAM"));

                sanPhamList.add(sanPham);


            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return sanPhamList;

    }
    public List<ThuongHieu>  LayDanhsachThuongHieuLon(String tenham, String tenmang){
        List<ThuongHieu> thuongHieuList = new ArrayList<>();
        List<HashMap<String, String>> attrs=new ArrayList<>();
        String dataJSON="";
//
        String duongdan= TrangChuActivity.SERVER_NAME;

        HashMap<String , String > hsHam= new HashMap<>();
        hsHam.put("ham", tenham);

        attrs.add(hsHam);
        DownloadJSON downloadJSON= new DownloadJSON(duongdan, attrs);

        downloadJSON.execute();
//        String duongdan = "http://192.168.43.86/weblazada/loaisanpham.php?maloaicha=0";
//        DownloadJSON downloadJSON=new DownloadJSON(duongdan);
//        downloadJSON.execute();


        try {
            dataJSON=downloadJSON.get();

            JSONObject jsonObject = new JSONObject(dataJSON);
            JSONArray danhsachthuonghieu = jsonObject.getJSONArray(tenmang);
            int dem = danhsachthuonghieu.length();
            for(int i=0; i<dem; i++){
                ThuongHieu thuongHieu = new ThuongHieu();
                JSONObject object =  danhsachthuonghieu.getJSONObject(i);
                thuongHieu.setMATHUONGHIEU(object.getInt("MASP"));
                thuongHieu.setTENTHUONGHIEU(object.getString("TENSP"));
                thuongHieu.setHINHTHUONGHIEU(object.getString("HINHSANPHAM"));

                thuongHieuList.add(thuongHieu);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return thuongHieuList;
    }
}
