package com.example.quanlynhahang.ui.gallery;


import androidx.fragment.app.FragmentActivity;

import java.util.ArrayList;
import java.util.Objects;

public class MonAn {
    private String TenMon;
    private String HinhAnh;
    private String DonGia;
    private String MaMon;

    public MonAn(String tenMon, String hinhAnh, String donGia, String maMon) {
        TenMon = tenMon;
        HinhAnh = hinhAnh;
        DonGia = donGia;
        MaMon = maMon;
    }

    public String getTenMon() {
        return TenMon;
    }

    public String getHinhAnh() {
        return HinhAnh;
    }

    public String getDonGia() {
        return DonGia;
    }

    public String getMaMon() {
        return MaMon;
    }

    @Override
    public String toString() {
        return "MonAn{" +
                "TenMon='" + TenMon + '\'' +
                ", HinhAnh='" + HinhAnh + '\'' +
                ", DonGia='" + DonGia + '\'' +
                ", MaMon='" + MaMon + '\'' +
                '}';
    }
}
