package com.example.ecommerce.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
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

public class RegistrationActivity extends AppCompatActivity {
EditText name, email,password;
private FirebaseAuth auth;
SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
//        getSupportActionBar().hide();

        auth=FirebaseAuth.getInstance();
        if(auth.getCurrentUser()!=null){
            startActivity(new Intent(RegistrationActivity.this,MainActivity.class));
finish();
        }

        name=findViewById(R.id.name);
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
sharedPreferences =getSharedPreferences("onBoardingScreen",MODE_PRIVATE);
        boolean isFirstTime=sharedPreferences.getBoolean("firstTime",true);
   if(isFirstTime){
       SharedPreferences.Editor editor=sharedPreferences.edit();
       editor.putBoolean("firstTime",false);
       editor.commit();
       Intent intent=new Intent(RegistrationActivity.this, On_Boarding_Activity.class);
   startActivity(intent);
   finish();
   }
    }

    public void SignUp(View view) {
        String username=name.getText().toString();
        String useremail=email.getText().toString();
        String userpassword=password.getText().toString();
        if(TextUtils.isEmpty(username)){
            Toast.makeText(this, "Enter Name !", Toast.LENGTH_SHORT).show();
        return;
        }
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
        auth.createUserWithEmailAndPassword(useremail,userpassword).addOnCompleteListener(RegistrationActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {
                    Toast.makeText(RegistrationActivity.this, "Successfully Registered", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(RegistrationActivity.this,MainActivity.class));
                }else{
                    Toast.makeText(RegistrationActivity.this, "Resigtration Failed "+ task.getException(), Toast.LENGTH_SHORT).show();
                }
            }
        });
      }

    public void SignIn(View view) {
        startActivity(new Intent(RegistrationActivity.this,LoginActivity.class));

    }
}