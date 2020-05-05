package com.example.demo_da4.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demo_da4.Model.ObjectClass.ThuongHieu;
import com.example.demo_da4.R;
import com.example.demo_da4.View.HienThiSanPhamTheoDanhMuc.HienThiSanPhamTheoDanhMucActivity;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterThuongHieuLon extends RecyclerView.Adapter<AdapterThuongHieuLon.ViewHolder> {
    Context context;
    List<ThuongHieu> hieuList;
    boolean kiemtra;

    public AdapterThuongHieuLon(Context context, List<ThuongHieu> hieuList, boolean kiemtra) {
        this.context = context;
        this.hieuList = hieuList;
        this.kiemtra = kiemtra;
    }


    public class ViewHolder extends  RecyclerView.ViewHolder{
        TextView txt_TieuDeThuongHieuLon;
        ImageView img_HinhDienTu;

        ProgressBar progressBar;
        LinearLayout layout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_TieuDeThuongHieuLon = (TextView) itemView.findViewById(R.id.txt_TieuDeThuongHieuLon);
            img_HinhDienTu = (ImageView) itemView.findViewById(R.id.img_HinhDienTu);
            progressBar = (ProgressBar) itemView.findViewById(R.id.progess_Bar_dowload);
            layout = (LinearLayout) itemView.findViewById(R.id.lnthuongthieulon);

        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater =(LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.custom_layout_recycleview_thl, parent, false);
        ViewHolder  viewHolder =  new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        final ThuongHieu thuongHieu = hieuList.get(position);

        holder.txt_TieuDeThuongHieuLon.setText(thuongHieu.getTENTHUONGHIEU());


        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentsanphamTheoDanhMuc = new Intent(context, HienThiSanPhamTheoDanhMucActivity.class);
                intentsanphamTheoDanhMuc.putExtra("MASP", thuongHieu.getMATHUONGHIEU());
                intentsanphamTheoDanhMuc.putExtra("TENLOAI", thuongHieu.getTENTHUONGHIEU());
                intentsanphamTheoDanhMuc.putExtra("KIEMTRA", kiemtra);
                context.startActivity(intentsanphamTheoDanhMuc);
                Log.d("click",thuongHieu.getMATHUONGHIEU() +  "  -  "+ thuongHieu.getTENTHUONGHIEU());
            }
        });


        Picasso.get().load(thuongHieu.getHINHTHUONGHIEU()).resize(120,120).into(holder.img_HinhDienTu, new Callback() {
            @Override
            public void onSuccess() {
                holder.progressBar.setVisibility(View.GONE);

            }

            @Override
            public void onError(Exception e) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return hieuList.size();
    }
}
