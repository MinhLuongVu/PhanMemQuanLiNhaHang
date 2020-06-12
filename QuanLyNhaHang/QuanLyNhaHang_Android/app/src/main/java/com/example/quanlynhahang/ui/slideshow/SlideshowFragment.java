package com.example.quanlynhahang.ui.slideshow;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.fragment.NavHostFragment;

import com.example.quanlynhahang.R;
import com.example.quanlynhahang.ui.gallery.CustomListAdapter;
import com.example.quanlynhahang.ui.gallery.CustomListAdapter1;
import com.example.quanlynhahang.ui.gallery.DanhSachLoaiMon;
import com.example.quanlynhahang.ui.gallery.DanhSachMonAn;
import com.example.quanlynhahang.ui.gallery.LoaiMon;
import com.example.quanlynhahang.ui.gallery.MonAn;
import com.example.quanlynhahang.ui.home.HomeFragment;
import com.google.android.material.textfield.TextInputEditText;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class SlideshowFragment extends Fragment {

    private SlideshowViewModel slideshowViewModel;
    private ArrayList<String> dstenloaimon = new ArrayList<String>();
    private ArrayList<String> dsmaloaimon = new ArrayList<String>();
    private ArrayList<String> dsmamon1 = new ArrayList<String>();
    private ArrayList<String> dstenmon1 = new ArrayList<String>();
    private ArrayList<String> dsdongia1 = new ArrayList<String>();
    private ArrayList<String> dshinhanh1 = new ArrayList<String>();
    private ArrayList<String> dsgoimon = new ArrayList<String>();
    private String[] HinhAnh = null;
    private DanhSachMonAn danhSachMonAn = new DanhSachMonAn();
    private DanhSachLoaiMon danhSachLoaiMon = new DanhSachLoaiMon();
    private GoiMon goiMon = new GoiMon();
    private LoaiMon loaiMon;
    private List<LoaiMon> list1 = new ArrayList<LoaiMon>();
    private MonAn monAn1;
    private List<MonAn> list = new ArrayList<MonAn>();
    private List<MonAn> list2 = new ArrayList<MonAn>();
    private static Spinner spinner;
    private String MaLoaiMon = null, MaMon = null, SoLuong, DonGia;
    private ListView lv;
    private TextView textView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        slideshowViewModel =
                ViewModelProviders.of(this).get(SlideshowViewModel.class);
        View root = inflater.inflate(R.layout.fragment_slideshow, container, false);
        lv = root.findViewById(R.id.list_item1);
        final TextView textView = root.findViewById(R.id.txtdanhsachloaimon);
        final TextView textView1 = root.findViewById(R.id.txtdanhsachmonan);
        final LinearLayout L1 = root.findViewById(R.id.L1);
        final LinearLayout L2 = root.findViewById(R.id.L2);

        spinner = root.findViewById(R.id.danhsachloaimon);
        final Bundle bundle = getArguments();
        if (bundle != null) {
            dstenloaimon = danhSachLoaiMon.getTenLoaiMon();
            dsmaloaimon = danhSachLoaiMon.getMaLoaiMon();
            textView.setVisibility(View.VISIBLE);
            textView1.setVisibility(View.VISIBLE);
            spinner.setVisibility(View.VISIBLE);
            L1.setVisibility(View.VISIBLE);
            L2.setVisibility(View.VISIBLE);
        } else {
            L2.setVisibility(View.INVISIBLE);
            L1.setVisibility(View.INVISIBLE);
            textView.setVisibility(View.INVISIBLE);
            textView1.setVisibility(View.INVISIBLE);
            spinner.setVisibility(View.INVISIBLE);

        }

        slideshowViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                for (int i = 0; i < dsmaloaimon.size(); i++) {
                    loaiMon = new LoaiMon(dsmaloaimon.get(i), dstenloaimon.get(i));
                    list1.add(loaiMon);
                }
                spinner.setAdapter(new CustomListAdapter1(getActivity(), list1));

                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    int Count = 0;

                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        list2.clear();
                        MaLoaiMon = spinner.getItemAtPosition(position).toString();
                        dsmamon1 = danhSachMonAn.getMaMon1(MaLoaiMon);
                        dstenmon1 = danhSachMonAn.getTenMon1(MaLoaiMon);
                        dsdongia1 = danhSachMonAn.getdongia1(MaLoaiMon);
                        dshinhanh1 = danhSachMonAn.getHinhAnh1(MaLoaiMon);
                        for (int i = 0; i < dstenmon1.size(); i++) {
                            HinhAnh = dshinhanh1.get(i).split("\\.jpg");
                            monAn1 = new MonAn(dstenmon1.get(i), HinhAnh[0], TienTe(dsdongia1.get(i)) + " VNĐ", dsmamon1.get(i));
                            list2.add(monAn1);
                        }
                        lv.setAdapter(new CustomListAdapter(getActivity(), list2));
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        MaMon = dsmamon1.get(position);
                        DonGia = dsdongia1.get(position);
                    }
                });
                registerForContextMenu(lv);


            }
        });
        return root;
    }

    @Override
    public void onCreateContextMenu(@NonNull ContextMenu menu, @NonNull View v, @Nullable ContextMenu.ContextMenuInfo menuInfo) {
        getActivity().getMenuInflater().inflate(R.menu.themmon, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int i = info.position;
        AdapterView.AdapterContextMenuInfo info1 = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        switch (item.getItemId()) {
            case R.id.add: {
                final Bundle bundle1 = getArguments();
                if (bundle1 != null) {
                    final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    LayoutInflater layoutInflater = getActivity().getLayoutInflater();
                    View view = layoutInflater.inflate(R.layout.themmon, null);

                    final TextInputEditText editText = view.findViewById(R.id.soluong);
                    builder.setView(view)
                            .setTitle("Order")
                            .setPositiveButton("Thêm món", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    String MaBan = bundle1.get("MaBan").toString();
                                    String SoLuong = editText.getText().toString();
                                    if (MaBan != null) {
                                        if (MaLoaiMon != null) {
                                            if (MaMon != null) {
                                                if (Integer.parseInt(SoLuong) > 0) {
                                                    dsgoimon = goiMon.getDanhSachGoiMon(MaBan, MaMon);
                                                    if (dsgoimon.size() <= 0) {
                                                        goiMon.AddGoiMon(MaBan, MaMon, DonGia, SoLuong);
                                                        goiMon.AddGoiMon1(MaBan,MaMon,SoLuong,"Chưa làm");
                                                        goiMon.TrangThaiBan(MaBan);
                                                        MaMon = null;
                                                        Toast.makeText(getActivity(), "Bạn đã order thành công!", Toast.LENGTH_LONG).show();
                                                    } else {

                                                        goiMon.UpdateGoiMon(MaBan, MaMon, SoLuong);
                                                        goiMon.AddGoiMon1(MaBan,MaMon,SoLuong,"Chưa làm");
                                                        Toast.makeText(getActivity(), "Bạn đã order thành công!", Toast.LENGTH_LONG).show();
                                                    }
                                                } else {
                                                    Toast.makeText(getActivity(), "Vui lòng nhập số lượng lớn hơn 0 !", Toast.LENGTH_LONG).show();
                                                }


                                            } else {
                                                Toast.makeText(getActivity(), "Vui lòng lựa chọn món ăn trước khi order!", Toast.LENGTH_LONG).show();
                                            }
                                        }
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
            }
            break;
            case R.id.exit:

                break;
        }
        return super.onContextItemSelected(item);
    }

    private static String TienTe(String Tien) {
        DecimalFormat format = new DecimalFormat("###,###,##0");
        return format.format(Double.parseDouble(Tien));
    }
}