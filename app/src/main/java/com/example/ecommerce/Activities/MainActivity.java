package com.example.ecommerce.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.example.ecommerce.Fragments.HomeFragment;
import com.example.ecommerce.R;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
Fragment fragment;
Toolbar toolbar;
FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        auth= FirebaseAuth.getInstance();
toolbar=findViewById(R.id.home_toolbar);
setSupportActionBar(toolbar);
getSupportActionBar().setDisplayShowHomeEnabled(true);
getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_menu_24);
        if (findViewById(R.id.homeFrame) != null) {
            // If we're being restored from a previous state, then we don't need to do anything
            // and should return or else we could end up with overlapping fragments.
            if (savedInstanceState != null) {
                return;
            }
            // Create a new Fragment to be placed in the activity layout
            fragment=new HomeFragment();
            // Add the fragment to the 'fragment_container' FrameLayout
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.homeFrame, fragment).commit();
        }

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();
        if(id==R.id.menu_logout){
        auth.signOut();
        startActivity(new Intent(MainActivity.this,RegistrationActivity.class));
        finish();
        }else if(id==R.id.menu_mycart){
        startActivity(new Intent(MainActivity.this,CartAcitivity.class));
        }
        return true;
    }
}