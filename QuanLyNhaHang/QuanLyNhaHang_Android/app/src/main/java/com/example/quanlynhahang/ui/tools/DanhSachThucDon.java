package com.example.quanlynhahang.ui.tools;

import com.example.quanlynhahang.Database.ConnectionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DanhSachThucDon {
    ResultSet resultSet;
    ConnectionDB connectionDB=new ConnectionDB();
    Connection connection;
    final ArrayList dsmagoimon= new ArrayList();
    final ArrayList dstenmon=new ArrayList();
    final ArrayList dssoluong= new ArrayList();
    final ArrayList dsdongia=new ArrayList();
    final ArrayList dshinhanh= new ArrayList();
    final ArrayList dsthanhtien= new ArrayList();

    public void UpdateThucDon(String MaGoiMon,String SoLuong)
    {
        dsmagoimon.clear();
        dsthanhtien.clear();
        dsdongia.clear();
        dssoluong.clear();
        dstenmon.clear();
        try {
            PreparedStatement statement1=connection.prepareStatement("update GOIMON set SoLuong=N'"+SoLuong+"' where MaGoiMon='"+MaGoiMon+"'");
            resultSet=statement1.executeQuery();
            statement1.executeUpdate();
        }
        catch (Exception ex)
        {

        }
    }

    public ArrayList getMaGoiMon(String MaBan)
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
    }
}

