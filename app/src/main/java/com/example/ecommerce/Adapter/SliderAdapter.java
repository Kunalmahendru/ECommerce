package com.example.ecommerce.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.PagerAdapter;

import com.example.ecommerce.R;

public class SliderAdapter extends PagerAdapter {
    Context context;
    ImageView imageView;
    LayoutInflater layoutInflater;

    public SliderAdapter(Context context) {
        this.context = context;
    }
int imageArray[] ={
  R.drawable.onboardscreen1,R.drawable.onboardscreen2,R.drawable.onboardscreen3
};
    int headingArray[]={
      R.string.First_Slide , R.string.Second_Slide,R.string.Third_Slide
    };


    int DescArray[]={
            R.string.Description , R.string.Description,R.string.Description
    };

    @Override
    public int getCount() {
        return headingArray.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==(ConstraintLayout) object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view =layoutInflater.inflate(R.layout.sliding_layout,container,false);
        ImageView Imageview=view.findViewById(R.id.slider_img);
        TextView heading=view.findViewById(R.id.heading);
        TextView Description=view.findViewById(R.id.description);
        Imageview.setImageResource(imageArray[position]);
        heading.setText(headingArray[position]);
        Imageview.setTag(DescArray[position]);
container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
      container.removeView((ConstraintLayout)object);
    }
}

