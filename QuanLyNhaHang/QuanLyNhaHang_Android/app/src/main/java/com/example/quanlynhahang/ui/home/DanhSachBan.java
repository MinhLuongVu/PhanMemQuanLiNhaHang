package com.example.quanlynhahang.ui.home;

import com.example.quanlynhahang.Database.ConnectionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DanhSachBan {
    ResultSet resultSet;
    ConnectionDB connectionDB=new ConnectionDB();
    Connection connection;

    final ArrayList dstenban= new ArrayList();
    final ArrayList dstrangthai=new ArrayList();
    final ArrayList dsmaban=new ArrayList();
    public ArrayList getTenBan()
    {
        dstenban.clear();
        try {
            connection=connectionDB.connections();
            PreparedStatement statement=connection.prepareStatement("select * from DANHSACHBAN");
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
    public  ArrayList getTrangThai()
    {
        dstrangthai.clear();
        try {
            connection=connectionDB.connections();
            PreparedStatement statement=connection.prepareStatement("select * from DANHSACHBAN");
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
    public  ArrayList getMaBan()
    {
        dsmaban.clear();
        try {
            connection=connectionDB.connections();
            PreparedStatement statement=connection.prepareStatement("select * from DANHSACHBAN");
            resultSet=statement.executeQuery();
            while (resultSet.next())
            {
                dsmaban.add(resultSet.getString("MaBan"));
            }
        }
        catch (Exception ex)
        {

        }
        return dsmaban;
    }
}
