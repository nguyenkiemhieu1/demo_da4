package com.example.demo_da4.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demo_da4.Model.ObjectClass.ThuongHieu;
import com.example.demo_da4.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterThuongHieuLonDienTu extends RecyclerView.Adapter<AdapterThuongHieuLonDienTu.ViewHolderthuongHieuLon> {
    Context context;
    List<ThuongHieu> thuongHieus;

    public AdapterThuongHieuLonDienTu(Context context, List<ThuongHieu> thuongHieus) {
        this.context = context;
        this.thuongHieus = thuongHieus;
    }

    public class ViewHolderthuongHieuLon extends RecyclerView.ViewHolder {
        ImageView img_logoThuongHieuLon;

        public ViewHolderthuongHieuLon(@NonNull View itemView) {
            super(itemView);
            img_logoThuongHieuLon = (ImageView) itemView.findViewById(R.id.img_logoThuongHieuLon);
        }
    }

    @NonNull
    @Override
    public ViewHolderthuongHieuLon onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater  layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.custom_layout_recycle_topthuonghieulon_dientu, parent, false);

        ViewHolderthuongHieuLon viewHolderthuongHieuLon = new ViewHolderthuongHieuLon(view);

        return viewHolderthuongHieuLon;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderthuongHieuLon holder, int position) {
        ThuongHieu thuongHieu = thuongHieus.get(position);

        Picasso.get().load(thuongHieu.getHINHTHUONGHIEU()).resize(180,90  ).centerInside().into(holder.img_logoThuongHieuLon);

    }

    @Override
    public int getItemCount() {
        return thuongHieus.size();
    }




}
