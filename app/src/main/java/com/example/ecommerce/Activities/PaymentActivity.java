package com.example.ecommerce.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.ecommerce.R;

public class PaymentActivity extends AppCompatActivity {
Toolbar toolbar;
TextView subTotal,discount,shipping,total;
Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_payment);
        toolbar=findViewById(R.id.payment_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        subTotal=findViewById(R.id.sub_total);
        discount=findViewById(R.id.textView17);
        shipping=findViewById(R.id.textView18);
        total=findViewById(R.id.total_amt);
double amount=0.0;
amount=getIntent().getDoubleExtra("amount",0.0);
total.setText(amount+"$");
subTotal.setText(amount+"$");

btn=findViewById(R.id.pay_btn);

        btn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Toast.makeText(PaymentActivity.this, "Your Order Has Been Placed Successfully", Toast.LENGTH_LONG).show();


        Intent intent = new Intent(PaymentActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
});


    }
}