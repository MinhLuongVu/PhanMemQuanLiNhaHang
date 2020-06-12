package com.example.quanlynhahang.ui.share;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.quanlynhahang.R;
import com.example.quanlynhahang.ui.gallery.CustomListAdapter;
import com.example.quanlynhahang.ui.gallery.CustomListAdapter1;
import com.example.quanlynhahang.ui.gallery.DanhSachMonAn;
import com.example.quanlynhahang.ui.gallery.MonAn;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class ShareFragment extends Fragment {

    private List<TrangThaiThucDon> list1 = new ArrayList<TrangThaiThucDon>();
    private List<MonAn> list11 = new ArrayList<MonAn>();
    private ShareViewModel shareViewModel;
    private ArrayList<String> dsmatrangthai = new ArrayList<String>();
    private ArrayList<String> dstenmon = new ArrayList<String>();
    private ArrayList<String> dstenban = new ArrayList<String>();
    private ArrayList<String> dstrangthai = new ArrayList<String>();
    private ArrayList<String> dssoluong = new ArrayList<String>();
    private TrangThaiThucDon trangThaiThucDon;
    private ListView lv;
    private String MaBan;
    private Bundle bundle;
    private DanhSachTrangThaiThucDon danhSachTrangThaiThucDon = new DanhSachTrangThaiThucDon();
    private CustomListAdapter3 customListAdapter3;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        shareViewModel =
                ViewModelProviders.of(this).get(ShareViewModel.class);
        View root = inflater.inflate(R.layout.fragment_share, container, false);
        lv = root.findViewById(R.id.list_item1);
        View root1 = inflater.inflate(R.layout.trangthaithucdon, container, false);
        shareViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                bundle=getArguments();
                LoadTrangThaiThucDon();
            }
        });
        return root;
    }

    @Override
    public void onCreateContextMenu(@NonNull ContextMenu menu, @NonNull View v, @Nullable ContextMenu.ContextMenuInfo menuInfo) {
        getActivity().getMenuInflater().inflate(R.menu.trangthaithucdon, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        final AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        final int i = info.position;
        AdapterView.AdapterContextMenuInfo info1 = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        switch (item.getItemId()) {
            case R.id.add: {
                final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                LayoutInflater layoutInflater = getActivity().getLayoutInflater();
                View view = layoutInflater.inflate(R.layout.capnhattrangthaithucdon, null);

                final RadioButton radioButtonchualam = view.findViewById(R.id.radiochualam);
                final RadioButton radioButtondanglam = view.findViewById(R.id.radiodanglam);
                final RadioButton radioButtonlamxong = view.findViewById(R.id.radiolamxong);
                if (dstrangthai.get(i).equals("Chưa làm")) {
                    radioButtonchualam.setChecked(true);
                } else if (dstrangthai.get(i).equals("Đang làm")) {
                    radioButtondanglam.setChecked(true);
                } else if (dstrangthai.get(i).equals("Làm xong")) {
                    radioButtondanglam.setChecked(true);
                }
                builder.setView(view)
                        .setTitle("Thay đổi")
                        .setPositiveButton("Thay đổi", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(getActivity(), "a", Toast.LENGTH_LONG).show();
                                String MaTrangThai = dsmatrangthai.get(i);
                                    if (radioButtonchualam.isChecked()==true)
                                    {
                                        list1.clear();
                                        danhSachTrangThaiThucDon.UpdateTrangThaiThucDon(MaTrangThai,"Chưa làm");
                                        Toast.makeText(getActivity(),"Bạn đã cập nhật thành công!",Toast.LENGTH_LONG).show();
                                        customListAdapter3.notifyDataSetChanged();
                                        LoadTrangThaiThucDon();
                                    }
                                    else if (radioButtondanglam.isChecked()==true)
                                    {
                                        list1.clear();
                                        danhSachTrangThaiThucDon.UpdateTrangThaiThucDon(MaTrangThai,"Đang làm");
                                        Toast.makeText(getActivity(),"Bạn đã cập nhật thành công!",Toast.LENGTH_LONG).show();
                                        customListAdapter3.notifyDataSetChanged();
                                        LoadTrangThaiThucDon();
                                    }
                                    else if (radioButtonlamxong.isChecked()==true)
                                    {
                                        list1.clear();
                                        danhSachTrangThaiThucDon.UpdateTrangThaiThucDon(MaTrangThai,"Làm xong");
                                        Toast.makeText(getActivity(),"Bạn đã cập nhật thành công!",Toast.LENGTH_LONG).show();
                                        customListAdapter3.notifyDataSetChanged();
                                        LoadTrangThaiThucDon();
                                    }
                            }
                        })
                        .setNegativeButton("Quay lại", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();

            }
            break;
            case R.id.exit:

                break;
        }
        return super.onContextItemSelected(item);
    }

    private void LoadTrangThaiThucDon() {
        if (bundle!=null) {
            MaBan = bundle.get("MaBan").toString();
        }
        list1.clear();
        dsmatrangthai = danhSachTrangThaiThucDon.getMaTrangThai(MaBan);
        dssoluong = danhSachTrangThaiThucDon.getSoLuong(MaBan);
        dstenban = danhSachTrangThaiThucDon.getTenBan(MaBan);
        dstenmon = danhSachTrangThaiThucDon.getTenMon(MaBan);
        dstrangthai = danhSachTrangThaiThucDon.getTrangThai(MaBan);
        for (int i = 0; i < dsmatrangthai.size(); i++) {

            trangThaiThucDon = new TrangThaiThucDon(dsmatrangthai.get(i), dstenban.get(i), dstenmon.get(i), dssoluong.get(i), dstrangthai.get(i), (i + 1));
            list1.add(trangThaiThucDon);
        }
        if (getActivity()!=null)
        {
            customListAdapter3 = new CustomListAdapter3(getActivity(), list1);
            lv.setAdapter(customListAdapter3);
            registerForContextMenu(lv);
            refesh(5000);
        }

    }
    private void refesh(int i)
    {
        final Handler handler=new Handler();
        final  Runnable runnable=new Runnable() {
            @Override
            public void run() {
                LoadTrangThaiThucDon();
            }
        };
        handler.postDelayed(runnable,i);
    }
}