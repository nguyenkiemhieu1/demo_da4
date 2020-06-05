package com.example.demo_da4.View.HienThiSanPhamTheoDanhMuc;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demo_da4.Adapter.AdapterDienThoaiDienTu;
import com.example.demo_da4.Model.ObjectClass.SanPham;

import com.example.demo_da4.Presenter.HienThiSanPhamTheoDanhMuc.PresenterLogicHienThiSanPhamTheoDanhMuc;
import com.example.demo_da4.R;

import com.example.demo_da4.View.TrangChu.ViewHienThiSanPhamTheoDanhMuc;

import java.util.List;

public class HienThiSanPhamTheoDanhMucActivity extends Fragment implements ViewHienThiSanPhamTheoDanhMuc, View.OnClickListener{
    RecyclerView recyclerView;
    Button btn_ThayDoiTrangThaiRecycler;
    boolean danggrid = true;
    RecyclerView.LayoutManager layoutManager;
    PresenterLogicHienThiSanPhamTheoDanhMuc presenterLogicHienThiSanPhamTheoDanhMuc;
    int masp;
    boolean kiemtra;
    AdapterDienThoaiDienTu adapterDienThoaiDienTu;
    Toolbar toolbar;


    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.layout_hienthisanphamtheodanhmuc, viewGroup, false);

        setHasOptionsMenu(false);

        recyclerView = (RecyclerView) v.findViewById(R.id.recycler_hienthisanphamtheodanhmuc);
        btn_ThayDoiTrangThaiRecycler = (Button) v.findViewById(R.id.btn_ThayDoiTrangThaiRecycler);
        toolbar = (Toolbar) v.findViewById(R.id.toolbar_danhmuc);


        Bundle bundle = getArguments();
        masp = bundle.getInt("MALOAI", 0);
        String tenloai = bundle.getString("TENLOAI");
        kiemtra = bundle.getBoolean("KIEMTRA", false);
        toolbar.setTitle(tenloai);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setHomeButtonEnabled(true);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        presenterLogicHienThiSanPhamTheoDanhMuc = new PresenterLogicHienThiSanPhamTheoDanhMuc(this);
        presenterLogicHienThiSanPhamTheoDanhMuc.LayDanhSachSanPhamTheoMaLoai(masp, kiemtra);


        btn_ThayDoiTrangThaiRecycler.setOnClickListener(this);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().popBackStack("TrangchuActivity", FragmentManager.POP_BACK_STACK_INCLUSIVE);
            }
        });

        return v;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menutrangchu, menu);


        super.onCreateOptionsMenu(menu, inflater);
    }



    @Override
    public void HienDanhSachSanPham(List<SanPham> sanPhams) {
//        nếu ở frament truyền getActivity, Adapter : context, activity: this
//        AdapterDienThoaiDienTu adapterDienThoaiDienTu = new AdapterDienThoaiDienTu(HienThiSanPhamTheoDanhMucActivity.this,R.layout.custom_layout_grid, sanPhams);

//        sanPhamList1 = sanPhams;
        if(danggrid ){
            layoutManager = new GridLayoutManager(getContext(),2);
            adapterDienThoaiDienTu = new AdapterDienThoaiDienTu(getContext(),R.layout.custom_layout_grid, sanPhams);
        }else {
            layoutManager = new LinearLayoutManager(getContext());
            adapterDienThoaiDienTu = new AdapterDienThoaiDienTu(getContext(),R.layout.custom_layout_list, sanPhams);
        }
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapterDienThoaiDienTu);
        adapterDienThoaiDienTu.notifyDataSetChanged();

    }

    @Override
    public void LoiHienDanhSachSanPham() {

    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id){
            case R.id.btn_ThayDoiTrangThaiRecycler:
                danggrid = !danggrid;
                presenterLogicHienThiSanPhamTheoDanhMuc.LayDanhSachSanPhamTheoMaLoai(masp, kiemtra);
                break;
        }

    }
}
