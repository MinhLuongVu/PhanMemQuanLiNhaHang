package com.example.quanlynhahang.ui.gallery;

import com.example.quanlynhahang.Database.ConnectionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DanhSachMonAn {
    ResultSet resultSet;
    ConnectionDB connectionDB=new ConnectionDB();
    Connection connection;

    final ArrayList dstenmon= new ArrayList();
    final ArrayList dsdongia=new ArrayList();
    final ArrayList dshinhanh=new ArrayList();
    final ArrayList dsmamon1= new ArrayList();
    final ArrayList dstenmon1= new ArrayList();
    final ArrayList dsdongia1=new ArrayList();
    final ArrayList dshinhanh1=new ArrayList();

    public ArrayList getTenMon()
    {
        try {
            connection=connectionDB.connections();
            PreparedStatement statement=connection.prepareStatement("select * from MONAN");
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
    public  ArrayList getdongia()
    {
        try {
            connection=connectionDB.connections();
            PreparedStatement statement=connection.prepareStatement("select * from MONAN");
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
    public  ArrayList getHinhAnh()
    {
        try {
            connection=connectionDB.connections();
            PreparedStatement statement=connection.prepareStatement("select * from MONAN");
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

    public ArrayList getMaMon1(String MaLoaiMon)
    {
        dsmamon1.clear();
        try {
            connection=connectionDB.connections();
            PreparedStatement statement=connection.prepareStatement("select * from MONAN where MaLoaiMon='"+MaLoaiMon+"'");
            resultSet=statement.executeQuery();
            while (resultSet.next())
            {

                dsmamon1.add(resultSet.getString("MaMon"));
            }
        }
        catch (Exception ex)
        {

        }
        return dsmamon1;
    }

    public ArrayList getTenMon1(String MaLoaiMon)
    {
        dstenmon1.clear();
        try {
            connection=connectionDB.connections();
            PreparedStatement statement=connection.prepareStatement("select * from MONAN where MaLoaiMon='"+MaLoaiMon+"'");
            resultSet=statement.executeQuery();
            while (resultSet.next())
            {

                dstenmon1.add(resultSet.getString("TenMon"));
            }
        }
        catch (Exception ex)
        {

        }
        return dstenmon1;
    }
    public  ArrayList getdongia1(String MaLoaiMon)
    {
        dsdongia1.clear();
        try {
            connection=connectionDB.connections();
            PreparedStatement statement=connection.prepareStatement("select * from MONAN where MaLoaiMon='"+MaLoaiMon+"'");
            resultSet=statement.executeQuery();
            while (resultSet.next())
            {
                dsdongia1.add(resultSet.getString("DonGia"));
            }
        }
        catch (Exception ex)
        {

        }
        return dsdongia1;
    }
    public  ArrayList getHinhAnh1(String MaLoaiMon)
    {
        dshinhanh1.clear();
        try {
            connection=connectionDB.connections();
            PreparedStatement statement=connection.prepareStatement("select * from MONAN where MaLoaiMon='"+MaLoaiMon+"'");
            resultSet=statement.executeQuery();
            while (resultSet.next())
            {
                dshinhanh1.add(resultSet.getString("HinhAnh"));
            }
        }
        catch (Exception ex)
        {

        }
        return dshinhanh1;
    }
}
