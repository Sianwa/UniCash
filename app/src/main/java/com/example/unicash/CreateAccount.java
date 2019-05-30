package com.example.unicash;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class CreateAccount extends AppCompatActivity {
    EditText _txtusername,_txtemail,_txtpass;
    Button _btnreg;
    TextView _btnlogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        _txtusername= findViewById(R.id.username);
        _txtemail= findViewById(R.id.email);
        _txtpass= findViewById(R.id.password);
        _btnlogin =findViewById(R.id.btnlog);
        _btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CreateAccount.this,Login.class);
                startActivity(intent);
            }
        });

        _btnreg= findViewById(R.id.reg);
        _btnreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createAccount();
            }
        });
    }

    private void createAccount() {
    }
}
