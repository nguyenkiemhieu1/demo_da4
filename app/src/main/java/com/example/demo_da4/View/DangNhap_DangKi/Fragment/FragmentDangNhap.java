package com.example.demo_da4.View.DangNhap_DangKi.Fragment;

import android.content.Intent;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.demo_da4.Model.DangNhap_DangKi.ModelDangNhap;
import com.example.demo_da4.R;
import com.example.demo_da4.View.TrangChu.TrangChuActivity;


public class FragmentDangNhap extends AppCompatActivity implements View.OnClickListener {
    Button btnDangNhap;
    EditText edtDiaChiEmailDangNhap, edtMatKhauDangNhap;
    ModelDangNhap model_dangNhap;
    Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_fragment_dangnhap);
        btnDangNhap = (Button) findViewById(R.id.btnDangNhap);
        edtDiaChiEmailDangNhap = (EditText) findViewById(R.id.edtDiaChiEmailDangNhap);
        edtMatKhauDangNhap = (EditText) findViewById(R.id.edtMatKhauDangNhap);
        toolbar = (Toolbar) findViewById(R.id.id_toolbarDangNhap);
        btnDangNhap.setOnClickListener(this);
        toolbar.setTitle("Đăng nhập");

        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),TrangChuActivity.class));
            }
        });
    }


    @Override
    public void onClick(View view) {
        String tendangnhap = edtDiaChiEmailDangNhap.getText().toString();
        String matkhau = edtMatKhauDangNhap.getText().toString();
        model_dangNhap = new ModelDangNhap();
        boolean kiemtra = model_dangNhap.KiemTraDangNhap(this, tendangnhap, matkhau);
        if (kiemtra == true) {
            finish();
        } else {
            Toast.makeText(this, "Đăng nhập thất bại", Toast.LENGTH_LONG).show();
        }
    }
}
