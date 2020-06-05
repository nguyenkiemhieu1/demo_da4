package com.example.demo_da4.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demo_da4.Model.ObjectClass.DanhGia;
import com.example.demo_da4.R;

import java.util.List;

public class AdapterDanhSachDanhGia extends RecyclerView.Adapter<AdapterDanhSachDanhGia.ViewHolderDanhGia> {
    List<DanhGia> danhGiaList;
    Context context;
    int limit;
    public AdapterDanhSachDanhGia(Context context,List<DanhGia> danhGiaList, int limit){

        this.context = context;
        this.danhGiaList = danhGiaList;
        this.limit = limit;
    }


    public class ViewHolderDanhGia extends RecyclerView.ViewHolder{
        TextView txt_TieuDeDanhGia, txt_DkDangBoi, txt_NoiDungDanhGia;
        public ViewHolderDanhGia(@NonNull View itemView) {
            super(itemView);
            txt_DkDangBoi = (TextView)itemView.findViewById(R.id.txt_TieuDeDanhGia);
            txt_NoiDungDanhGia = (TextView) itemView.findViewById(R.id.txt_NoiDungDanhGia);
            txt_TieuDeDanhGia = (TextView) itemView.findViewById(R.id.txt_TieuDeDanhGia);
        }
    }

    @NonNull
    @Override
    public AdapterDanhSachDanhGia.ViewHolderDanhGia onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.custom_layout_recycler_danhgia_chitiet, parent, false);

        ViewHolderDanhGia viewHolderDanhGia = new ViewHolderDanhGia(view);


        return viewHolderDanhGia;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterDanhSachDanhGia.ViewHolderDanhGia holder, int position) {
        DanhGia  danhGia = new DanhGia();
        holder.txt_TieuDeDanhGia.setText(danhGia.getTIEUDE());
        holder.txt_NoiDungDanhGia.setText(danhGia.getNOIDUNG());
        holder.txt_DkDangBoi.setText("Được đánh giá bởi: " + danhGia.getTENTHIETBI() + " ngày" +danhGia.getNGAYDANHGIA());


    }

    @Override
    public int getItemCount() {
        int current = 0;
        if(danhGiaList.size() < limit){
           current = danhGiaList.size();
        }else {
            if (limit != 0 ){
                current = limit;
            }else {
                current = danhGiaList.size();

            }
        }

        return current;
    }

}
