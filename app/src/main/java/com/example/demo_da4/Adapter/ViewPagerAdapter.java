package com.example.demo_da4.Adapter;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.demo_da4.View.TrangChu.Fragment.FragmentChuongTrinhKhuyenMai;
import com.example.demo_da4.View.TrangChu.Fragment.FragmentDienTu;
import com.example.demo_da4.View.TrangChu.Fragment.FragmentLamDep;
import com.example.demo_da4.View.TrangChu.Fragment.FragmentMevaBe;
import com.example.demo_da4.View.TrangChu.Fragment.FragmentNhaCuavaDoiSong;
import com.example.demo_da4.View.TrangChu.Fragment.FragmentNoiBat;
import com.example.demo_da4.View.TrangChu.Fragment.FragmentTheThaoVaDuLich;
import com.example.demo_da4.View.TrangChu.Fragment.FragmentThoiTrang;
import com.example.demo_da4.View.TrangChu.Fragment.FragmentThuongHieu;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends FragmentPagerAdapter {
    List<Fragment> listFragment= new ArrayList<Fragment>();
    List<String > titleFragment= new ArrayList<String>();

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
        listFragment.add(new FragmentNoiBat());
        listFragment.add(new FragmentChuongTrinhKhuyenMai());
        listFragment.add(new FragmentDienTu());
        listFragment.add(new FragmentNhaCuavaDoiSong());
        listFragment.add(new FragmentMevaBe());
        listFragment.add(new FragmentLamDep());
        listFragment.add(new FragmentThoiTrang());
        listFragment.add(new FragmentTheThaoVaDuLich());
        listFragment.add(new FragmentThuongHieu());

        titleFragment.add("Nổi bật");
        titleFragment.add("Chương trình khuyến mãi");
        titleFragment.add("Điện tử");
        titleFragment.add("Nhà của & đời sống");
        titleFragment.add("Mẹ & bé");
        titleFragment.add("Làm đẹp");
        titleFragment.add("Thời trang");
        titleFragment.add("Thể thao & du lịch");
        titleFragment.add("Thương hiệu");
    }

    @Override
    public Fragment getItem(int position) {
        return listFragment.get(position);
    }

    @Override
    public int getCount() {
        return listFragment.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titleFragment.get(position);
    }
}
