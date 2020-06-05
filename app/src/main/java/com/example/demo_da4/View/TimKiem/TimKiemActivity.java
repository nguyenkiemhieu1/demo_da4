package com.example.demo_da4.View.TimKiem;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demo_da4.Adapter.AdapterDienThoaiDienTu;
import com.example.demo_da4.Model.ObjectClass.SanPham;
import com.example.demo_da4.Presenter.TimKiem.PresenterLogicTimKiem;
import com.example.demo_da4.R;

import java.util.List;

public class TimKiemActivity extends AppCompatActivity implements ViewTimKiem , SearchView.OnQueryTextListener {
    Toolbar toolbar;
    RecyclerView recyclerView;
    PresenterLogicTimKiem presenterLogicTimKiem ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_timkiem);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        recyclerView =(RecyclerView) findViewById(R.id.recycler_TimKiem);
        presenterLogicTimKiem =new PresenterLogicTimKiem(this);

        setSupportActionBar(toolbar);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater() .inflate(R.menu.menutimkiem, menu);

        MenuItem itSearch = menu.findItem(R.id.itSearch);
        SearchView searchView =(SearchView) MenuItemCompat.getActionView(itSearch);
        searchView.setIconified(false);
        searchView.setOnQueryTextListener(this);
        return true;
    }

    @Override
    public void ThanhCong(List<SanPham> sanPhamList) {

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        AdapterDienThoaiDienTu adapterDienThoaiDienTu = new AdapterDienThoaiDienTu(this, R.layout.custom_layout_list, sanPhamList);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapterDienThoaiDienTu);
        adapterDienThoaiDienTu.notifyDataSetChanged();
    }

    @Override
    public void Thatbai() {

    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        presenterLogicTimKiem.TimKiemSanPhamTheoTenSanPham(s, 0 );

        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        return false;
    }
}
