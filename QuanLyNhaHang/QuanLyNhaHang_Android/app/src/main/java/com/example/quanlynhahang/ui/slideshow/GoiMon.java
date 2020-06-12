package com.example.quanlynhahang.ui.slideshow;

import com.example.quanlynhahang.Database.ConnectionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class GoiMon {

    final ArrayList dsgoimon=new ArrayList();
    ConnectionDB connectionDB=new ConnectionDB();
    ResultSet resultSet;
    Connection connection=connectionDB.connections();

    public  void  AddGoiMon(String MaBan,String MaMon,String DonGia,String SoLuong)
    {
        try {
            PreparedStatement statement=connection.prepareStatement("insert into GOIMON(MaBan,MaMon,DonGia,SoLuong) values('"+MaBan+"','"+MaMon+"','"+DonGia+"','"+SoLuong+"')");
            resultSet=statement.executeQuery();
            statement.executeUpdate();
        }
        catch (Exception ex)
        {

        }
    }
    public  void  AddGoiMon1(String MaBan,String MaMon,String SoLuong,String TrangThai)
    {
        try {
            PreparedStatement statement=connection.prepareStatement("insert into TRANGTHAI(MaBan,MaMon,TrangThai,SoLuong) values('"+MaBan+"','"+MaMon+"',N'"+TrangThai+"','"+SoLuong+"')");
            resultSet=statement.executeQuery();
            statement.executeUpdate();
        }
        catch (Exception ex)
        {

        }
    }
    public void TrangThaiBan(String MaBan)
    {
        String TrangThai="dangoi.png";
        try {
            PreparedStatement statement1=connection.prepareStatement("update DANHSACHBAN set TrangThai=N'"+TrangThai+"' where MaBan='"+MaBan+"'");
            resultSet=statement1.executeQuery();
            statement1.executeUpdate();
        }
        catch (Exception ex)
        {

        }
    }
    public  ArrayList getDanhSachGoiMon(String MaBan,String MaMon)
    {
        dsgoimon.clear();
        try {
            connection=connectionDB.connections();
            PreparedStatement statement=connection.prepareStatement("select * from GOIMON where MaBan='"+MaBan+"' and MaMon='"+MaMon+"'");
            resultSet=statement.executeQuery();
            while (resultSet.next())
            {
                dsgoimon.add(resultSet.getString("MaMon"));
            }
        }
        catch (Exception ex)
        {

        }
        return dsgoimon;
    }
    public  void  UpdateGoiMon(String MaBan,String MaMon,String SoLuong)
    {
        try {
            connection=connectionDB.connections();
            PreparedStatement statement=connection.prepareStatement("update GOIMON set SoLuong+='"+SoLuong+"' where MaBan='"+MaBan+"' and MaMon='"+MaMon+"'");
            resultSet=statement.executeQuery();
            statement.executeUpdate();
        }
        catch (Exception ex)
        {

        }
    }
}
