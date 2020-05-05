package com.example.demo_da4.Presenter.TrangChu.XuLyMenu;

import com.example.demo_da4.ConnectInternet.DownloadJSON;
import com.example.demo_da4.Model.ObjectClass.LoaiSanPham;
import com.example.demo_da4.Model.Trangchu.XuLyMenu.XuLyJSONMenu;
import com.example.demo_da4.View.TrangChu.TrangChuActivity;
import com.example.demo_da4.View.TrangChu.ViewXuLyMenu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class PresenterLogicXuLyMenu implements IPresenterXuLyMenu {
    ViewXuLyMenu viewXuLyMenu;
    public  PresenterLogicXuLyMenu(ViewXuLyMenu viewXuLyMenu){
        this.viewXuLyMenu=viewXuLyMenu;

    }
    @Override
    public void LayDanhSachMenu() {
        List<LoaiSanPham> loaiSanPhamList;
        String dataJSON="";
        List<HashMap<String, String>> attrs=new ArrayList<>();
//        GET
//        String duongdan = TrangChuActivity.SERVER_NAME+ "/weblazada/loaisanpham.php?maloaicha=0";
//        DownloadJSON downloadJSON=new DownloadJSON(duongdan);
//         end
////       POST
        String duongdan= TrangChuActivity.SERVER_NAME;

        HashMap<String , String > hsham= new HashMap<>();
        hsham.put("ham", "LayDanhSachMenu");

        HashMap<String , String > hsMaLoaiCha= new HashMap<>();
        hsMaLoaiCha.put("maloaicha", "0");

        attrs.add(hsMaLoaiCha);
        attrs.add(hsham);

        DownloadJSON downloadJSON= new DownloadJSON(duongdan, attrs);
//        end
        downloadJSON.execute();

        try {
            dataJSON=downloadJSON.get();
            XuLyJSONMenu xuLyJSONMenu=new XuLyJSONMenu();
            loaiSanPhamList= xuLyJSONMenu.ParserJSONMenu(dataJSON);
            viewXuLyMenu.HienThiDanhSachMenu(loaiSanPhamList);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }
}
