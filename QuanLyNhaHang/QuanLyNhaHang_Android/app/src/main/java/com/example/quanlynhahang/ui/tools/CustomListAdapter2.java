package com.example.quanlynhahang.ui.tools;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.quanlynhahang.R;

import java.util.List;

public class CustomListAdapter2 extends BaseAdapter {
    private List<ThucDon> listData;
    private LayoutInflater layoutInflater;
    private Context context;

    public CustomListAdapter2(Context aContext, List<ThucDon> listData)
    {
        this.context=aContext;
        this.listData=listData;
        layoutInflater=LayoutInflater.from(aContext);
    }

    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int i) {
        return listData.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ViewHolder holder;
        if(view==null)
        {
            view=layoutInflater.inflate(R.layout.danhsachthucdon,null);
            holder=new ViewHolder();
            holder.flagView=(ImageView)view.findViewById(R.id.imageView_flag);
            holder.countryNameView = (TextView)view.findViewById(R.id.textView_countryName);
            holder.populationView = (TextView)view.findViewById(R.id.textView_population);
            holder.soluongView=(TextView)view.findViewById(R.id.textView_soluong);
            holder.thanhtienView=(TextView)view.findViewById(R.id.textView_thanhtien);
            view.setTag(holder);
        }
        else
        {
            holder=(ViewHolder)view.getTag();
        }

        ThucDon thucDon=this.listData.get(i);
        holder.countryNameView.setText("Tên món: "+thucDon.getTenMon());
        holder.populationView.setText("Đơn giá: "+thucDon.getDonGia());
        holder.soluongView.setText("Số lượng: "+thucDon.getSoLuong());
        holder.thanhtienView.setText("Thành tiền: "+thucDon.getThanhTien());
        int imageld=this.getMipmapResIdByName(thucDon.getHinhAnh());
        holder.flagView.setImageResource(imageld);
        return  view;
    }

    private int getMipmapResIdByName(String resName) {
        String pkgName = context.getPackageName();
        int resID = context.getResources().getIdentifier(resName , "mipmap", pkgName);
        Log.i("CustomListView", "Res Name: "+ resName+"==> Res ID = "+ resID);
        return resID;
    }

    static class ViewHolder
    {
        ImageView flagView;
        TextView countryNameView;
        TextView populationView;
        TextView soluongView;
        TextView thanhtienView;
    }
}
