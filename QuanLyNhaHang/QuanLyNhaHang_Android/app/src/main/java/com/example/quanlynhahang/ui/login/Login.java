package com.example.quanlynhahang.ui.login;

import com.example.quanlynhahang.Database.ConnectionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Login {

    final ArrayList HoTenNV=new ArrayList();
    ConnectionDB connectionDB=new ConnectionDB();
    ResultSet resultSet;
    Connection connection=connectionDB.connections();


/*
    public String Login(String TenDN,String MatKhau)
    {
        try {
            PreparedStatement statement1=connection.prepareStatement("select HoTenNV from NHANVIEN where TenDN='"+TenDN+"' and MatKhau='"+MatKhau+"'");
            resultSet=statement1.executeQuery();
            statement1.executeUpdate();
        }
        catch (Exception ex)
        {

        }
    }*/

    public ArrayList Login(String TenDN,String MatKhau)
    {
        try {
            MatKhau=MD5(MatKhau);
            PreparedStatement statement1=connection.prepareStatement("select HoTenNV from NHANVIEN where TenDN='"+TenDN+"' and MatKhau='"+MatKhau+"'");
            resultSet=statement1.executeQuery();
            while (resultSet.next())
            {
                HoTenNV.add(resultSet.getString("HoTenNV"));
            }
        }
        catch (Exception ex)
        {

        }
        return HoTenNV;
    }
    public String MD5(String md5) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(md5.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
        }
        return null;
    }

}