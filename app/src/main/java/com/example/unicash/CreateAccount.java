package com.example.unicash;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class CreateAccount extends AppCompatActivity {
    EditText _txtusername,_txtemail,_txtpass;
    Button _btnreg;
    TextView _btnlogin;

    private FirebaseAuth mAuth;
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
                Intent intent = new Intent(CreateAccount.this, Login.class);
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

        mAuth=FirebaseAuth.getInstance();
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

        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    //checks status of task proceeding it
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(CreateAccount.this, "Signed in",Toast.LENGTH_LONG).show();

                            Intent intent = new Intent(CreateAccount.this, MainActivity.class);
                            startActivity(intent);
                        }
                        else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(CreateAccount.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }
}
