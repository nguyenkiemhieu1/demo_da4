package com.example.demo_da4.View.TrangChu.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demo_da4.Adapter.AdapterDienThoaiDienTu;
import com.example.demo_da4.Adapter.AdapterDienTu;
import com.example.demo_da4.Adapter.AdapterThuongHieuLonDienTu;
import com.example.demo_da4.Model.ObjectClass.DienTu;
import com.example.demo_da4.Model.ObjectClass.SanPham;
import com.example.demo_da4.Model.ObjectClass.ThuongHieu;
import com.example.demo_da4.Presenter.Trangchu_DienTu.PresenterLogicDienTu;
import com.example.demo_da4.R;
import com.example.demo_da4.View.TrangChu.ViewDienTu;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FragmentDienTu extends Fragment  implements ViewDienTu {

    RecyclerView recyclerView, recycler_thuonghieulon, recycler_sanphammoi;
    List<DienTu> dienTuList;
    PresenterLogicDienTu presenterLogicDienTu;
    ImageView img_sp1,img_sp3, img_sp2;
    TextView txt_sp1,txt_sp2,txt_sp3;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.layout_dientu, container, false);
        recyclerView=(RecyclerView) view.findViewById(R.id.recycler_dientu);
        recycler_thuonghieulon = (RecyclerView) view.findViewById(R.id.recycler_thuonghieulon);
        recycler_sanphammoi = (RecyclerView) view.findViewById(R.id.recycler_sanphammoi);
        img_sp1 = (ImageView) view.findViewById(R.id.img_sp1);
        img_sp2 = (ImageView) view.findViewById(R.id.img_sp2);
        img_sp3 = (ImageView) view.findViewById(R.id.img_sp3);

        txt_sp1 = (TextView) view.findViewById(R.id.txt_sp1);
        txt_sp2 = (TextView) view.findViewById(R.id.txt_sp2);
        txt_sp3 = (TextView) view.findViewById(R.id.txt_sp3);

        presenterLogicDienTu = new PresenterLogicDienTu(this);

        dienTuList = new ArrayList<>();
         presenterLogicDienTu.LayDanhSachDienTu();
         presenterLogicDienTu.layDanhSachLogothuongHieu();
         presenterLogicDienTu.LayDanhSachSanPhamMoiVe();
        return view;
    }

    @Override
    public void HienThiDanhSach(List<DienTu> dienTus) {
        dienTuList = dienTus;

        AdapterDienTu  adapterDienTu= new AdapterDienTu(getContext(), dienTuList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapterDienTu);

        adapterDienTu.notifyDataSetChanged();

    }

    @Override
    public void HienThiLogoThuongHieu(List<ThuongHieu> thuongHieus) {
        AdapterThuongHieuLonDienTu adapterThuongHieuLonDienTu = new AdapterThuongHieuLonDienTu(getContext(), thuongHieus);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(), 2, GridLayoutManager.HORIZONTAL, false);

        recycler_thuonghieulon.setLayoutManager(layoutManager);
        recycler_thuonghieulon .setAdapter(adapterThuongHieuLonDienTu);
        adapterThuongHieuLonDienTu.notifyDataSetChanged();
    }

    @Override
    public void LoiLayDuLieu() {

    }

    @Override
    public void SanPhamMoiVe(List<SanPham> sanPhamList) {
        AdapterDienThoaiDienTu dienThoaiDienTu = new AdapterDienThoaiDienTu(getContext(),R.layout.custom_layout_grid, sanPhamList);

        RecyclerView.LayoutManager layoutManager1 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recycler_sanphammoi.setLayoutManager(layoutManager1);
        recycler_sanphammoi.setAdapter(dienThoaiDienTu);
        dienThoaiDienTu.notifyDataSetChanged();

        Random random = new Random();

        int vitri = random.nextInt(sanPhamList.size());
//        fit tự động điều chỉnh ảnh về kích thước của img
        Picasso.get().load(sanPhamList.get(vitri).getANHLON()).fit().centerInside().into(img_sp1);
         txt_sp1.setText(sanPhamList.get(vitri).getTENSANPHAM());

        int vitri2 = random.nextInt(sanPhamList.size());
        Picasso.get().load(sanPhamList.get(vitri2).getANHLON()).fit().centerInside().into(img_sp2);
        txt_sp2.setText(sanPhamList.get(vitri2).getTENSANPHAM());

        int vitri3 = random.nextInt(sanPhamList.size());
        Picasso.get().load(sanPhamList.get(vitri3).getANHLON()).fit().centerInside().into(img_sp3);
        txt_sp3.setText(sanPhamList.get(vitri3).getTENSANPHAM());

    }
}
