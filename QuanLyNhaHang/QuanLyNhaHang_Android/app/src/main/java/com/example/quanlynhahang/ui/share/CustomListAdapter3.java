package com.example.quanlynhahang.ui.share;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quanlynhahang.R;

import java.lang.reflect.Type;
import java.util.List;
public class CustomListAdapter3 extends BaseAdapter {
    private List<TrangThaiThucDon> listData;
    private LayoutInflater layoutInflater;
    private TrangThaiThucDon trangThaiThucDon;
    private Context context;

    public CustomListAdapter3(Context aContext, List<TrangThaiThucDon> listData)
    {
        this.context=aContext;
        this.listData=listData;
        layoutInflater=LayoutInflater.from(aContext);
    }


    public int getCount() {
        return listData.size();
    }

    public Object getItem(int i) {
        return listData.get(i);
    }

    public long getItemId(int i) {
        return i;
    }

    public View getView(final int i, View view, ViewGroup viewGroup) {

        final CustomListAdapter3.ViewHolder holder;
        if(view==null)
        {
            view=layoutInflater.inflate(R.layout.trangthaithucdon,null);
            holder=new CustomListAdapter3.ViewHolder();
            holder.textView = (TextView)view.findViewById(R.id.txtstt);
            holder.textView1 = (TextView)view.findViewById(R.id.txttenban);
            holder.textView2=(TextView)view.findViewById(R.id.txttenmon);
            holder.textView3=(TextView)view.findViewById(R.id.txtsoluong);
            holder.textView4=(TextView)view.findViewById(R.id.txttrangthai);
            view.setTag(holder);
        }
        else
        {
            holder=(CustomListAdapter3.ViewHolder)view.getTag();
        }

        final TrangThaiThucDon trangThaiThucDon=this.listData.get(i);
        holder.textView.setText(""+trangThaiThucDon.getSTT());
        holder.textView1.setText(""+trangThaiThucDon.getTenBan());
        holder.textView2.setText(""+trangThaiThucDon.getTenMon());
        holder.textView3.setText(""+trangThaiThucDon.getSoLuong());
        holder.textView4.setText(""+trangThaiThucDon.getTrangThai());
        if (trangThaiThucDon.getTrangThai().equals("Chưa làm"))
        {
            holder.textView4.setTextColor(Color.BLACK);
            holder.textView4.setTypeface(null, Typeface.BOLD);
        }
        else if (trangThaiThucDon.getTrangThai().equals("Đang làm"))
        {
            holder.textView4.setTextColor(Color.BLUE);
            holder.textView4.setTypeface(null, Typeface.BOLD);
        }
        else if (trangThaiThucDon.getTrangThai().equals("Làm xong"))
        {
            holder.textView4.setTextColor(Color.RED);
            holder.textView4.setTypeface(null, Typeface.BOLD);
        }
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
        TextView textView;
        TextView textView1;
        TextView textView2;
        TextView textView3;
        TextView textView4;
    }
}
