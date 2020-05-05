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
import androidx.fragment.app.Fragment;

import com.example.demo_da4.Model.DangNhap_DangKi.ModelDangNhap;
import com.example.demo_da4.R;
import com.example.demo_da4.View.TrangChu.TrangChuActivity;


public class FragmentDangNhap extends Fragment implements View.OnClickListener {
   Button btnDangNhap;
   EditText edtDiaChiEmailDangNhap, edtMatKhauDangNhap;
    ModelDangNhap model_dangNhap;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_fragment_dangnhap, container, false);

        btnDangNhap = (Button)view.findViewById(R.id.btnDangNhap);
        edtDiaChiEmailDangNhap=(EditText)view.findViewById(R.id.edtDiaChiEmailDangNhap);
        edtMatKhauDangNhap=(EditText)view.findViewById(R.id.edtMatKhauDangNhap);
        btnDangNhap.setOnClickListener(this);
        return view;

    }

    @Override
    public void onClick(View view) {
        String tendangnhap = edtDiaChiEmailDangNhap.getText().toString();
        String matkhau =  edtMatKhauDangNhap.getText().toString();
        model_dangNhap = new ModelDangNhap();
        boolean kiemtra = model_dangNhap.KiemTraDangNhap(getActivity() ,tendangnhap, matkhau);
        if(kiemtra == true){
            Intent intent = new Intent(getActivity(), TrangChuActivity.class);
            startActivity(intent);

        }else {
            Toast.makeText(getActivity(), "Đăng nhập thất bại", Toast.LENGTH_LONG).show();

        }




    }
}
