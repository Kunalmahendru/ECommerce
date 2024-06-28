package com.example.ecommerce.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ecommerce.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
EditText email,password;
private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
//        getSupportActionBar().hide();
        auth=FirebaseAuth.getInstance();
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
    }

    public void SignUp(View view) {

        startActivity(new Intent(LoginActivity.this,MainActivity.class));

    }

    public void SignIn(View view) {
        String useremail=email.getText().toString();
        String userpassword=password.getText().toString();
        if(TextUtils.isEmpty(useremail)){
            Toast.makeText(this, "Enter Email Adress !", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(userpassword)){
            Toast.makeText(this, "Enter PassWord !", Toast.LENGTH_SHORT).show();
            return;
        }
        if(userpassword.length()<6){

            Toast.makeText(this, "PassWord to short , Enter Minimum 6 Characters !", Toast.LENGTH_SHORT).show();
            return;
        }
        auth.signInWithEmailAndPassword(useremail,userpassword).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {
                    Toast.makeText(LoginActivity.this, "Successfully Login", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LoginActivity.this,MainActivity.class));
                }else{
                    Toast.makeText(LoginActivity.this, "Login Failed "+ task.getException(), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}