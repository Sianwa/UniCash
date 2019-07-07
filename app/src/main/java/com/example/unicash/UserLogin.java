package com.example.unicash;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.unicash.models.User;
import com.example.unicash.models.Login;
import com.example.unicash.network.Api;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserLogin extends AppCompatActivity {
    EditText _txtemail,_txtpass;
    Button _btnlogin;
    TextView _btnreg;

    private FirebaseAuth mAuth;

    Retrofit builder = new Retrofit.Builder()
            .baseUrl(Api.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();


    Api api = builder.create(Api.class);
    Login login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        _btnlogin = findViewById(R.id.login);
        _btnreg = findViewById(R.id.btnreg);

        _txtemail = findViewById(R.id.email);
        _txtpass = findViewById(R.id.password);

        _btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

        _btnreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserLogin.this, CreateAccount.class);
                startActivity(intent);
            }
        });
    }

    private static String token;
    private void login() {
        String email=_txtemail.getText().toString();
        String password=_txtpass.getText().toString();

        if(TextUtils.isEmpty(email)){
            Toast.makeText(this,"Please enter email",Toast.LENGTH_LONG).show();
            return;
        }
        if(TextUtils.isEmpty(password)){
            Toast.makeText(this,"Please enter password",Toast.LENGTH_LONG).show();
            return;
        }

        login = new Login(email,password);
        Call<User>call=api.login(login);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()){
                    //Toast.makeText(UserLogin.this,response.body().getToken(),Toast.LENGTH_LONG).show();
                    token=response.body().getToken();

                    //signin user with custom token
                     startSignIn();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_LONG).show();
                Log.d("Error",t.getMessage());

            }
        });
    }

    private void startSignIn() {
        mAuth.signInWithCustomToken(token)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                       if(task.isSuccessful()){
                           //sign in user
                           Intent intent = new Intent(UserLogin.this, MainActivity.class);
                           startActivity(intent);

                       }
                       else{
                           Toast.makeText(UserLogin.this, "Authentication failed.",
                                   Toast.LENGTH_SHORT).show();
                       }
                    }
                });

    }
}
