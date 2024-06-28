package com.example.ecommerce.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.ecommerce.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class AddAdressActivity extends AppCompatActivity {

    EditText name,address,city,postalCode,phoneNumber;
    Toolbar toolb;
    Button addAdresbtn;
    FirebaseFirestore fireStore;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_adress);
        fireStore= FirebaseFirestore.getInstance();
        auth=FirebaseAuth.getInstance();
toolb=findViewById(R.id.add_address_toolbar);
setSupportActionBar(toolb);
getSupportActionBar().setDisplayHomeAsUpEnabled(true);
toolb.setNavigationOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        finish();
    }
});
name=findViewById(R.id.ad_name);
address=findViewById(R.id.ad_address);
city=findViewById(R.id.ad_city);
phoneNumber=findViewById(R.id.ad_phone);
postalCode=findViewById(R.id.ad_code);

addAdresbtn=findViewById(R.id.ad_add_address);
addAdresbtn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        String username=name.getText().toString();
        String userCity=city.getText().toString();
        String userAddress=address.getText().toString();
        String userCode=postalCode.getText().toString();
        String userNumber=phoneNumber.getText().toString();
String finalstring="";
if(!username.isEmpty()){
    finalstring+=username;
}
finalstring+=" ";
    if(!userCity.isEmpty()){
            finalstring+=userCity;
        }
        finalstring+=" ";
    if(!userAddress.isEmpty()){
            finalstring+=userAddress;
        }finalstring+=" ";
    if(!userCode.isEmpty()){
            finalstring+=userCode;
        }
        finalstring+=" ";
    if(!userNumber.isEmpty()){
            finalstring+=userNumber;
        }
        if(!username.isEmpty() && !userCity.isEmpty() &&!userCode.isEmpty() &&!userAddress.isEmpty() &&!userNumber.isEmpty() ){
            Map<String,String> map=new HashMap<>() ;
            map.put("userAddress",finalstring);
            fireStore.collection("CurrentUser").document(auth.getCurrentUser().getUid()).collection("Address")
                    .add(map).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentReference> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(AddAdressActivity.this, "Address Added", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(AddAdressActivity.this,DetailActivity.class));
                                finish();
                            }
                        }
                    });
        }else {
            Toast.makeText(AddAdressActivity.this, "Kindly fill all ENtries", Toast.LENGTH_SHORT).show();
        }
    }
});


    }
}