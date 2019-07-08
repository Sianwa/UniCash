package com.example.unicash;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.unicash.models.SignUp;
import com.example.unicash.models.User;
import com.example.unicash.network.Api;
import com.example.unicash.onboarding.Onboarding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CreateAccount extends AppCompatActivity {
    EditText _txtusername,_txtemail,_txtpass;
    Button _btnreg;
    TextView _btnlogin;

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(Api.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    Api api = retrofit.create(Api.class);
    SignUp signup;

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
                Intent intent = new Intent(CreateAccount.this, UserLogin.class);
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
        String username=_txtusername.getText().toString();
        String email=_txtemail.getText().toString();
        String password=_txtpass.getText().toString();

        if(TextUtils.isEmpty(username)){
            Toast.makeText(this,"Please enter username",Toast.LENGTH_LONG).show();
            return;
        }
        if(TextUtils.isEmpty(email)){
            Toast.makeText(this,"Please enter email",Toast.LENGTH_LONG).show();
            return;
        }
        if(TextUtils.isEmpty(password)){
            Toast.makeText(this,"Please enter password",Toast.LENGTH_LONG).show();
            return;
        }

        signup = new SignUp(email,username,password);
        Call<User> call=api.signup(signup);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.isSuccessful()){
                    Intent intent= new Intent(CreateAccount.this, Onboarding.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_LONG).show();
                Log.d("Error",t.getMessage());

            }
        });

    }

}
