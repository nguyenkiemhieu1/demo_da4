package com.example.demo_da4.Adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Point;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.demo_da4.Model.ObjectClass.LoaiSanPham;
import com.example.demo_da4.Model.Trangchu.XuLyMenu.XuLyJSONMenu;
import com.example.demo_da4.R;

import java.util.List;

public class ExpandAdapter extends BaseExpandableListAdapter {

    Context context;
    List<LoaiSanPham> loaiSanPhams;

    ViewHorderMenu viewHorderMenu;

    public ExpandAdapter(Context context, List<LoaiSanPham> loaiSanPhams){
        this.context=context;
        this.loaiSanPhams=loaiSanPhams;


        XuLyJSONMenu xuLyJSONMenu = new XuLyJSONMenu();

        int count = loaiSanPhams.size();
        for (int i=0;i<count;i++){
            int maloaisp = loaiSanPhams.get(i).getMALOAISP();
            loaiSanPhams.get(i).setListcon(xuLyJSONMenu.LayLoaiSanPhamTheoMaLoai(maloaisp));

        }

    }
    @Override
    public int getGroupCount() {
        return loaiSanPhams.size();
    }

    @Override
    public int getChildrenCount(int vitriGroupCha) {
        if(loaiSanPhams.get(vitriGroupCha).getListcon().size()!=0){
            return 1;
        }else {
            return 0;
        }
    }
// lớp đầu tiên
    @Override
    public Object getGroup(int vitriGroupCha) {
        return loaiSanPhams.get(vitriGroupCha);
    }
// lớp thứ 2
    @Override
    public Object getChild(int vitriGroupCha, int vitriGroupCon) {
        return loaiSanPhams.get(vitriGroupCha).getListcon().get(vitriGroupCon);
    }

    @Override
    public long getGroupId(int vitriGroupCha) {
        return loaiSanPhams.get(vitriGroupCha).getMALOAISP();
    }

    @Override
    public long getChildId(int vitriGroupCha, int vitriGroupCon) {
        return loaiSanPhams.get(vitriGroupCha).getListcon().get(vitriGroupCon).getMALOAISP();
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }
    public class  ViewHorderMenu{
        TextView txtTenLoaiSp;
        ImageView hinhSp;
    }
//    isExpanded xổ xuống

    @Override
    public View getGroupView(final int vitriGroupCha, boolean isExpanded, View view, ViewGroup viewGroup) {
        View view1=view;
        if(view1==null){
            viewHorderMenu= new ViewHorderMenu();

            LayoutInflater layoutInflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view1= layoutInflater.inflate(R.layout.custom_layout_group_cha,viewGroup, false);
             viewHorderMenu.txtTenLoaiSp= (TextView) view1.findViewById(R.id.txtTenLoaiSP);
            viewHorderMenu.hinhSp=(ImageView)view1.findViewById(R.id.imgmenu);
            view1.setTag(viewHorderMenu);
        }else {
             viewHorderMenu=(ViewHorderMenu) view1.getTag();
        }





        viewHorderMenu.txtTenLoaiSp.setText(loaiSanPhams.get(vitriGroupCha).getTENLOAISP());
        int demSPcon=loaiSanPhams.get(vitriGroupCha).getListcon().size();

        if(demSPcon>0){
//            cho pheps hình ảnh hiển lên
            viewHorderMenu.hinhSp.setVisibility(View.VISIBLE);
        }else {
            viewHorderMenu.hinhSp.setVisibility(View.INVISIBLE);
        }

        if(isExpanded){
            viewHorderMenu.hinhSp.setImageResource(R.drawable.ic_remove_black_24dp);
            view1.setBackgroundResource(R.color.colorGray);
        }else {
            viewHorderMenu.hinhSp.setImageResource(R.drawable.ic_add_black_24dp);

        }

        view1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                Log.d("maloaisp",loaiSanPhams.get(vitriGroupCha).getTENLOAISP() +"_"+loaiSanPhams.get(vitriGroupCha).getMALOAISP());
                return false;
            }
        });
        return view1;
    }

    @Override
    public View getChildView(int vitriGroupCha, int vitriGroupCon, boolean isExpanded, View view, ViewGroup viewGroup) {
//        LayoutInflater layoutInflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        View viewGroupCon= layoutInflater.inflate(R.layout.custom_layout_group_con,viewGroup, false);
//        ExpandableListView expandableListView=(ExpandableListView) viewGroupCon.findViewById(R.id.epMenuCon);
        SecondExpandable expandable=new SecondExpandable(context);
        ExpandAdapter secondAdapter=new ExpandAdapter(context,loaiSanPhams.get(vitriGroupCha).getListcon());
        expandable.setAdapter(secondAdapter);

        expandable.setGroupIndicator(null);
        notifyDataSetChanged();

        return expandable;
    }
    public class SecondExpandable extends ExpandableListView{

         public SecondExpandable(Context context) {
            super(context);
        }
//xets chiều rộng chiều cao
        @Override
        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
             WindowManager windowManager= (WindowManager) context.getSystemService(context.WINDOW_SERVICE);
             Display display=windowManager.getDefaultDisplay();
             Point size=new Point();
             display.getSize(size);

             int width=size.x;
                     int height=size.y;
                     Log.d("size", width + "_ " +height);

//             widthMeasureSpec=MeasureSpec.makeMeasureSpec(width, MeasureSpec.AT_MOST);
             heightMeasureSpec=MeasureSpec.makeMeasureSpec(height, MeasureSpec.AT_MOST);
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }
//    public class  SecondAdapter extends  BaseExpandableListAdapter{
//        List<LoaiSanPham> listCon;
//        public  SecondAdapter (List<LoaiSanPham> listCon){
//            this.listCon=listCon;
//
//            XuLyJSONMenu xuLyJSONMenu = new XuLyJSONMenu();
//
//            int count = listCon.size();
//            for (int i=0;i<count;i++){
//                int maloaisp = listCon.get(i).getMALOAISP();
//                listCon.get(i).setListcon(xuLyJSONMenu.LayLoaiSanPhamTheoMaLoai(maloaisp));
//            }
//
//        }
//
//        @Override
//        public int getGroupCount() {
//            return listCon.size();
//        }
//
//        @Override
//        public int getChildrenCount(int vitriGroupCha) {
//            return listCon.get(vitriGroupCha).getListcon().size();
//        }
//        // lớp đầu tiên
//        @Override
//        public Object getGroup(int vitriGroupCha) {
//            return listCon.get(vitriGroupCha);
//        }
//        // lớp thứ 2
//        @Override
//        public Object getChild(int vitriGroupCha, int vitriGroupCon) {
//            return listCon.get(vitriGroupCha).getListcon().get(vitriGroupCon);
//        }
//
//        @Override
//        public long getGroupId(int vitriGroupCha) {
//            return listCon.get(vitriGroupCha).getMALOAISP();
//        }
//
//        @Override
//        public long getChildId(int vitriGroupCha, int vitriGroupCon) {
//            return listCon.get(vitriGroupCha).getListcon().get(vitriGroupCon).getMALOAISP();
//        }
//
//        @Override
//        public boolean hasStableIds() {
//            return false;
//        }
//
//        @Override
//        public View getGroupView(int vitriGroupCha, boolean isExpanded, View view, ViewGroup viewGroup) {
//            LayoutInflater layoutInflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            View view1= layoutInflater.inflate(R.layout.custom_layout_group_cha,viewGroup, false);
//            TextView txtTenLoaiSP= (TextView) view1.findViewById(R.id.txtTenLoaiSP);
//            txtTenLoaiSP.setText(listCon.get(vitriGroupCha).getTENLOAISP());
//
//            return view1;
//        }
//
//        @Override
//        public View getChildView(int vitriGroupCha, int vitriGroupCon, boolean isExpanded, View view, ViewGroup viewGroup) {
////            LayoutInflater layoutInflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
////            View view1= layoutInflater.inflate(R.layout.custom_layout_group_cha,viewGroup, false);
////            TextView txtTenLoaiSP= (TextView) view1.findViewById(R.id.txtTenLoaiSP);
////            txtTenLoaiSP.setText(listCon.get(vitriGroupCha).getListcon().get(vitriGroupCon).getTENLOAISP());
////            return view1;
//            TextView tv=new TextView(context);
//            tv.setText(listCon.get(vitriGroupCha).getListcon().get(vitriGroupCon).getTENLOAISP());
//            tv.setPadding(15,5,5,5);
//            tv.setLayoutParams(new ListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
//            return tv;
//        }
//
//
//        @Override
//        public boolean isChildSelectable(int i, int i1) {
//            return false;
//        }
//    }
}
