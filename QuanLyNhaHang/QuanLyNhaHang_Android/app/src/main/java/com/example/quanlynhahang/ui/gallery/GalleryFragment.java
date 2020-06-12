package com.example.quanlynhahang.ui.gallery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.quanlynhahang.Database.ConnectionDB;
import com.example.quanlynhahang.R;

import java.sql.Connection;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;


public class GalleryFragment extends Fragment{

    private GalleryViewModel galleryViewModel;
    private ArrayList<String> dstenloaimon=new ArrayList<String>();
    private ArrayList<String> dsmaloaimon=new ArrayList<String>();
    private ArrayList<String> dstenmon=new ArrayList<String>();
    private ArrayList<String> dsdongia=new ArrayList<String>();
    private ArrayList<String> dshinhanh=new ArrayList<String>();
    private ArrayList<String> dstenmon1=new ArrayList<String>();
    private ArrayList<String> dsdongia1=new ArrayList<String>();
    private ArrayList<String> dshinhanh1=new ArrayList<String>();
    private String[] HinhAnh=null;
    private DanhSachMonAn danhSachMonAn=new DanhSachMonAn();
    private DanhSachLoaiMon danhSachLoaiMon=new DanhSachLoaiMon();
    private  LoaiMon loaiMon;
    private  List<LoaiMon> list1=new ArrayList<LoaiMon>();
    private MonAn monAn,monAn1;
    private  List<MonAn> list=new ArrayList<MonAn>();
    private  List<MonAn> list2=new ArrayList<MonAn>();
    private static  Spinner spinner;
    private  String MaLoaiMon=null;
    private ListView lv;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =  ViewModelProviders.of(this).get(GalleryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);

        lv=root.findViewById(R.id.list_item1);
        final TextView textView=root.findViewById(R.id.txtdanhsachloaimon);
        spinner=root.findViewById(R.id.danhsachloaimon);
        dstenmon=danhSachMonAn.getTenMon();
        dsdongia=danhSachMonAn.getdongia();
        dshinhanh=danhSachMonAn.getHinhAnh();
        dstenloaimon=danhSachLoaiMon.getTenLoaiMon();
        dsmaloaimon=danhSachLoaiMon.getMaLoaiMon();

        galleryViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

                for ( int i=0;i<dstenmon.size();i++)
                {
                    HinhAnh=dshinhanh.get(i).split("\\.jpg");
                    monAn=new MonAn(dstenmon.get(i),HinhAnh[0],TienTe(dsdongia.get(i))+" VNĐ",null);
                    list.add(monAn);
                }
                lv.setAdapter(new CustomListAdapter(getActivity(),list));

                for (int i=0;i<dsmaloaimon.size();i++)
                {
                    loaiMon=new LoaiMon(dsmaloaimon.get(i),dstenloaimon.get(i));
                    list1.add(loaiMon);
                }
                spinner.setAdapter(new CustomListAdapter1(getActivity(),list1));

                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    int Count=0;
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        if (Count>0)
                        {
                            list2.clear();
                            MaLoaiMon=spinner.getItemAtPosition(position).toString();
                            dstenmon1=danhSachMonAn.getTenMon1(MaLoaiMon);
                            dsdongia1=danhSachMonAn.getdongia1(MaLoaiMon);
                            dshinhanh1=danhSachMonAn.getHinhAnh1(MaLoaiMon);
                            for ( int i=0;i<dstenmon1.size();i++)
                            {
                                HinhAnh=dshinhanh1.get(i).toString().split("\\.jpg");
                                monAn1=new MonAn(dstenmon1.get(i).toString(),HinhAnh[0],TienTe(dsdongia1.get(i).toString())+" VNĐ",null);
                                list2.add(monAn1);
                            }
                            lv.setAdapter(new CustomListAdapter(getActivity(),list2));
                        }
                        Count++;
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
            }
        }
        );
        return root;
    }
    private static String TienTe(String Tien)
    {
        DecimalFormat format=new DecimalFormat("###,###,##0");
        return format.format(Double.parseDouble(Tien));
    }
}