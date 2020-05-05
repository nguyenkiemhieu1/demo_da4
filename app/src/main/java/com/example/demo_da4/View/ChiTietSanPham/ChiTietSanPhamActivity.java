package com.example.demo_da4.View.ChiTietSanPham;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import com.example.demo_da4.Model.ObjectClass.ChiTietSanPham;
import com.example.demo_da4.Model.ObjectClass.SanPham;
import com.example.demo_da4.Presenter.ChiTietSanPham.PresenterLogicChiTietSanPham;
import com.example.demo_da4.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

public class ChiTietSanPhamActivity extends AppCompatActivity implements ViewChiTietSanPham {
    PresenterLogicChiTietSanPham presenterLogicChiTietSanPham;
    TextView txt_giatien, txt_tensanpham, txt_thongtinsanpham;
    ImageView imgAnhSP, img_xemthemchitiet;
    Toolbar toolbar;
    boolean kiemtra = false;
    LinearLayout id_thongsokithuat;

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
        setSupportActionBar(toolbar);



        int masp = getIntent().getIntExtra("masp", 0);
        presenterLogicChiTietSanPham = new PresenterLogicChiTietSanPham(this);
        presenterLogicChiTietSanPham.LayChiTietSanPham(masp);
    }


    @Override
    public void HienThiChiTietSanPham(final SanPham sanPham) {
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
    private  void hienthiThongSo(SanPham sanPham){
        List<ChiTietSanPham> chiTietSanPhamList  = sanPham.getChiTietSanPhams();

        id_thongsokithuat.removeAllViews();
        for(int i = 0; i < chiTietSanPhamList.size(); i++){
            LinearLayout linearLayoutChiTiet = new LinearLayout(this);
            linearLayoutChiTiet.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            linearLayoutChiTiet.setOrientation(LinearLayout.HORIZONTAL);

            TextView txt_ThongSoKiThuat  = new TextView(this);
            txt_ThongSoKiThuat.setLayoutParams( new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1.0f));
            txt_ThongSoKiThuat.setTextColor(Color.BLACK);
            txt_ThongSoKiThuat.setText(chiTietSanPhamList.get(i).getTenChiTiet());

            TextView txt_GiaTri  = new TextView(this);
            txt_GiaTri.setLayoutParams( new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1.0f));
            txt_GiaTri.setText(chiTietSanPhamList.get(i).getGiaTri());


            linearLayoutChiTiet.addView(txt_ThongSoKiThuat);
            linearLayoutChiTiet.addView(txt_GiaTri);

            id_thongsokithuat.addView(linearLayoutChiTiet);


        }


    }
    private Drawable getHinhChiTiet(int idDrawable)
    {
        Drawable drawable;
        if(Build.VERSION.SDK_INT > 21){
            drawable =  ContextCompat.getDrawable(this, idDrawable);

        }else {
            drawable =  ContextCompat.getDrawable(this, idDrawable);
        }
        return drawable;
    }
    public boolean onCreateOptionsMenu(final Menu menu) {
        getMenuInflater().inflate(R.menu.menutrangchu, menu);

        return true;
    }
}
