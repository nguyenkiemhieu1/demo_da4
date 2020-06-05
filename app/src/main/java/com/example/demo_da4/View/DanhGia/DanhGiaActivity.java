package com.example.demo_da4.View.DanhGia;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;
import android.os.Build;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.demo_da4.Model.ObjectClass.DanhGia;
import com.example.demo_da4.Presenter.DanhGia.PresenterLogicDanhGia;
import com.example.demo_da4.R;
import com.google.android.material.textfield.TextInputLayout;

public class DanhGiaActivity extends AppCompatActivity implements ViewDanhGia, View.OnClickListener {
    TextInputLayout ip_tieude, ip_noidung;
    EditText edt_tieude, edt_noidung;
    int masp = 0;
    Button btndongy;
    PresenterLogicDanhGia presenterLogicDanhGia;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_danhgia_activity);

        ip_noidung = (TextInputLayout) findViewById(R.id.ip_noidung);
        ip_tieude = (TextInputLayout) findViewById(R.id.ip_tieude);
        edt_noidung = (EditText) findViewById(R.id.edt_noidung);
        edt_tieude = (EditText) findViewById(R.id.edt_tieude);

        btndongy = (Button) findViewById(R.id.btn_Danhgia);
        btndongy.setOnClickListener(this);

        presenterLogicDanhGia = new PresenterLogicDanhGia(this);


        masp = getIntent().getIntExtra("masp", 0);
        Log.d("masp", String.valueOf(masp));


    }

    @Override
    public void DanhGiaThanhCong() {
        Toast.makeText(this, "Đánh giá thành công", Toast.LENGTH_LONG).show();
    }

    @Override
    public void DanhGiaThatBai() {
        Toast.makeText(this, "Bạn không thể đánh giá sản phẩm này", Toast.LENGTH_LONG).show();

    }

    @Override
    public void onClick(View view) {
        String tenthietbi = Build.MODEL;
        String tieude = edt_tieude.getText().toString();
        String noidung = edt_noidung.getText().toString();

        if (tieude.trim().length() > 0) {
            ip_tieude.setErrorEnabled(false);
            ip_tieude.setError("");

        }else {
            ip_tieude.setErrorEnabled(true);
            ip_tieude.setError("Bạn chưa nhập tiêu đề");

        }
        if (noidung .trim().length() > 0) {
            ip_noidung.setErrorEnabled(false);
            ip_noidung.setError("");

        }else {
            ip_noidung.setErrorEnabled(true);
            ip_noidung.setError("Bạn chưa nhập nội dung");

        }
        if(!ip_noidung.isErrorEnabled()&& !ip_tieude.isErrorEnabled()){
            DanhGia danhGia = new DanhGia();
            danhGia.setMASP(masp);
            danhGia.setNOIDUNG(noidung);
            danhGia.setTIEUDE(tieude);
            danhGia.setTENTHIETBI(tenthietbi);

            presenterLogicDanhGia.ThemDanhGia(danhGia);
            finish();
        }
    }
}
