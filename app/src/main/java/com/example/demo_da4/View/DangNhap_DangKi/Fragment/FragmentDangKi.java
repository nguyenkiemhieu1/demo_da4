package com.example.demo_da4.View.DangNhap_DangKi.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.example.demo_da4.Model.ObjectClass.NhanVien;
import com.example.demo_da4.Presenter.DangKi.PresenterLogicDangKi;
import com.example.demo_da4.R;
import com.example.demo_da4.View.DangNhap_DangKi.ViewDangKi;
import com.example.demo_da4.View.TrangChu.TrangChuActivity;
import com.google.android.material.textfield.TextInputLayout;

public class FragmentDangKi extends AppCompatActivity implements ViewDangKi , View.OnClickListener, View.OnFocusChangeListener {
    PresenterLogicDangKi presenterLogicDangKi;
    Button btnDangKy;
    EditText edt_hotendk, edt_diachi_email, edt_nhaplai_matkhau, edt_matkhaudk;
    TextInputLayout ip_edt_hotendangki, ip_edt_diachi_email, ip_edt_matkhau, ip_edt_nhaplai_matkhau;
    Boolean kiemtrathongtin = false;
    Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_fragment_dangki);
        btnDangKy = (Button) findViewById(R.id.btnDangKy);
        toolbar = (Toolbar) findViewById(R.id.id_toolbarDangKi);
        edt_hotendk = (EditText) findViewById(R.id.edt_hotendk);
        edt_diachi_email = (EditText) findViewById(R.id.edt_diachi_email);
        edt_nhaplai_matkhau = (EditText) findViewById(R.id.edt_nhaplai_matkhau);
        edt_matkhaudk = (EditText) findViewById(R.id.edt_matkhaudk);

        ip_edt_hotendangki = (TextInputLayout) findViewById(R.id.ip_edt_hotendangki);
        ip_edt_diachi_email = (TextInputLayout) findViewById(R.id.ip_edt_diachi_email);
        ip_edt_matkhau = (TextInputLayout) findViewById(R.id.ip_edt_matkhau);
        ip_edt_nhaplai_matkhau = (TextInputLayout)findViewById(R.id.ip_edt_nhaplai_matkhau);
        toolbar.setTitle("Đăng kí");
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), TrangChuActivity.class));
            }
        });





    presenterLogicDangKi = new PresenterLogicDangKi(this);

        btnDangKy.setOnClickListener(this);
        edt_hotendk.setOnFocusChangeListener(this);
        edt_diachi_email.setOnFocusChangeListener(this);
        edt_nhaplai_matkhau.setOnFocusChangeListener(this);

    }

//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.layout_fragment_dangki, container, false);
//
//
//        btnDangKy = (Button) view.findViewById(R.id.btnDangKy);
//        edt_hotendk = (EditText) view.findViewById(R.id.edt_hotendk);
//        edt_diachi_email = (EditText) view.findViewById(R.id.edt_diachi_email);
//        edt_nhaplai_matkhau = (EditText) view.findViewById(R.id.edt_nhaplai_matkhau);
//        edt_matkhaudk = (EditText) view.findViewById(R.id.edt_matkhaudk);
//
//        ip_edt_hotendangki = (TextInputLayout) view.findViewById(R.id.ip_edt_hotendangki);
//        ip_edt_diachi_email = (TextInputLayout) view.findViewById(R.id.ip_edt_diachi_email);
//        ip_edt_matkhau = (TextInputLayout) view.findViewById(R.id.ip_edt_matkhau);
//        ip_edt_nhaplai_matkhau = (TextInputLayout) view.findViewById(R.id.ip_edt_nhaplai_matkhau);
//
//        presenterLogicDangKi = new PresenterLogicDangKi(this);
//
//        btnDangKy.setOnClickListener(this);
//        edt_hotendk.setOnFocusChangeListener(this);
//        edt_diachi_email.setOnFocusChangeListener(this);
//        edt_nhaplai_matkhau.setOnFocusChangeListener(this);
//
//
//        return view;
//    }

    @Override
    public void DangKiThanhCong() {
        Toast.makeText(this, "Đăng kí thành công", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void DangKiThatBai() {
        Toast.makeText(this, "Đăng kí thất bại", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.btnDangKy:
                btnDangKy();
                break;
        }

    }


    private void btnDangKy() {

        String hoten = edt_hotendk.getText().toString();
        String email = edt_diachi_email.getText().toString();
        String matkhau = edt_matkhaudk.getText().toString();
        String nhaplaimatkhau = edt_nhaplai_matkhau.getText().toString();


        if (kiemtrathongtin) {
            NhanVien nhanVien = new NhanVien();
            nhanVien.setTenNV(hoten);
            nhanVien.setTenDN(email);
            nhanVien.setMatKhau(matkhau);
            nhanVien.setMaLoaiNV(2);

            presenterLogicDangKi.ThucHienDangKi(nhanVien);

        }

    }

    @Override
    public void onFocusChange(View view, boolean b) {

        int id = view.getId();
        switch (id) {
            case R.id.edt_hotendk:
                if (!b) {
                    String chuoi = ((EditText) view).getText().toString();
                    if (chuoi.trim().equals("") || chuoi.equals(null)) {
                        ip_edt_hotendangki.setErrorEnabled(true);
                        ip_edt_hotendangki.setError("Bạn chưa nhập mục này!!");
                        kiemtrathongtin = false;
                    } else {
                        ip_edt_hotendangki.setErrorEnabled(false);
                        ip_edt_hotendangki.setError("");
                        kiemtrathongtin = true;

                    }
                }
                break;

            case R.id.edt_diachi_email:
                if (!b) {
                    String chuoi = ((EditText) view).getText().toString();

                    if (chuoi.trim().equals("") || chuoi.equals(null)) {
                        ip_edt_diachi_email.setErrorEnabled(true);
                        ip_edt_diachi_email.setError("Bạn hãy nhập địa chỉ email!");
                        kiemtrathongtin = false;
                    } else {
                        Boolean kiemtraemail = Patterns.EMAIL_ADDRESS.matcher(chuoi).matches();

                        if (!kiemtraemail) {
                            ip_edt_diachi_email.setErrorEnabled(false);
                            ip_edt_diachi_email.setError("Địa chỉ email không tồn tại !!! Hãy nhập lại");
                            kiemtrathongtin = false;
                        } else {
                            ip_edt_diachi_email.setErrorEnabled(false);
                            ip_edt_diachi_email.setError("");
                            kiemtrathongtin = true;

                        }
                    }
                }
                break;

            case R.id.edt_matkhaudk:
                break;

            case R.id.edt_nhaplai_matkhau:
                if (!b) {

                    String chuoi = ((EditText) view).getText().toString();

                    String matkhau = edt_matkhaudk.getText().toString();
                    if (!chuoi.equals(matkhau)) {

                        ip_edt_nhaplai_matkhau.setErrorEnabled(false);
                        ip_edt_nhaplai_matkhau.setError("Mật khẩu không trùng khớp!!");
                        kiemtrathongtin = false;
                    } else {
                        ip_edt_nhaplai_matkhau.setErrorEnabled(false);
                        ip_edt_nhaplai_matkhau.setError("");
                        kiemtrathongtin = true;

                    }
                }
                break;
        }
    }
}
