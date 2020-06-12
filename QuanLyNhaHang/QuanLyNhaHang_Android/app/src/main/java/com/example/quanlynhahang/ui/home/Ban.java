package com.example.quanlynhahang.ui.home;

public class Ban {
    private String MaBan;
    private String TenBan;
    private String TrangThai;
    private String HinhAnh;

    public Ban(String maBan, String tenBan, String trangThai) {
        MaBan = maBan;
        TenBan = tenBan;
        TrangThai = trangThai;
    }

    public String getMaBan() {
        return MaBan;
    }

    public String getTenBan() {
        return TenBan;
    }

    public String getTrangThai() {
        return TrangThai;
    }


    @Override
    public String toString() {
        return MaBan;
    }
}
