package com.example.unicash;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Login extends AppCompatActivity {
    EditText _txtemail,_txtpass;
    Button _btnlogin;
    TextView _btnreg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        _btnlogin = findViewById(R.id.login);
        _btnreg = findViewById(R.id.btnreg);

        _txtemail = findViewById(R.id.email);
        _txtpass = findViewById(R.id.password);

        _btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, MainActivity.class);
                startActivity(intent);
                //login();
            }
        });

        _btnreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, CreateAccount.class);
                startActivity(intent);
            }
        });
    }

    private void login() {
        //TODO ADD AUTHENTICATION CODE
    }
}
