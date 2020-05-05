package com.example.demo_da4.Adapter;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.demo_da4.View.DangNhap_DangKi.Fragment.FragmentDangKi;
import com.example.demo_da4.View.DangNhap_DangKi.Fragment.FragmentDangNhap;

public class ViewPagerAdapterDangNhap extends FragmentPagerAdapter {
    public ViewPagerAdapterDangNhap(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                FragmentDangNhap fragmentDangNhap=new FragmentDangNhap();
                return fragmentDangNhap;
            case 1:
                FragmentDangKi fragmentDangKi=new FragmentDangKi();
                return fragmentDangKi;

                default: return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Đăng Nhập";
            case 1:
                return "Đăng Kí";

            default:
                return null;
        }
    }
}
