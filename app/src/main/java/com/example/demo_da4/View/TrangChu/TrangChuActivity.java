package com.example.demo_da4.View.TrangChu;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ExpandableListView;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import com.example.demo_da4.Adapter.ExpandAdapter;
import com.example.demo_da4.Adapter.ViewPagerAdapter;
import com.example.demo_da4.Model.DangNhap_DangKi.ModelDangNhap;
import com.example.demo_da4.Model.ObjectClass.LoaiSanPham;
import com.example.demo_da4.Presenter.TrangChu.XuLyMenu.PresenterLogicXuLyMenu;
import com.example.demo_da4.R;
import com.example.demo_da4.View.DangNhap_DangKi.DangNhapActivity;
import com.google.android.material.tabs.TabLayout;

import java.util.List;


public class TrangChuActivity extends AppCompatActivity implements ViewXuLyMenu {

    public static  final  String  SERVER_NAME = "http://192.168.43.214/weblazada/loaisanpham.php";
    Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager viewPager;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle drawerToggle;
    ExpandableListView expandableListView;
    ModelDangNhap modelDangNhap;
    MenuItem itemDangNhap,menuITDangXuat;
    Menu menu;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trangchu_layout);

        toolbar=(Toolbar)findViewById(R.id.id_toolbar);
        tabLayout=(TabLayout)findViewById(R.id.tabs);
        viewPager=(ViewPager)findViewById(R.id.viewpager);
        drawerLayout=(DrawerLayout)findViewById(R.id.drawerlayout);
        expandableListView=findViewById(R.id.ebMenu);


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

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {

        getMenuInflater().inflate(R.menu.menutrangchu, menu);
        this.menu = menu;
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
                Intent itDangNhap=new Intent(this, DangNhapActivity.class);
                startActivity(itDangNhap);
        }
        return true;
    }

    @Override
    public void HienThiDanhSachMenu(List<LoaiSanPham> loaiSanPhamList) {
        ExpandAdapter expandAdapter= new ExpandAdapter(this, loaiSanPhamList);
        expandableListView.setAdapter(expandAdapter);
        expandAdapter.notifyDataSetChanged();
    }
}
