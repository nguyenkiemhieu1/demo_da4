package com.example.demo_da4.View.ThanhToan;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.demo_da4.Model.ObjectClass.ChiTietHoaDon;
import com.example.demo_da4.Model.ObjectClass.ChiTietSanPham;
import com.example.demo_da4.Model.ObjectClass.DonHang;
import com.example.demo_da4.Model.ObjectClass.SanPham;
import com.example.demo_da4.Presenter.ThanhToan.PresenterLogicThanhToan;
import com.example.demo_da4.R;

import java.util.ArrayList;
import java.util.List;

public class ThanhToanActivity extends AppCompatActivity implements View.OnClickListener, ViewThanhToan {
    Toolbar toolbar;
    EditText edtTenNguoiNhan, edtDiaChi, edtSDT;
    ImageButton imb_giaohang, imb_chuyenkhoan;
    Button btn_MuaNgay;
    CheckBox checkBox;
    PresenterLogicThanhToan presenterLogicThanhToan;
    List<ChiTietHoaDon> chiTietHoaDonList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_hoadon);

        toolbar = (Toolbar)findViewById(R.id.toolbar_muahang);
        edtTenNguoiNhan = (EditText) findViewById(R.id.edtTenNguoiNhan);
        edtDiaChi = (EditText) findViewById(R.id.edtDiaChi);
        edtSDT = (EditText) findViewById(R.id.edtSDT);
        imb_chuyenkhoan = (ImageButton) findViewById(R.id.imb_chuyenkhoan);
        imb_giaohang = (ImageButton) findViewById(R.id.imb_giaohang);
        btn_MuaNgay = (Button) findViewById(R.id.btn_MuaNgay);
        checkBox = (CheckBox) findViewById(R.id.cb_thoathuan);


        presenterLogicThanhToan = new PresenterLogicThanhToan(this);
        presenterLogicThanhToan.LayDanhSachSanPhamTrongGioHang(this);

        toolbar.setTitle("THANHTOAN");
        setSupportActionBar(toolbar);
        btn_MuaNgay.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id){
            case R.id.btn_MuaNgay:
                String tennguoinhan = edtTenNguoiNhan.getText().toString();
                String sodt = edtSDT.getText().toString();
                String diachi = edtDiaChi.getText().toString();

                if(tennguoinhan.trim().length() > 0 && sodt.trim().length() > 0 && diachi.trim().length() > 0){
                    if(checkBox.isChecked()){
                        DonHang donHang = new DonHang();
                        donHang.setTenNguoiNhan(tennguoinhan);
                        donHang.setSoDT(sodt);
                        donHang.setDiaChi(diachi);
                        donHang.setChiTietHoaDons(chiTietHoaDonList);
                        presenterLogicThanhToan.themhoadon(donHang);

                    }else {
                        Toast.makeText(this, "Bạn chọn vào ô cam kết" , Toast.LENGTH_LONG).show();


                    }
                }else {
                    Toast.makeText(this, "Bạn chưa nhập đầy đủ thông tin" , Toast.LENGTH_LONG).show();

                }
                break;
        }

    }

    @Override
    public void DatHangThanhCong() {
        Toast.makeText(this, "Thanhf công" , Toast.LENGTH_LONG).show();

    }

    @Override
    public void DatHangThatBai() {
        Toast.makeText(this, "thất  bại" , Toast.LENGTH_LONG).show();

    }

    @Override
    public void LayDanhSachSanPhamTrongGioHang(List<SanPham> sanPhamList) {

        for (int i = 0; i < chiTietHoaDonList.size(); i++){
            ChiTietHoaDon chiTietHoaDon = new ChiTietHoaDon();
            chiTietHoaDon.setMaSP(sanPhamList.get(i).getMASP());
            chiTietHoaDon.setSoLuong(sanPhamList.get(i).getSOLUONG());

            chiTietHoaDonList.add(chiTietHoaDon);

        }
    }
}
