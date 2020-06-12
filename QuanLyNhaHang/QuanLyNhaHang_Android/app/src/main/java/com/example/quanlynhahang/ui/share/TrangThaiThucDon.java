package com.example.quanlynhahang.ui.share;

public class TrangThaiThucDon {
    private String MaTrangThai;
    private  String TenBan;
    private String TenMon;
    private String SoLuong;
    private String TrangThai;
    private int STT;

    public TrangThaiThucDon(String maTrangThai, String tenBan, String tenMon, String soLuong, String trangThai, int STT) {
        MaTrangThai = maTrangThai;
        TenBan = tenBan;
        TenMon = tenMon;
        SoLuong = soLuong;
        TrangThai = trangThai;
        this.STT = STT;
    }

    public String getMaTrangThai() {
        return MaTrangThai;
    }

    public String getTenBan() {
        return TenBan;
    }

    public String getTenMon() {
        return TenMon;
    }

    public String getSoLuong() {
        return SoLuong;
    }

    public String getTrangThai() {
        return TrangThai;
    }

    public int getSTT() {
        return STT;
    }

    @Override
    public String toString() {
        return MaTrangThai ;
    }
}
