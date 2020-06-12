package com.example.quanlynhahang.ui.gallery;

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

public class CustomListAdapter extends BaseAdapter {

    private List<MonAn> listData;
    private LayoutInflater layoutInflater;
    private Context context;

    public CustomListAdapter(Context aContext, List<MonAn> listData)
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
            view=layoutInflater.inflate(R.layout.danhsachmonan,null);
            holder=new ViewHolder();
            holder.flagView=(ImageView)view.findViewById(R.id.imageView_flag);
            holder.countryNameView = (TextView)view.findViewById(R.id.textView_countryName);
            holder.populationView = (TextView)view.findViewById(R.id.textView_population);
            view.setTag(holder);
        }
        else
        {
            holder=(ViewHolder)view.getTag();
        }

        MonAn monAn=this.listData.get(i);
        holder.countryNameView.setText(""+monAn.getTenMon());
        holder.populationView.setText(""+monAn.getDonGia());
        int imageld=this.getMipmapResIdByName(monAn.getHinhAnh());
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
    }
}
