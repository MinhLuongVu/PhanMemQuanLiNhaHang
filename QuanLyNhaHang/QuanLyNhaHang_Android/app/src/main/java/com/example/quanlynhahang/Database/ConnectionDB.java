package com.example.quanlynhahang.Database;
import android.annotation.SuppressLint;
import android.app.Application;
import android.os.StrictMode;
import android.util.Log;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.Statement;
import java.util.ArrayList;

public class ConnectionDB extends Application {

    String IP, DB, DBUsername, DBPass,classs;
    @SuppressLint("NewApi")
    Statement st;
    ResultSet rs;
    Connection conn;
    public Connection connections(){
        classs ="net.sourceforge.jtds.jdbc.Driver";
        IP = "192.168.1.6";
        DB = "QuanLiNhaHang";
        DBUsername = "minhluong";
        DBPass = "23021998";
        StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        java.sql.Connection connection=null;
        String ConURL=null;

        try{Class.forName(classs);
            ConURL = "jdbc:jtds:sqlserver://"+IP+";"+"databaseName="+DB+";user="+DBUsername+";password="+DBPass+";";
            connection = DriverManager.getConnection(ConURL);

        }catch (SQLDataException se){
            Log.e("Error",se.getMessage());

        }
        catch (ClassNotFoundException e){
            Log.e("eroor from class",e.getMessage());
        }
        catch (Exception ex){
            Log.e("Eroor Ex",ex.getMessage());
        }

        return connection;
    }

    /*
    public  void save(String sql)
    {
        try
        {
            st=conn.createStatement();
            st.executeQuery(sql);
        }
        catch (Exception ex)
        {
            Toast.makeText(getApplicationContext(),ex.getMessage(),Toast.LENGTH_LONG).show();
        }
    }*/
}