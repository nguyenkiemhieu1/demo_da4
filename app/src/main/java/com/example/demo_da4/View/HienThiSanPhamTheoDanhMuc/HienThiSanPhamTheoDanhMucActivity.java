package com.example.demo_da4.View.HienThiSanPhamTheoDanhMuc;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.widget.Toolbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demo_da4.Adapter.AdapterDienThoaiDienTu;
import com.example.demo_da4.Model.ObjectClass.SanPham;
import com.example.demo_da4.Presenter.HienThiSanPhamTheoDanhMuc.PresenterLogicHienThiSanPhamTheoDanhMuc;
import com.example.demo_da4.R;
import com.example.demo_da4.View.TrangChu.ViewHienThiSanPhamTheoDanhMuc;

import java.util.List;

public class HienThiSanPhamTheoDanhMucActivity extends AppCompatActivity implements ViewHienThiSanPhamTheoDanhMuc, View.OnClickListener{
    RecyclerView recyclerView;
    Button btn_ThayDoiTrangThaiRecycler;
    boolean danggrid = true;
    RecyclerView.LayoutManager layoutManager;
    PresenterLogicHienThiSanPhamTheoDanhMuc presenterLogicHienThiSanPhamTheoDanhMuc;
    int masp;
    boolean kiemtra;
    AdapterDienThoaiDienTu adapterDienThoaiDienTu;
    Toolbar toolbar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_hienthisanphamtheodanhmuc);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_hienthisanphamtheodanhmuc);
        btn_ThayDoiTrangThaiRecycler = (Button) findViewById(R.id.btn_ThayDoiTrangThaiRecycler);
        toolbar = (Toolbar) findViewById(R.id.toolbar);



        Intent intent = getIntent();
        masp = intent.getIntExtra("MASP", 0);
        String tenloai = intent.getStringExtra("TENLOAI");
        kiemtra = intent.getBooleanExtra("KIEMTRA", false);

//      Log.d("chon",  masp +  " - " + tenloai + "  -  " +kiemtra  );

        presenterLogicHienThiSanPhamTheoDanhMuc = new PresenterLogicHienThiSanPhamTheoDanhMuc(this);
        presenterLogicHienThiSanPhamTheoDanhMuc.LayDanhSachSanPhamTheoMaLoai(masp, kiemtra);


        btn_ThayDoiTrangThaiRecycler.setOnClickListener(this);
        toolbar.setTitle(tenloai);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menutrangchu, menu);
        return true;
    }

    @Override
    public void HienDanhSachSanPham(List<SanPham> sanPhams) {
//        nếu ở frament truyền getActivity, Adapter : context, activity: this
//        AdapterDienThoaiDienTu adapterDienThoaiDienTu = new AdapterDienThoaiDienTu(HienThiSanPhamTheoDanhMucActivity.this,R.layout.custom_layout_grid, sanPhams);

//        sanPhamList1 = sanPhams;
        if(danggrid ){
            layoutManager = new GridLayoutManager(HienThiSanPhamTheoDanhMucActivity.this,2);
            adapterDienThoaiDienTu = new AdapterDienThoaiDienTu(HienThiSanPhamTheoDanhMucActivity.this,R.layout.custom_layout_grid, sanPhams);
        }else {
            layoutManager = new LinearLayoutManager(HienThiSanPhamTheoDanhMucActivity.this);
            adapterDienThoaiDienTu = new AdapterDienThoaiDienTu(HienThiSanPhamTheoDanhMucActivity.this,R.layout.custom_layout_list, sanPhams);
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
