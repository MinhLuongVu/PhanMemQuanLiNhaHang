package com.example.quanlynhahang.ui.gallery;

public class LoaiMon {

    private String MaLoaiMon;
    private  String TenLoaiMon;

    public LoaiMon(String maLoaiMon, String tenLoaiMon) {
        MaLoaiMon = maLoaiMon;
        TenLoaiMon = tenLoaiMon;
    }

    public String getMaLoaiMon() {
        return MaLoaiMon;
    }

    public String getTenLoaiMon() {
        return TenLoaiMon;
    }

    @Override
    public String toString() {
        return MaLoaiMon;
    }
}
