package com.example.demo_da4.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demo_da4.Model.ObjectClass.DienTu;
import com.example.demo_da4.R;
import com.example.demo_da4.View.DangNhap_DangKi.ViewDangKi;
import com.example.demo_da4.View.TrangChu.ViewDienTu;

import java.util.List;

public class AdapterDienTu extends RecyclerView.Adapter<AdapterDienTu.ViewHolder> {
    Context context;
    List<DienTu> dienTuList;

    public AdapterDienTu(Context context, List<DienTu> dienTuList) {
        this.context = context;
        this.dienTuList = dienTuList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imgKhuyenMaiDienTu;
        RecyclerView recyclerViewThuongHieuLon, recyclerViewTopSanPham;
        TextView txtTenSanPhamNoiBat, txtTenTopSanPhamNoiBat;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgKhuyenMaiDienTu = (ImageView) itemView.findViewById(R.id.imgKhuyenMaiDienTu);
            recyclerViewThuongHieuLon = (RecyclerView) itemView.findViewById(R.id.recycler_ThuongHieuLon);
            recyclerViewTopSanPham=(RecyclerView)itemView.findViewById(R.id.recycler_TopDienThoaiVaMayTinhBang);
            txtTenSanPhamNoiBat = (TextView) itemView.findViewById(R.id.txtTenSanPhamNoiBat);
            txtTenTopSanPhamNoiBat = (TextView)itemView.findViewById(R.id.txtTenTopSanPhamNoiBat);

        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=layoutInflater.inflate(R.layout.custom_layout_recycleview_dientu, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DienTu dienTu = dienTuList.get(position);

        holder.txtTenSanPhamNoiBat.setText(dienTu.getTennoibat().toString());
        holder.txtTenTopSanPhamNoiBat.setText(dienTu.getTentopnoibat().toString());


        // xử lý hiển thị dánh sách thương hiệu lớn (recyclerView Thương Hiệu Lớn)
        AdapterThuongHieuLon adapterThuongHieuLon = new AdapterThuongHieuLon(context , dienTu.getThuongHieus(), dienTu.isThuonghieu());

        RecyclerView.LayoutManager layoutManager =  new GridLayoutManager(context, 3, GridLayoutManager.HORIZONTAL, false);

        holder.recyclerViewThuongHieuLon.setLayoutManager(layoutManager);

        holder.recyclerViewThuongHieuLon.setAdapter( adapterThuongHieuLon);
        adapterThuongHieuLon.notifyDataSetChanged();

        // xử lý hiển thị danh sách top sp(recyclerView Top SP)
        AdapterDienThoaiDienTu dienThoaiDienTu = new AdapterDienThoaiDienTu(context, R.layout.custom_layout_grid,dienTu.getSamPhams());

        RecyclerView.LayoutManager layoutManager1 = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);

        holder.recyclerViewTopSanPham.setLayoutManager(layoutManager1);

         holder.recyclerViewTopSanPham.setAdapter(dienThoaiDienTu);




    }

    @Override
    public int getItemCount() {
        return dienTuList.size();
    }


}
