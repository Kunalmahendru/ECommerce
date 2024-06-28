package com.example.ecommerce.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.example.ecommerce.Models.NewProductsModel;
import com.example.ecommerce.Models.PopularProductsModel;
import com.example.ecommerce.Models.ShowAllModel;
import com.example.ecommerce.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class DetailActivity extends AppCompatActivity {
ImageView detailImg;
TextView rating ,name ,desc,price,quantity;
Button addtocard,buyNow;
Toolbar toolbar;
ImageView addItems,removeItems;
int totalQuantity=1;
int totalprice=0;
NewProductsModel newProductsModel=null;
PopularProductsModel popularProductsModel=null;

//ShowAllAcitivity
    ShowAllModel showAllModel=null;
    FirebaseAuth auth;
private FirebaseFirestore firestore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_detail);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        toolbar=findViewById(R.id.detailed_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
toolbar.setNavigationOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        finish();
    }
});
firestore=FirebaseFirestore.getInstance();
auth=FirebaseAuth.getInstance();
final Object object=getIntent().getSerializableExtra("detailed");
if(object instanceof NewProductsModel){
    newProductsModel=(NewProductsModel)object;
}else if(object instanceof PopularProductsModel) {
popularProductsModel=(PopularProductsModel) object;
}else if(object instanceof ShowAllModel) {
    showAllModel=(ShowAllModel) object;
}
        detailImg=findViewById(R.id.detailed_image);
        quantity=findViewById(R.id.quantity);
        name=findViewById(R.id.detailed_name);
        rating=findViewById(R.id.rating);
        price=findViewById(R.id.detailed_price);
        desc=findViewById(R.id.detailed_desc);

       addtocard =findViewById(R.id.add_to_cart);
       buyNow =findViewById(R.id.buy_now);

        addItems=findViewById(R.id.add_item);
        removeItems=findViewById(R.id.remove_item);

    //NewProdct
        if(newProductsModel!=null){
            Glide.with(getApplicationContext()).load(newProductsModel.getImg_url()).into(detailImg);
            name.setText(newProductsModel.getName());
            price.setText(String.valueOf(newProductsModel.getPrice()));
            desc.setText(newProductsModel.getDescription());
            rating.setText(newProductsModel.getRating());
totalprice=newProductsModel.getPrice()*totalQuantity;
        }


        // popular product
        if(popularProductsModel!=null){
            Glide.with(getApplicationContext()).load(popularProductsModel.getImg_url()).into(detailImg);
            name.setText(popularProductsModel.getName());
            price.setText(String.valueOf(popularProductsModel.getPrice()));
            desc.setText(popularProductsModel.getDescription());
            rating.setText(popularProductsModel.getRating());
            totalprice=popularProductsModel.getPrice()*totalQuantity;

        }


        //show all
        if(showAllModel!=null){
            Glide.with(getApplicationContext()).load(showAllModel.getImg_url()).into(detailImg);
            name.setText(showAllModel.getName());
            price.setText("$"+(showAllModel.getPrice()));
            desc.setText(showAllModel.getDescription());

            rating.setText(showAllModel.getRating());
            totalprice=showAllModel.getPrice()*totalQuantity;
        }

            addtocard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    addtocard();
                }
            });


        buyNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(DetailActivity.this,AddressActivity.class);
                if(newProductsModel!=null){
                    intent.putExtra("item",newProductsModel );
                }  if(popularProductsModel!=null){
                    intent.putExtra("item",popularProductsModel );
                }  if(showAllModel!=null){
                    intent.putExtra("item",showAllModel );
                }
                startActivity(intent);
            }
        });


        addItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(totalQuantity<10){
                    totalQuantity++;
                    quantity.setText(String.valueOf(totalQuantity));
                    if(newProductsModel!=null){
                        totalprice=newProductsModel.getPrice()*totalQuantity;
                    }
                    if(popularProductsModel!=null){
                        totalprice=popularProductsModel.getPrice()*totalQuantity;
                    }if(showAllModel!=null){
                        totalprice=showAllModel.getPrice()*totalQuantity;
                    }
                }
            }
        });
        removeItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(totalQuantity>1){
                    totalQuantity--;
                    quantity.setText(String.valueOf(totalQuantity));
                    if(newProductsModel!=null){
                        totalprice=newProductsModel.getPrice()*totalQuantity;
                    }
                    if(popularProductsModel!=null){
                        totalprice=popularProductsModel.getPrice()*totalQuantity;
                    }if(showAllModel!=null){
                        totalprice=showAllModel.getPrice()*totalQuantity;
                    }
                }
            }
        });

    }

    private void addtocard() {
        String saveCurrentTime,saveCurrentDate;
        Calendar calforDate=Calendar.getInstance();
        SimpleDateFormat currentDate=new SimpleDateFormat("MM-dd-yyyy");
        saveCurrentDate=currentDate.format(calforDate.getTime());

        SimpleDateFormat curretnTime=new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime=curretnTime.format(calforDate.getTime());
        final HashMap<String,Object> cartMap=new HashMap<>();
        cartMap.put("productName",name.getText().toString());
        cartMap.put("productPrice",price.getText().toString());
        cartMap.put("currentTime",saveCurrentTime);
        cartMap.put("currentDate",saveCurrentDate);
        cartMap.put("totalQuantity",quantity.getText().toString());
        cartMap.put("totalPrice",totalprice);

    firestore.collection("AddToCart").document(auth.getCurrentUser().getUid()).collection("User").add(cartMap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
        @Override
        public void onComplete(@NonNull Task<DocumentReference> task) {
            Toast.makeText(DetailActivity.this, "Added to A Cart", Toast.LENGTH_SHORT).show();
            finish();
        }

        });
    }
}