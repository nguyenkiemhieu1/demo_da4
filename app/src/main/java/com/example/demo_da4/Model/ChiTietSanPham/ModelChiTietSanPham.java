package com.example.demo_da4.Model.ChiTietSanPham;

import com.example.demo_da4.ConnectInternet.DownloadJSON;
import com.example.demo_da4.Model.ObjectClass.ChiTietSanPham;
import com.example.demo_da4.Model.ObjectClass.SanPham;
import com.example.demo_da4.View.TrangChu.TrangChuActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ModelChiTietSanPham {
    public SanPham layChiTietSanPham(String tenham, String tenmang, int masp){
        SanPham sanPham = new SanPham();

        List<ChiTietSanPham> chiTietSanPhams = new ArrayList<>();


        List<HashMap<String, String>> attrs=new ArrayList<>();
        String dataJSON="";
//
        String duongdan= TrangChuActivity.SERVER_NAME;

        HashMap<String , String > hsHam= new HashMap<>();
        hsHam.put("ham", tenham );

        HashMap<String , String > hsMasp= new HashMap<>();
        hsMasp.put("masp", String.valueOf(masp));

        attrs.add(hsHam);
        attrs.add(hsMasp);
        DownloadJSON downloadJSON= new DownloadJSON(duongdan, attrs);

        downloadJSON.execute();
//        String duongdan = "http://192.168.43.86/weblazada/loaisanpham.php?maloaicha=0";
//        DownloadJSON downloadJSON=new DownloadJSON(duongdan);
//        downloadJSON.execute();


       try  {
            dataJSON=downloadJSON.get();

            JSONObject jsonObject = new JSONObject(dataJSON);
            JSONArray danhsachSanPham = jsonObject.getJSONArray(tenmang);
            int dem = danhsachSanPham.length();
            for(int i=0; i<dem; i++){
                JSONObject object =  danhsachSanPham.getJSONObject(i);
                sanPham.setMASP(object.getInt("MASP"));
                sanPham.setTENSANPHAM(object.getString("TENSP"));
                sanPham.setGIA(object.getInt("GIATIEN"));
                sanPham.setANHLON(object.getString("ANHLON"));
                sanPham.setSOLUONG(object.getInt("SOLUONG"));
                sanPham.setTHONGTIN(object.getString("THONGTIN"));
                sanPham.setMALOAISP(object.getInt("MALOAISP"));
                sanPham.setMATHUONGHIEU(object.getInt("MATHUONGHIEU"));
                sanPham.setLUOTMUA(object.getInt("LUOTMUA"));

                JSONArray jsonArrayThongSo = object.getJSONArray("THONGSOKITHUAT");

                for (int j = 0; j < jsonArrayThongSo.length(); j++){
                    JSONObject jsonObject1 = jsonArrayThongSo.getJSONObject(j);

                    for(int k = 0; k < jsonObject1.names().length(); k++){
                            String tenchitiet = jsonObject1.names().getString(k);

                        ChiTietSanPham chiTietSanPham = new ChiTietSanPham();
                        chiTietSanPham.setTenChiTiet(tenchitiet);
                        chiTietSanPham.setGiaTri(jsonObject1.getString(tenchitiet));

                        chiTietSanPhams.add(chiTietSanPham);


                    }
                }

                sanPham.setChiTietSanPhams(chiTietSanPhams);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return sanPham;
    }
}

