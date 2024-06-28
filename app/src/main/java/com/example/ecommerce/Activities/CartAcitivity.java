package com.example.ecommerce.Activities;

import static android.content.ContentValues.TAG;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ecommerce.Adapter.MyCartAdapter;
import com.example.ecommerce.Models.MyCartModel;
import com.example.ecommerce.Models.ShowAllModel;
import com.example.ecommerce.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class CartAcitivity extends AppCompatActivity {
    Toolbar toolbar;
    RecyclerView recyclerView;
    Button btn;
    TextView overAllTotal;
    List<MyCartModel> list;
    MyCartAdapter myCartAdapter;
    private FirebaseAuth auth;
    private FirebaseFirestore firestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cart_acitivity);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        overAllTotal=findViewById(R.id.textView3);
        auth=FirebaseAuth.getInstance();
btn=findViewById(R.id.buy_now);
btn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        startActivity(new Intent(CartAcitivity.this,AddressActivity.class));

    }
});
        firestore=FirebaseFirestore.getInstance();
        //get data from my cart adpater
LocalBroadcastManager.getInstance(this).registerReceiver(mbroadcastReceiver,new IntentFilter("MyTotalAmount"));
        toolbar=findViewById(R.id.my_Cart_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

recyclerView=findViewById(R.id.cart_rec);
recyclerView.setLayoutManager(new LinearLayoutManager(this));
list=new ArrayList<>();
myCartAdapter=new MyCartAdapter(this,list);
recyclerView.setAdapter(myCartAdapter);
firestore.collection("AddToCart").document(auth.getCurrentUser().getUid()).collection("User").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
    @Override
    public void onComplete(@NonNull Task<QuerySnapshot> task) {
      if(task.isSuccessful()){
          for(DocumentSnapshot doc:task.getResult().getDocuments()){
                MyCartModel myCartModel=doc.toObject(MyCartModel.class);
                list.add(myCartModel);
                myCartAdapter.notifyDataSetChanged();
          }
      }
    }
});
  }
public BroadcastReceiver mbroadcastReceiver=new BroadcastReceiver() {
    @Override
    public void onReceive(Context context, Intent intent) {
            int totalBill=intent.getIntExtra("totalAmount",0);
        overAllTotal.setText("Total Amount : "+totalBill+"$");

    }
};

}