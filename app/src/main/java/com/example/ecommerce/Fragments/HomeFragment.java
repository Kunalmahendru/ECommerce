package com.example.ecommerce.Fragments;

import static android.content.ContentValues.TAG;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.ecommerce.Activities.ShowAllAcitivity;
import com.example.ecommerce.Adapter.Category_Adapter;
import com.example.ecommerce.Adapter.NewProductsAdapter;
import com.example.ecommerce.Adapter.PopularProductsAdapter;
import com.example.ecommerce.Models.Category_Model;
import com.example.ecommerce.Models.NewProductsModel;
import com.example.ecommerce.Models.PopularProductsModel;
import com.example.ecommerce.R;
import com.google.firebase.Firebase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {
LinearLayout linearLayout;
TextView catShowAll,popularShowALl,newProductShowAll;
    FirebaseFirestore db;
ProgressDialog progressDialog;
    Category_Adapter categoryAdapter;
    NewProductsAdapter newProductsAdapter;
    PopularProductsAdapter popularProductsAdapter;
    List<Category_Model> categoryModelList;
    List<NewProductsModel> newProductsModels;
    List<PopularProductsModel> popularProductsModels;


    RecyclerView catrecycler,newProductRecycler,popularRecylerView;


    public HomeFragment() {
        // Required empty public constructor
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_home, container, false);
       db=FirebaseFirestore.getInstance();
       linearLayout=view.findViewById(R.id.home_layout);
       linearLayout.setVisibility(View.GONE);
progressDialog =new ProgressDialog(getActivity());
progressDialog.setTitle("Welcome to My Ecommerce App");
progressDialog.setMessage("Please Wait ....");
progressDialog.setCanceledOnTouchOutside(false);
progressDialog.show();


catShowAll=view.findViewById(R.id.category_see_all);
popularShowALl=view.findViewById(R.id.popular_see_all);
newProductShowAll=view.findViewById(R.id.newProducts_see_all);

catShowAll.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {

        Intent intent=new Intent(getContext(), ShowAllAcitivity.class);
        startActivity(intent);
    }
});
        popularShowALl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(getContext(), ShowAllAcitivity.class);
                startActivity(intent);
            }
        });
        newProductShowAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(getContext(), ShowAllAcitivity.class);
                startActivity(intent);
            }
        });

       // Category

        catrecycler=view.findViewById(R.id.rec_category);
        catrecycler.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));
        categoryModelList=new ArrayList<>();
        categoryAdapter=new Category_Adapter(getContext(),categoryModelList);
        catrecycler.setAdapter(categoryAdapter);


        db.collection("Category")
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                        Category_Model category = document.toObject(Category_Model.class);
                        categoryModelList.add(category);
                        categoryAdapter.notifyDataSetChanged();
                        linearLayout.setVisibility(View.VISIBLE);
                        progressDialog.dismiss();
                    }

                })
                .addOnFailureListener(e -> Log.w(TAG, "Error getting documents.", e));



        //NewProduct
            newProductRecycler=view.findViewById(R.id.new_product_rec);
          newProductRecycler.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));
        newProductsModels =new ArrayList<>();
        newProductsAdapter=new NewProductsAdapter(getContext(),newProductsModels);
newProductRecycler.setAdapter(newProductsAdapter);

        db.collection("New Products")
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                        NewProductsModel category = document.toObject(NewProductsModel.class);
                        newProductsModels.add(category);
                        newProductsAdapter.notifyDataSetChanged();
                    }

                })
                .addOnFailureListener(e -> Log.w(TAG, "Error getting documents.", e));



        //All products
        popularRecylerView=view.findViewById(R.id.popular_rec);
        popularRecylerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
         popularProductsModels=new ArrayList<>();
        popularProductsAdapter=new PopularProductsAdapter(getContext(),popularProductsModels);
        popularRecylerView.setAdapter(popularProductsAdapter);

        db.collection("AllProducts")
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                        PopularProductsModel category = document.toObject(PopularProductsModel.class);
                        popularProductsModels.add(category);
                        popularProductsAdapter.notifyDataSetChanged();
                    }

                })
                .addOnFailureListener(e -> Log.w(TAG, "Error getting documents.", e));




        return view;
    }
}