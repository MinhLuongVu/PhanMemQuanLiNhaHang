package com.example.quanlynhahang.ui.home;

import android.os.Bundle;
import android.os.Handler;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.fragment.NavHostFragment;

import com.example.quanlynhahang.R;
import com.example.quanlynhahang.ui.gallery.LoaiMon;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private  Ban ban;
    private   GridView gridView;
    private List<Ban> list=new ArrayList<Ban>();
    private ArrayList<String> dstenban=new ArrayList<String>();
    private ArrayList<String> dstrangthai=new ArrayList<String>();
    private ArrayList<String> dsmaban=new ArrayList<String>();
    private DanhSachBan danhSachBan=new DanhSachBan();
    private String[] HinhAnh=null;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        gridView=root.findViewById(R.id.list_item1);

        homeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                LoadDanhSachBan();
                registerForContextMenu(gridView);
            }
        });
        return root;
    }

    @Override
    public void onCreateContextMenu(@NonNull ContextMenu menu, @NonNull View v, @Nullable ContextMenu.ContextMenuInfo menuInfo) {
        getActivity().getMenuInflater().inflate(R.menu.danhsachban,menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo info=(AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
        int i=info.position;
        AdapterView.AdapterContextMenuInfo info1=(AdapterView.AdapterContextMenuInfo)item.getMenuInfo();

        switch (item.getItemId())
        {
            case R.id.add:
            {
                Bundle bundle =new  Bundle();
                bundle.putString("MaBan", gridView.getItemAtPosition(i).toString());
                NavHostFragment.findNavController(HomeFragment.this).navigate(R.id.nav_slideshow,bundle);
            }
                break;
            case R.id.exit:
            {
                Bundle bundle1 =new  Bundle();
                bundle1.putString("MaBan", gridView.getItemAtPosition(i).toString());
                NavHostFragment.findNavController(HomeFragment.this).navigate(R.id.nav_tools,bundle1);
            }
                break;
            case R.id.exit1:
            {
                Bundle bundle =new  Bundle();
                bundle.putString("MaBan", gridView.getItemAtPosition(i).toString());
                NavHostFragment.findNavController(HomeFragment.this).navigate(R.id.nav_share,bundle);
            }
            break;
        }
        return super.onContextItemSelected(item);
    }
    private void refesh(int i)
    {
        final Handler handler=new Handler();
        final  Runnable runnable=new Runnable() {
            @Override
            public void run() {
                LoadDanhSachBan();
            }
        };
        handler.postDelayed(runnable,i);
    }
    private void LoadDanhSachBan()
    {
        list.clear();
        dsmaban=danhSachBan.getMaBan();
        dstenban=danhSachBan.getTenBan();
        dstrangthai=danhSachBan.getTrangThai();
        if (getActivity()!=null)
        {
            for ( int i=0;i<dsmaban.size();i++)
            {
                HinhAnh=dstrangthai.get(i).toString().split("\\.png");
                ban=new Ban(dsmaban.get(i).toString(),dstenban.get(i).toString(),HinhAnh[0]);
                list.add(ban);
                CustomListAdapter customListAdapter=new CustomListAdapter(getActivity(),list);
                gridView.setAdapter(customListAdapter);
            }
            refesh(100000);
        }

    }

}