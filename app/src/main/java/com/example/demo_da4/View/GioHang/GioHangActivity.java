package com.example.demo_da4.View.GioHang;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.widget.Toolbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demo_da4.Adapter.AdapterGioHang;
import com.example.demo_da4.Model.ObjectClass.SanPham;
import com.example.demo_da4.Presenter.GioHang.PresenterGioHang;
import com.example.demo_da4.R;
import com.example.demo_da4.View.ThanhToan.ThanhToanActivity;

import java.util.List;

public class GioHangActivity extends AppCompatActivity implements ViewGioHang, View.OnClickListener {
    RecyclerView recyclerView;
    PresenterGioHang presenterGioHang;
    Toolbar toolbar;
    Button btn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_giohang);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_gioHang);
        presenterGioHang = new PresenterGioHang(this);
        presenterGioHang.LayDanhSachSanPhamTrongGioHang(this);
        toolbar = (Toolbar) findViewById(R.id.toolbar_GioHang);
        btn = (Button) findViewById(R.id.btn_MuaNgay);
        btn.setOnClickListener(this);

        toolbar.setTitle("Giỏ hàng");
        setSupportActionBar(toolbar);


    }

    @Override
    public void HienThiDanhSachSanPhamTrongGIoHang(List<SanPham> sanPhamList) {
        RecyclerView.LayoutManager layoutManager =  new LinearLayoutManager(this);
        AdapterGioHang adapterGioHang = new AdapterGioHang(this, sanPhamList);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapterGioHang);

    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id){
            case  R.id.btn_MuaNgay:
                Intent intent =new Intent(GioHangActivity.this, ThanhToanActivity.class);
                startActivity(intent);
        }

    }
}
