package com.example.demo_da4.View.TrangChu;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import com.example.demo_da4.Adapter.ExpandAdapter;
import com.example.demo_da4.Adapter.ViewPagerAdapter;
import com.example.demo_da4.Model.DangNhap_DangKi.ModelDangNhap;
import com.example.demo_da4.Model.ObjectClass.LoaiSanPham;
import com.example.demo_da4.Presenter.ChiTietSanPham.PresenterLogicChiTietSanPham;
import com.example.demo_da4.Presenter.TrangChu.XuLyMenu.PresenterLogicXuLyMenu;
import com.example.demo_da4.R;
//import com.example.demo_da4.View.DangNhap_DangKi.DangNhapActivity;
import com.example.demo_da4.View.DangNhap_DangKi.Fragment.FragmentDangKi;
import com.example.demo_da4.View.DangNhap_DangKi.Fragment.FragmentDangNhap;
import com.example.demo_da4.View.GioHang.GioHangActivity;
import com.example.demo_da4.View.TimKiem.TimKiemActivity;
import com.google.android.material.tabs.TabLayout;

import java.util.List;


public class TrangChuActivity extends AppCompatActivity implements ViewXuLyMenu {

    public static  final  String  SERVER_NAME = "http://192.168.1.3/weblazada/loaisanpham.php";
    public static  final  String  SERVER = "http://192.168.1.3/weblazada";
    Toolbar toolbar;
    Button btn_timkiem;
    TabLayout tabLayout;
    TextView txtGioHang;
    ViewPager viewPager;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle drawerToggle;
    ExpandableListView expandableListView;
    ModelDangNhap modelDangNhap;
    MenuItem itemDangNhap,menuITDangXuat;
    Menu menu;
    boolean onPause = false;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trangchu_layout);

        toolbar=(Toolbar)findViewById(R.id.id_toolbar);
        tabLayout=(TabLayout)findViewById(R.id.tabs);
        viewPager=(ViewPager)findViewById(R.id.viewpager);
        drawerLayout=(DrawerLayout)findViewById(R.id.drawerlayout);
        expandableListView=findViewById(R.id.ebMenu);
        btn_timkiem = (Button) findViewById(R.id.btn_timkiem);
        btn_timkiem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentTimKiem =  new Intent(TrangChuActivity.this, TimKiemActivity.class);
                startActivity(intentTimKiem);

            }
        });

        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        drawerToggle= new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(drawerToggle);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        drawerToggle.syncState();

        ViewPagerAdapter adapter= new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        PresenterLogicXuLyMenu logicXuLyMenu= new PresenterLogicXuLyMenu(this);
        logicXuLyMenu.LayDanhSachMenu();
    }

    private void init(){
        sharedPreferences = getSharedPreferences("thongtintaikhoan", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {

        getMenuInflater().inflate(R.menu.menutrangchu, menu);
        this.menu = menu;

        MenuItem menuItem =this.menu.findItem(R.id.itGioHang);

        View ViewThongSoGioHang = MenuItemCompat.getActionView(menuItem);
        txtGioHang =(TextView) ViewThongSoGioHang.findViewById(R.id.txt_soLuongSPGioHang);

        ViewThongSoGioHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TrangChuActivity.this, GioHangActivity.class);
                startActivity(intent);

            }
        });
        PresenterLogicChiTietSanPham presenterLogicChiTietSanPham = new PresenterLogicChiTietSanPham();

        txtGioHang.setText(String.valueOf(presenterLogicChiTietSanPham.DemSanPhamTrongGioHang(this)));
        itemDangNhap = menu.findItem(R.id.itDangNhap);

//        String tennv = modelDangNhap.LayCachedDangNhap(this);
//        if(!tennv.equals("")){
//            itemDangNhap.setTitle(tennv);
//        }

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(drawerToggle.onOptionsItemSelected(item)){
            return true;
        }

        int id=item.getItemId();
        switch (id){
            case R.id.itDangNhap:
                Intent itDangNhap = new Intent(this, FragmentDangNhap.class);
                startActivity(itDangNhap);
                break;
            case R.id.itDangKi:
                Intent itDangKi = new Intent(this, FragmentDangKi.class);
                startActivity(itDangKi);
                break;
            case R.id.itSearch:
                Intent intentTimKiem =  new Intent(this, TimKiemActivity.class);
                startActivity(intentTimKiem);
                break;

        }
        return true;
    }

    @Override
    public void HienThiDanhSachMenu(List<LoaiSanPham> loaiSanPhamList) {
        ExpandAdapter expandAdapter= new ExpandAdapter(this, loaiSanPhamList);
        expandableListView.setAdapter(expandAdapter);
        expandAdapter.notifyDataSetChanged();
    }
    @Override
    public void onResume(){
        super.onResume();
        // put your code here...
        if(onPause){
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
