package com.example.demo_da4.View.DangNhap_DangKi;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.example.demo_da4.Adapter.ViewPagerAdapterDangNhap;
import com.example.demo_da4.R;
import com.google.android.material.tabs.TabLayout;

public class DangNhapActivity  extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;
    Toolbar toolbarDangNhap;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_dangnhap);

        tabLayout=(TabLayout) findViewById(R.id.tabDangNhap);
        viewPager=(ViewPager)findViewById(R.id.viewDangNhap);
        toolbarDangNhap=(Toolbar)findViewById(R.id.id_toolbarDangNhap);

        setSupportActionBar(toolbarDangNhap);

        ViewPagerAdapterDangNhap viewPagerAdapterDangNhap=new ViewPagerAdapterDangNhap(getSupportFragmentManager());
        viewPager.setAdapter(viewPagerAdapterDangNhap);
        viewPagerAdapterDangNhap.notifyDataSetChanged();

        tabLayout.setupWithViewPager(viewPager);


    }
}
