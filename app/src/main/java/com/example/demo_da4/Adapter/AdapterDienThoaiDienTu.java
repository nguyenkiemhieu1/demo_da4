package com.example.demo_da4.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demo_da4.Model.ObjectClass.SanPham;
import com.example.demo_da4.R;
import com.example.demo_da4.View.ChiTietSanPham.ChiTietSanPhamActivity;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

public class AdapterDienThoaiDienTu  extends RecyclerView.Adapter<AdapterDienThoaiDienTu.ViewHolderTopDienThoaiDienTu> {

    Context context;
     List<SanPham> sanPhamList;
     int layout;

    public AdapterDienThoaiDienTu(Context context,int layout, List<SanPham> sanPhamList) {
        this.context = context;
        this.sanPhamList = sanPhamList;
        this.layout = layout;
    }

    public class ViewHolderTopDienThoaiDienTu extends RecyclerView.ViewHolder {
        ImageView img_TopDTVaMTB;
        TextView txt_TieuDeTopDTVaMTB, txt_Gia_DienTu,txt_GiamGiaDienTu;
        ProgressBar progess_Bar_dowloadDtdt;
        CardView cardView;


        public ViewHolderTopDienThoaiDienTu(@NonNull View itemView) {
            super(itemView);
            img_TopDTVaMTB = (ImageView) itemView.findViewById(R.id.img_TopDTVaMTB);
            txt_TieuDeTopDTVaMTB =  (TextView) itemView.findViewById(R.id.txt_TieuDeTopDTVaMTB);
            txt_Gia_DienTu = (TextView)itemView.findViewById(R.id.txt_Gia_DienTu);
            txt_GiamGiaDienTu = (TextView) itemView.findViewById(R.id.txt_GiamGiaDienTu);
            progess_Bar_dowloadDtdt = (ProgressBar) itemView.findViewById(R.id.progess_Bar_dowloadDtdt);
            cardView = (CardView)itemView.findViewById(R.id.cardView);

        }
    }
    @NonNull
    @Override
    public AdapterDienThoaiDienTu.ViewHolderTopDienThoaiDienTu onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(layout, parent, false);
        ViewHolderTopDienThoaiDienTu viewHolderTopDienThoaiDienTu = new ViewHolderTopDienThoaiDienTu(view);
        return viewHolderTopDienThoaiDienTu;
    }

    @Override
    public void onBindViewHolder(@NonNull final AdapterDienThoaiDienTu.ViewHolderTopDienThoaiDienTu holder, int position) {
        SanPham sanPham = sanPhamList.get(position);
        Picasso.get().load(sanPham.getANHLON()).resize(140, 140).into(holder.img_TopDTVaMTB, new Callback() {
            @Override
            public void onSuccess() {
                holder.progess_Bar_dowloadDtdt.setVisibility(View.GONE);

            }

            @Override
            public void onError(Exception e) {

            }
        });

        NumberFormat numberFormat = new DecimalFormat("###,###");
        String gia = numberFormat.format(sanPham.getGIA());

        holder.txt_Gia_DienTu.setText(gia + " VNĐ");
        holder.txt_TieuDeTopDTVaMTB.setText(sanPham.getTENSANPHAM());

        holder.cardView.setTag(sanPham.getMASP());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ChiTietSanPhamActivity.class);
                intent .putExtra("masp", (int) view.getTag());
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return sanPhamList.size();
    }


}
