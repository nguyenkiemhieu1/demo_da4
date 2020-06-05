package com.example.demo_da4.View.ChiTietSanPham;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.ColorInt;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.demo_da4.Model.ObjectClass.ChiTietSanPham;
import com.example.demo_da4.Model.ObjectClass.SanPham;
import com.example.demo_da4.Presenter.ChiTietSanPham.PresenterLogicChiTietSanPham;
import com.example.demo_da4.R;
import com.example.demo_da4.View.DangNhap_DangKi.Fragment.FragmentDangNhap;
import com.example.demo_da4.View.DanhGia.DanhGiaActivity;
import com.example.demo_da4.View.GioHang.GioHangActivity;
import com.example.demo_da4.View.HienThiSanPhamTheoDanhMuc.HienThiSanPhamTheoDanhMucActivity;
import com.example.demo_da4.View.TrangChu.TrangChuActivity;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;


public class ChiTietSanPhamActivity extends AppCompatActivity implements ViewChiTietSanPham, View.OnClickListener {
    PresenterLogicChiTietSanPham presenterLogicChiTietSanPham;
    TextView txt_giatien, txt_tensanpham, txt_thongtinsanpham, txt_VietDanhGia, txtGioHang;
    ImageView imgAnhSP, img_xemthemchitiet, imbtn_ThemGioHang;
    Toolbar toolbar;
    boolean kiemtra = false;
    LinearLayout id_thongsokithuat;
    int masp;
    SanPham sanPhamGioHang;
    boolean onPause = false;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_chitietsanpham);

        txt_giatien = (TextView) findViewById(R.id.txt_giatien);
        txt_tensanpham = (TextView) findViewById(R.id.txt_tensannpham);
        imgAnhSP = (ImageView) findViewById(R.id.imgAnhSP);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        txt_thongtinsanpham = (TextView) findViewById(R.id.txt_thongtinsanpham);
        img_xemthemchitiet = (ImageView) findViewById(R.id.img_hienthithem);
        id_thongsokithuat = (LinearLayout) findViewById(R.id.id_thongsokithuat);
        txt_VietDanhGia = (TextView) findViewById(R.id.txt_VietDanhGia);
        imbtn_ThemGioHang = (ImageView) findViewById(R.id.imbtn_themGioHang);
        init();
        setSupportActionBar(toolbar);


        masp = getIntent().getIntExtra("masp", 0);
        presenterLogicChiTietSanPham = new PresenterLogicChiTietSanPham(this);
        presenterLogicChiTietSanPham.LayChiTietSanPham(masp);

        txt_VietDanhGia.setOnClickListener(this);
        imbtn_ThemGioHang.setOnClickListener(this);
    }


    private void init() {
        sharedPreferences = getSharedPreferences("dangnhap", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    @Override
    public void HienThiChiTietSanPham(final SanPham sanPham) {

        masp = sanPham.getMASP();
        Log.d("d", String.valueOf(masp));

        sanPhamGioHang = sanPham;
        sanPhamGioHang.setSoluongtonkho(sanPham.getSOLUONG());
        txt_tensanpham.setText(sanPham.getTENSANPHAM());
        NumberFormat numberFormat = new DecimalFormat("###,###");
        String gia = numberFormat.format(sanPham.getGIA());
        txt_giatien.setText(gia + "VNĐ");
        Picasso.get().load(sanPham.getANHLON()).into(imgAnhSP);

        txt_thongtinsanpham.setText(sanPham.getTHONGTIN().substring(0, 100));

        if (sanPham.getTHONGTIN().length() < 100) {
            img_xemthemchitiet.setVisibility(View.GONE);

        } else {
            img_xemthemchitiet.setVisibility(View.VISIBLE);

            img_xemthemchitiet.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    kiemtra = !kiemtra;
                    if (kiemtra) {

//mở chi tiết
                        txt_thongtinsanpham.setText(sanPham.getTHONGTIN());
                        img_xemthemchitiet.setImageDrawable(getHinhChiTiet(R.drawable.ic_keyboard_arrow_up_black_24dp));
                        id_thongsokithuat.setVisibility(View.VISIBLE);
                        hienthiThongSo(sanPham);
                    } else {
//                        đóng chi tiết
                        txt_thongtinsanpham.setText(sanPham.getTHONGTIN().substring(0, 100));
                        img_xemthemchitiet.setImageDrawable(getHinhChiTiet(R.drawable.ic_keyboard_arrow_down_black_24dp));
                        id_thongsokithuat.setVisibility(View.GONE);

                    }

                }
            });
        }
    }

    @Override
    public void ThemGioHangThanhCong() {
        Toast.makeText(this, "Sản phẩm được thêm vào giỏ hàng thành công", Toast.LENGTH_SHORT).show();

        txtGioHang.setText(String.valueOf(presenterLogicChiTietSanPham.DemSanPhamTrongGioHang(this)));

    }

    @Override
    public void ThemGioHangThatBai() {
        Toast.makeText(this, "Sản phẩm đã có trong của hàng!!!", Toast.LENGTH_SHORT).show();


    }

    private void hienthiThongSo(SanPham sanPham) {
        List<ChiTietSanPham> chiTietSanPhamList = sanPham.getChiTietSanPhams();

        id_thongsokithuat.removeAllViews();
        for (int i = 0; i < chiTietSanPhamList.size(); i++) {
            LinearLayout linearLayoutChiTiet = new LinearLayout(this);
            linearLayoutChiTiet.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            linearLayoutChiTiet.setOrientation(LinearLayout.HORIZONTAL);

            TextView txt_ThongSoKiThuat = new TextView(this);
            txt_ThongSoKiThuat.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1.0f));
            txt_ThongSoKiThuat.setTextColor(Color.BLACK);
            txt_ThongSoKiThuat.setText(chiTietSanPhamList.get(i).getTenChiTiet());

            TextView txt_GiaTri = new TextView(this);
            txt_GiaTri.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1.0f));
            txt_GiaTri.setText(chiTietSanPhamList.get(i).getGiaTri());


            linearLayoutChiTiet.addView(txt_ThongSoKiThuat);
            linearLayoutChiTiet.addView(txt_GiaTri);

            id_thongsokithuat.addView(linearLayoutChiTiet);

        }


    }

    private Drawable getHinhChiTiet(int idDrawable) {
        Drawable drawable;
        if (Build.VERSION.SDK_INT > 21) {
            drawable = ContextCompat.getDrawable(this, idDrawable);

        } else {
            drawable = ContextCompat.getDrawable(this, idDrawable);
        }
        return drawable;
    }

    public boolean onCreateOptionsMenu(Menu menu) {


        getMenuInflater().inflate(R.menu.menutrangchu, menu);

        MenuItem menuItem = menu.findItem(R.id.itGioHang);

        View ViewThongSoGioHang = MenuItemCompat.getActionView(menuItem);
        txtGioHang = ViewThongSoGioHang.findViewById(R.id.txt_soLuongSPGioHang);
        ViewThongSoGioHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChiTietSanPhamActivity.this, GioHangActivity.class);
                startActivity(intent);

            }
        });

        txtGioHang.setText(String.valueOf(presenterLogicChiTietSanPham.DemSanPhamTrongGioHang(this)));
        return true;
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.txt_VietDanhGia:
                String ten = sharedPreferences.getString("tennv","");
                if(!TextUtils.isEmpty(ten)){
                    Intent intentDanhgia = new Intent(this, DanhGiaActivity.class);
                    intentDanhgia.putExtra("masp", masp);
                    startActivity(intentDanhgia);
                }else {
                    Intent intentDanhgia = new Intent(this, FragmentDangNhap.class);
                    startActivity(intentDanhgia);
                }
                break;

            case R.id.imbtn_themGioHang:
                ImageView imageView = (ImageView) findViewById(R.id.imgAnhSP);
                Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();

//                imbtn_ThemGioHang.setImageBitmap(bitmap);

                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                byte[] hinhgiohang = byteArrayOutputStream.toByteArray();
                sanPhamGioHang.setHinhgiohang(hinhgiohang);
                sanPhamGioHang.setSOLUONG(1);

                presenterLogicChiTietSanPham.ThemGioHang(sanPhamGioHang, this);

                break;
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        // put your code here...
        if (onPause) {
            PresenterLogicChiTietSanPham presenterLogicChiTietSanPham = new PresenterLogicChiTietSanPham();

            txtGioHang.setText(String.valueOf(presenterLogicChiTietSanPham.DemSanPhamTrongGioHang(this)));
        }

    }

    // KHi người dùng chuyển trang onPause sẽ kích hoạt
    @Override
    protected void onPause() {
        super.onPause();

        onPause = true;
    }
}
