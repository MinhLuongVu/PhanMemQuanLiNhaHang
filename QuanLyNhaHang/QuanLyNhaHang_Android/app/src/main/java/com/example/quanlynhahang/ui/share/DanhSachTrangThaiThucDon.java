package com.example.quanlynhahang.ui.share;

import com.example.quanlynhahang.Database.ConnectionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DanhSachTrangThaiThucDon {
    ResultSet resultSet;
    ConnectionDB connectionDB=new ConnectionDB();
    Connection connection;
    final ArrayList dsmatrangthai= new ArrayList();
    final ArrayList dstenmon=new ArrayList();
    final ArrayList dssoluong= new ArrayList();
    final ArrayList dstenban=new ArrayList();
    final ArrayList dstrangthai= new ArrayList();
    private PreparedStatement statement;
    public void UpdateTrangThaiThucDon(String MaTrangThai,String TrangThai)
    {
        dsmatrangthai.clear();
        dstenmon.clear();
        dssoluong.clear();
        dstrangthai.clear();
        dstenban.clear();
        try {
            statement=connection.prepareStatement("update TRANGTHAI set TrangThai=N'"+TrangThai+"' where MaTrangThai='"+MaTrangThai+"'");
            resultSet=statement.executeQuery();
            statement.executeUpdate();
        }
        catch (Exception ex)
        {

        }
    }
    public ArrayList getMaTrangThai(String MaBan)
    {
        dsmatrangthai.clear();
        try {
            connection=connectionDB.connections();
            if (MaBan==null)
            {

                statement=connection.prepareStatement("select * from TRANGTHAI order by TrangThai asc");

            }
            else
            {
                statement=connection.prepareStatement("select * from TRANGTHAI where MaBan='"+MaBan+"' order by TrangThai asc");
            }
            resultSet=statement.executeQuery();
            while (resultSet.next())
            {
                dsmatrangthai.add(resultSet.getString("MaTrangThai"));
            }

        }
        catch (Exception ex)
        {

        }
        return dsmatrangthai;
    }

    public ArrayList getTenMon(String MaBan)
    {
        dstenmon.clear();
        try {
            connection=connectionDB.connections();
            if (MaBan==null)
            {
                statement=connection.prepareStatement("select * from TRANGTHAI,MONAN  where  MONAN.MaMon=TRANGTHAI.MaMon order by TRANGTHAI.TrangThai asc");
            }
            else
            {
                statement=connection.prepareStatement("select * from TRANGTHAI,MONAN  where TRANGTHAI.MaBan='"+MaBan+"' and MONAN.MaMon=TRANGTHAI.MaMon order by TRANGTHAI.TrangThai asc");
            }
            resultSet=statement.executeQuery();
            while (resultSet.next())
            {
                dstenmon.add(resultSet.getString("TenMon"));
            }
        }
        catch (Exception ex)
        {

        }
        return dstenmon;
    }

    public ArrayList getTenBan(String MaBan)
    {
        dstenban.clear();
        try {
            connection=connectionDB.connections();
            if (MaBan==null)
            {
                statement=connection.prepareStatement("select * from TRANGTHAI,DANHSACHBAN  where  DANHSACHBAN.MaBan=TRANGTHAI.MaBan order by TRANGTHAI.TrangThai asc");
            }
            else
            {
                statement=connection.prepareStatement("select * from TRANGTHAI,DANHSACHBAN  where TRANGTHAI.MaBan='"+MaBan+"' and  DANHSACHBAN.MaBan=TRANGTHAI.MaBan order by TRANGTHAI.TrangThai asc");
            }
            resultSet=statement.executeQuery();
            while (resultSet.next())
            {
                dstenban.add(resultSet.getString("TenBan"));
            }
        }
        catch (Exception ex)
        {

        }
        return dstenban;
    }
    public ArrayList getSoLuong(String MaBan)
    {
        dssoluong.clear();
        try {
            connection=connectionDB.connections();
            if (MaBan==null)
            {
                statement=connection.prepareStatement("select * from TRANGTHAI  order by TrangThai asc");
            }
            else
            {
                statement=connection.prepareStatement("select * from TRANGTHAI where MaBan='"+MaBan+"' order by TrangThai asc");
            }
            resultSet=statement.executeQuery();
            while (resultSet.next())
            {
                dssoluong.add(resultSet.getString("SoLuong"));
            }
        }
        catch (Exception ex)
        {

        }
        return dssoluong;
    }
    public ArrayList getTrangThai(String MaBan)
    {
        dstrangthai.clear();
        try {

            connection=connectionDB.connections();
            if (MaBan==null)
            {
                statement=connection.prepareStatement("select * from TRANGTHAI order by TrangThai asc");
            }
            else
            {
                statement=connection.prepareStatement("select * from TRANGTHAI where MaBan='"+MaBan+"' order by TrangThai asc");
            }
            resultSet=statement.executeQuery();
            while (resultSet.next())
            {
                dstrangthai.add(resultSet.getString("TrangThai"));
            }
        }
        catch (Exception ex)
        {

        }
        return dstrangthai;
    }




   /* public ArrayList getMaTrangThai(String MaBan)
    {
        try {

            connection=connectionDB.connections();
            PreparedStatement statement=connection.prepareStatement("select * from GOIMON,MONAN,DANHSACHBAN where DANHSACHBAN.MaBan=GOIMON.MaBan and MONAN.MaMon=GOIMON.MaMon and GOIMON.MaBan='"+MaBan+"'");
            resultSet=statement.executeQuery();
            while (resultSet.next())
            {
                dsmagoimon.add(resultSet.getString("MaGoiMon"));
            }
        }
        catch (Exception ex)
        {

        }
        return dsmagoimon;
    }
    public ArrayList getTenMon(String MaBan)
    {
        try {

            connection=connectionDB.connections();
            PreparedStatement statement=connection.prepareStatement("select * from GOIMON,MONAN,DANHSACHBAN where DANHSACHBAN.MaBan=GOIMON.MaBan and MONAN.MaMon=GOIMON.MaMon and GOIMON.MaBan='"+MaBan+"'");
            resultSet=statement.executeQuery();
            while (resultSet.next())
            {
                dstenmon.add(resultSet.getString("TenMon"));
            }
        }
        catch (Exception ex)
        {

        }
        return dstenmon;
    }
    public ArrayList getSoLuong(String MaBan)
    {
        try {

            connection=connectionDB.connections();
            PreparedStatement statement=connection.prepareStatement("select * from GOIMON,MONAN,DANHSACHBAN where DANHSACHBAN.MaBan=GOIMON.MaBan and MONAN.MaMon=GOIMON.MaMon and GOIMON.MaBan='"+MaBan+"'");
            resultSet=statement.executeQuery();
            while (resultSet.next())
            {
                dssoluong.add(resultSet.getString("SoLuong"));
            }
        }
        catch (Exception ex)
        {

        }
        return dssoluong;
    }
    public ArrayList getHinhAnh(String MaBan)
    {
        try {

            connection=connectionDB.connections();
            PreparedStatement statement=connection.prepareStatement("select MONAN.HinhAnh from GOIMON,MONAN,DANHSACHBAN where DANHSACHBAN.MaBan=GOIMON.MaBan and MONAN.MaMon=GOIMON.MaMon and GOIMON.MaBan='"+MaBan+"'");
            resultSet=statement.executeQuery();
            while (resultSet.next())
            {
                dshinhanh.add(resultSet.getString("HinhAnh"));
            }
        }
        catch (Exception ex)
        {

        }
        return dshinhanh;
    }
    public ArrayList getDonGia(String MaBan)
    {
        try {

            connection=connectionDB.connections();
            PreparedStatement statement=connection.prepareStatement("select MONAN.DonGia from GOIMON,MONAN,DANHSACHBAN where DANHSACHBAN.MaBan=GOIMON.MaBan and MONAN.MaMon=GOIMON.MaMon and GOIMON.MaBan='"+MaBan+"'");
            resultSet=statement.executeQuery();
            while (resultSet.next())
            {
                dsdongia.add(resultSet.getString("DonGia"));
            }
        }
        catch (Exception ex)
        {

        }
        return dsdongia;
    }
    public ArrayList getThanhTien(String MaBan)
    {
        try {

            connection=connectionDB.connections();
            PreparedStatement statement=connection.prepareStatement("select * from GOIMON,MONAN,DANHSACHBAN where DANHSACHBAN.MaBan=GOIMON.MaBan and MONAN.MaMon=GOIMON.MaMon and GOIMON.MaBan='"+MaBan+"'");
            resultSet=statement.executeQuery();
            while (resultSet.next())
            {
                dsthanhtien.add(resultSet.getString("ThanhTien"));
            }
        }
        catch (Exception ex)
        {

        }
        return dsthanhtien;
    }*/
}
