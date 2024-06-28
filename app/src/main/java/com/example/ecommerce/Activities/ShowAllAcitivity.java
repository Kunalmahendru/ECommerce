package com.example.ecommerce.Activities;

import static android.content.ContentValues.TAG;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ecommerce.Adapter.Show_All_Adapter;
import com.example.ecommerce.Models.ShowAllModel;
import com.example.ecommerce.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ShowAllAcitivity extends AppCompatActivity {
RecyclerView recyclerView;
Show_All_Adapter showAllAdapter;
Toolbar toolbar;

FirebaseFirestore firestore;
List<ShowAllModel> showAllModelList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_show_all_acitivity);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
toolbar=findViewById(R.id.show_all_toolbar);
setSupportActionBar(toolbar);
getSupportActionBar().setDisplayHomeAsUpEnabled(true);
toolbar.setNavigationOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        finish();
    }
});
        firestore=FirebaseFirestore.getInstance();
        recyclerView=findViewById(R.id.show_all_rec);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        showAllModelList=new ArrayList<>();
        showAllAdapter=new Show_All_Adapter(this,showAllModelList);
        recyclerView.setAdapter(showAllAdapter);

        String type=getIntent().getStringExtra("type");
        if(type==null || type.isEmpty()){

            firestore.collection("ShowAll")
                    .get()
                    .addOnSuccessListener(queryDocumentSnapshots -> {
                        for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                            ShowAllModel showAllModel=document.toObject(ShowAllModel.class);
                            showAllModelList.add(showAllModel);
                            showAllAdapter.notifyDataSetChanged();

                        }
                    }).addOnFailureListener(e -> Log.w(TAG, "Error getting documents.", e));


        }

        if(type!=null && type.equalsIgnoreCase("kids")){
            firestore.collection("ShowAll").whereEqualTo("type","kids")
                    .get()
                    .addOnSuccessListener(queryDocumentSnapshots -> {
                        for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                            ShowAllModel showAllModel=document.toObject(ShowAllModel.class);
                            showAllModelList.add(showAllModel);
                            showAllAdapter.notifyDataSetChanged();

                        }
                    }).addOnFailureListener(e -> Log.w(TAG, "Error getting documents.", e));

        }

        if(type!=null && type.equalsIgnoreCase("Sports shoes")){
            firestore.collection("ShowAll").whereEqualTo("type","Sports Shoes")
                    .get()
                    .addOnSuccessListener(queryDocumentSnapshots -> {
                        for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                            ShowAllModel showAllModel=document.toObject(ShowAllModel.class);
                            showAllModelList.add(showAllModel);
                            showAllAdapter.notifyDataSetChanged();

                        }
                    }).addOnFailureListener(e -> Log.w(TAG, "Error getting documents.", e));

        }

        if(type!=null && type.equalsIgnoreCase("Women")){
            firestore.collection("ShowAll").whereEqualTo("type","Women")
                    .get()
                    .addOnSuccessListener(queryDocumentSnapshots -> {
                        for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                            ShowAllModel showAllModel=document.toObject(ShowAllModel.class);
                            showAllModelList.add(showAllModel);
                            showAllAdapter.notifyDataSetChanged();

                        }
                    }).addOnFailureListener(e -> Log.w(TAG, "Error getting documents.", e));

        }

        if(type!=null && type.equalsIgnoreCase("camera")){
            firestore.collection("ShowAll").whereEqualTo("type","camera")
                    .get()
                    .addOnSuccessListener(queryDocumentSnapshots -> {
                        for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                            ShowAllModel showAllModel=document.toObject(ShowAllModel.class);
                            showAllModelList.add(showAllModel);
                            showAllAdapter.notifyDataSetChanged();

                        }
                    }).addOnFailureListener(e -> Log.w(TAG, "Error getting documents.", e));

        }

        if(type!=null && type.equalsIgnoreCase("Men")){
            firestore.collection("ShowAll").whereEqualTo("type","Men")
                    .get()
                    .addOnSuccessListener(queryDocumentSnapshots -> {
                        for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                            ShowAllModel showAllModel=document.toObject(ShowAllModel.class);
                            showAllModelList.add(showAllModel);
                            showAllAdapter.notifyDataSetChanged();

                        }
                    }).addOnFailureListener(e -> Log.w(TAG, "Error getting documents.", e));

        }

        if(type!=null && type.equalsIgnoreCase("Analog Watch")){
            firestore.collection("ShowAll").whereEqualTo("type","Analog Watch")
                    .get()
                    .addOnSuccessListener(queryDocumentSnapshots -> {
                        for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                            ShowAllModel showAllModel=document.toObject(ShowAllModel.class);
                            showAllModelList.add(showAllModel);
                            showAllAdapter.notifyDataSetChanged();

                        }
                    }).addOnFailureListener(e -> Log.w(TAG, "Error getting documents.", e));

        }




    }

}