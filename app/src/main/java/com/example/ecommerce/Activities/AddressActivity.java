package com.example.ecommerce.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ecommerce.Models.NewProductsModel;
import com.example.ecommerce.Models.PopularProductsModel;
import com.example.ecommerce.Models.ShowAllModel;
import com.example.ecommerce.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class AddressActivity extends AppCompatActivity {
    Button addAddress, paymentbtn;
    FirebaseFirestore firestore;
    FirebaseAuth auth;
    ListView addressListView;
    List<String> addressList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);

        addAddress = findViewById(R.id.add_address_btn);
        paymentbtn = findViewById(R.id.payment_btn);
        addressListView = findViewById(R.id.address_recycler);

        auth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();
        addressList = new ArrayList<>();
//get data from detailed activity
        Object obj=getIntent().getSerializableExtra("item");
        fetchAddresses();

        paymentbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double amount = 0.0;
                if (obj instanceof NewProductsModel) {
                    NewProductsModel newProductsModel = (NewProductsModel) obj;
                    amount = newProductsModel.getPrice();
                }
                if (obj instanceof PopularProductsModel) {
                    PopularProductsModel popularProductsModel = (PopularProductsModel) obj;
                    amount = popularProductsModel.getPrice();
                }
                if (obj instanceof ShowAllModel) {
                    ShowAllModel showAllModel = (ShowAllModel) obj;
                    amount = showAllModel.getPrice();
                }

                Intent intent=new Intent(AddressActivity.this,PaymentActivity.class);
                intent.putExtra("amount",amount);
                startActivity(intent);
            }
        });
        addAddress.setOnClickListener(view -> startActivity(new Intent(AddressActivity.this, AddAdressActivity.class)));
    }

    private void fetchAddresses() {
        firestore.collection("CurrentUser")
                .document(auth.getCurrentUser().getUid())
                .collection("Address")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                // Assuming your address object has a method toString() that gives a readable address
                                addressList.add(document.getString("userAddress")); // Adjust this line based on your address model
                            }
                            updateListView();
                        } else {
                            Toast.makeText(AddressActivity.this, "Failed to fetch addresses", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void updateListView() {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, addressList);
        addressListView.setAdapter(adapter);
    }
}
