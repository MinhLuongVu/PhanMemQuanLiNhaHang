package com.example.quanlynhahang.ui.gallery;

import com.example.quanlynhahang.Database.ConnectionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DanhSachLoaiMon {
    ResultSet resultSet;
    ConnectionDB connectionDB=new ConnectionDB();
    Connection connection;
    final ArrayList dstenloaimon= new ArrayList();
    final ArrayList dsmaloaimon=new ArrayList();
    public ArrayList getTenLoaiMon()
    {
        try {

            connection=connectionDB.connections();
            PreparedStatement statement=connection.prepareStatement("select * from LOAIMON");
            resultSet=statement.executeQuery();
            while (resultSet.next())
            {
                dstenloaimon.add(resultSet.getString("TenLoaiMon"));
            }
        }
        catch (Exception ex)
        {

        }
        return dstenloaimon;
    }
    public ArrayList getMaLoaiMon()
    {
        try {
            connection=connectionDB.connections();
            PreparedStatement statement=connection.prepareStatement("select * from LOAIMON");
            resultSet=statement.executeQuery();
            while (resultSet.next())
            {
                dsmaloaimon.add(resultSet.getString("MaLoaiMon"));
            }
        }
        catch (Exception ex)
        {

        }
        return dsmaloaimon;
    }
}
