package com.example.quanlynhahang.ui.login;

import android.app.Activity;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.quanlynhahang.MainActivity;
import com.example.quanlynhahang.R;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    private ArrayList<String> HoTenNV=new ArrayList<String>();
    private Login login=new Login();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button button=findViewById(R.id.btnlogin);
        final EditText editText=findViewById(R.id.edit_user);
        final EditText editText1=findViewById(R.id.edit_password);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                HoTenNV=login.Login(editText.getText().toString(),editText1.getText().toString());
                if (HoTenNV.size()>0)
                {
                    String a=HoTenNV.toString();
                    Intent intent=new Intent(getApplication(), MainActivity.class);
                    intent.putExtra("HoTenNV",a);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(getApplication(),"Sai tên đăng nhập hoặc mật khẩu!",Toast.LENGTH_SHORT).show();
                }


            }
        });

    }

    public void onClick(View view) {
    }
}

