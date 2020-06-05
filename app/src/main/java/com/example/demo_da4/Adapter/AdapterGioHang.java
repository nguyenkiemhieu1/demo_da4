package com.example.demo_da4.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demo_da4.Model.GioHang.ModelGioHang;
import com.example.demo_da4.Model.ObjectClass.SanPham;
import com.example.demo_da4.R;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

public class AdapterGioHang extends RecyclerView.Adapter<AdapterGioHang.ViewHolderGioHang> {
    Context context;
    List<SanPham> sanPhamList;
    ModelGioHang modelGioHang;

    public AdapterGioHang(Context context, List<SanPham> sanPhamList){
        this.context = context;
        this.sanPhamList = sanPhamList;
        modelGioHang = new ModelGioHang();
        modelGioHang.DatabaseHandler(context);

    }

    public class ViewHolderGioHang extends RecyclerView.ViewHolder{
        TextView txtTenTieuDeGioHang, txtGiaTienGioHang, txt_SLSanpham;
        ImageView img_HinhGioHang, img_XoaSanPham;
        ImageButton imb_GiamSLSanPham, imb_TangSLSanPham;

        public ViewHolderGioHang(@NonNull View itemView) {
            super(itemView);
            txtGiaTienGioHang  = (TextView) itemView.findViewById(R.id.txt_GiaGioHang);
            txtTenTieuDeGioHang = (TextView) itemView.findViewById(R.id.txtTieuDeGioHang);
            img_HinhGioHang = (ImageView) itemView.findViewById(R.id.img_HinhGioHang);
            img_XoaSanPham = (ImageView) itemView.findViewById(R.id.img_XoaSanPham);

            txt_SLSanpham = (TextView)  itemView.findViewById(R.id.txt_SLSanPham);
            imb_GiamSLSanPham = (ImageButton) itemView.findViewById(R.id.imb_GiamSLSanPham);
            imb_TangSLSanPham = (ImageButton) itemView.findViewById(R.id.imb_TangSLSanPham);

        }
    }
    @NonNull
    @Override
    public ViewHolderGioHang onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater =(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.custom_danhsach_giohang, parent, false );
        ViewHolderGioHang viewHolderGioHang = new ViewHolderGioHang(view);

        return viewHolderGioHang;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolderGioHang holder, final int position) {
        final SanPham sanPham = sanPhamList.get(position);

        holder.txtTenTieuDeGioHang.setText(sanPham.getTENSANPHAM());
        NumberFormat numberFormat = new DecimalFormat("###,###");
        String gia = numberFormat.format(sanPham.getGIA());

        byte[] hinhsanpham = sanPham.getHinhgiohang();
        Bitmap bitmapGioHang = BitmapFactory.decodeByteArray(sanPham.getHinhgiohang(), 0,hinhsanpham.length );
        holder.txtGiaTienGioHang.setText(gia + " VNĐ");
        holder.img_HinhGioHang.setImageBitmap(bitmapGioHang);

        holder.img_XoaSanPham.setTag(sanPham.getMASP());

        holder.img_XoaSanPham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ModelGioHang modelGioHang = new ModelGioHang();
                modelGioHang.DatabaseHandler(context);
                modelGioHang.XoaSanphamTrongGioHang((int)view.getTag());
                sanPhamList.remove(position);
                notifyDataSetChanged();
            }
        });

        holder.txt_SLSanpham.setText(String.valueOf(sanPham.getSOLUONG()));

        holder.imb_TangSLSanPham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int soluong = Integer.parseInt(holder.txt_SLSanpham.getText().toString());
                int soluongtonkho = sanPham.getSoluongtonkho();
                if(soluong < soluongtonkho){
                    soluong++ ;
                }else {

//Adapter là context
                   Toast.makeText(context, "Số lượng sản phẩm đã hết trong cửa hàng !!!" , Toast.LENGTH_LONG).show();
                }

                modelGioHang.CapNhatSoLuongGioHang(sanPham.getMASP(), soluong);

                holder.txt_SLSanpham.setText(String.valueOf(soluong));
            }
        });
        holder.imb_GiamSLSanPham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int soluong = Integer.parseInt(holder.txt_SLSanpham.getText().toString());

                if(soluong > 1){
                    soluong--;
                    modelGioHang.CapNhatSoLuongGioHang(sanPham.getMASP(), soluong);
                }
                holder.txt_SLSanpham.setText(String.valueOf(soluong));
            }
        });
    }

    @Override
    public int getItemCount() {
        return sanPhamList.size();
    }

}
