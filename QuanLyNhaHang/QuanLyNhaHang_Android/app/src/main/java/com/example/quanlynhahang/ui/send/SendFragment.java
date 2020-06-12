package com.example.quanlynhahang.ui.send;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.quanlynhahang.R;

public class SendFragment extends Fragment {

    private SendViewModel sendViewModel;
    private RadioButton radioButton,radioButton1,radioButton2;
    public View onCreateView(@NonNull final LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        sendViewModel =
                ViewModelProviders.of(this).get(SendViewModel.class);
        View root = inflater.inflate(R.layout.fragment_send, container, false);

        RadioGroup radioGroup=root.findViewById(R.id.radiogroup);
        final RadioButton radioButton=root.findViewById(R.id.radiochualam);
        final RadioButton radioButton1=root.findViewById(R.id.radiodanglam);
        final RadioButton radioButton2=root.findViewById(R.id.radiodalam);
        sendViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                radioButton.setOnCheckedChangeListener(new Radio_check());
                radioButton1.setOnCheckedChangeListener(new Radio_check());
                radioButton2.setOnCheckedChangeListener(new Radio_check());
            }
        });
        return root;
    }
    private class Radio_check implements CompoundButton.OnCheckedChangeListener
    {

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if(radioButton.isChecked())
            {
                Toast.makeText(getActivity(),"1",Toast.LENGTH_LONG).show();
            }
            else if(radioButton1.isChecked())
            {
                Toast.makeText(getActivity(),"2",Toast.LENGTH_LONG).show();
            }
            else if(radioButton2.isChecked())
            {
                Toast.makeText(getActivity(),"3",Toast.LENGTH_LONG).show();
            }
        }
    }
}