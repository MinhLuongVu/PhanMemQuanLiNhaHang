package com.example.quanlynhahang.ui.gallery;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.quanlynhahang.R;

import java.util.List;

public class CustomListAdapter1 extends BaseAdapter {
    private List<LoaiMon> listData;
    private LayoutInflater layoutInflater;
    private Context context;

    public CustomListAdapter1(Context aContext, List<LoaiMon> listData)
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
    public Object getItem(int position) {
        return listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
      ViewHolder holder;
        if(convertView==null)
        {
            convertView=layoutInflater.inflate(R.layout.danhsachloaimonan,null);
            holder=new ViewHolder();
            holder.textView=(TextView) convertView.findViewById(R.id.textView);
            convertView.setTag(holder);
        }
        else
        {
            holder=(ViewHolder)convertView.getTag();
        }

        LoaiMon loaiMon=this.listData.get(position);
        holder.textView.setText(""+loaiMon.getTenLoaiMon());
        return  convertView;
    }
    static class ViewHolder
    {
        TextView textView;
    }
}
