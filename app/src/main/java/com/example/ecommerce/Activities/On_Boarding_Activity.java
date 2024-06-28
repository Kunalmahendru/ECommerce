package com.example.ecommerce.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager.widget.ViewPager;

import androidx.core.content.ContextCompat;

import com.example.ecommerce.R;
import com.example.ecommerce.Adapter.SliderAdapter;

public class On_Boarding_Activity extends AppCompatActivity {
    private ViewPager viewPager;
    private Button button;
    SharedPreferences sharedPreferences;

    Animation animation;
    private LinearLayout linearLayout;
    private SliderAdapter sliderAdapter;
    private TextView[] dots;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_on_boarding);
        getSupportActionBar().hide();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        button = findViewById(R.id.get_started_btn);
        viewPager = findViewById(R.id.slider);
        linearLayout = findViewById(R.id.dots);

        sliderAdapter = new SliderAdapter(this);
        viewPager.setAdapter(sliderAdapter);
button.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        startActivity(new Intent(On_Boarding_Activity.this,RegistrationActivity.class));
   finish();
    }
});
        addDots(0);
        viewPager.addOnPageChangeListener(changeListener);
    }

    private void addDots(int position) {
        dots = new TextView[3];
        linearLayout.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(ContextCompat.getColor(this, R.color.teal_200)); // Assuming inactive_dot_color is defined in your colors.xml
            linearLayout.addView(dots[i]);
        }
        if (dots.length > 0) {
            dots[position].setTextColor(ContextCompat.getColor(this, R.color.pink));
        }
    }

    ViewPager.OnPageChangeListener changeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

        @Override
        public void onPageSelected(int position) {
            addDots(position);
            if (position == 0 || position == 1) {
                button.setVisibility(View.INVISIBLE);
            } else {
                animation= AnimationUtils.loadAnimation(On_Boarding_Activity.this,R.anim.slide_anim);
                button.setAnimation(animation);
                button.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {}
    };
}
