package com.example.quanlynhahang.ui.tools;

public class ThucDon {
    private String TenMon;
    private String HinhAnh;
    private String DonGia;
    private String SoLuong;
    private String ThanhTien;
    private String MaGoiMon;

    public ThucDon(String tenMon, String hinhAnh, String donGia,String soLuong,String thanhTien,String maGoiMon) {
        TenMon = tenMon;
        HinhAnh = hinhAnh;
        DonGia = donGia;
        SoLuong=soLuong;
        ThanhTien=thanhTien;
        MaGoiMon=maGoiMon;
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

    public String getSoLuong() {
        return SoLuong;
    }

    public String getThanhTien() {
        return ThanhTien;
    }

    public String getMaGoiMon() {
        return MaGoiMon;
    }

    @Override
    public String toString() {
        return MaGoiMon;
    }

}
