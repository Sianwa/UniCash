package com.example.unicash.onboarding;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.unicash.MainActivity;
import com.example.unicash.R;


public class SliderAdapter extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater;

    public SliderAdapter(Context context){
        this.context=context;
    }
    //Arrays
    public int[] slide_images={
            R.drawable.onboarding7,
            R.drawable.onboarding3,
            R.drawable.onboarding5,
            R.drawable.onboarding6
    };

    public String[] slide_headings={
            "ACCESSIBILITY",
            "TRACKING",
            "COLLABORATION",
            "GET STARTED"
    };

    public String[] slide_descriptions ={
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit"

    };
    @Override
    public int getCount() {
        return slide_headings.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }
    @Override
    public Object instantiateItem(ViewGroup container, int position){
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slide_layout,container,false);

        ImageView imageView = view.findViewById(R.id.imageView);
        TextView textView1 = view.findViewById(R.id.heading);
        TextView textView2 = view.findViewById(R.id.body);


        imageView.setImageResource(slide_images[position]);
        textView1.setText(slide_headings[position]);
        textView2.setText(slide_descriptions[position]);

        container.addView(view);

        return view;
    }
    public void destroyItem(ViewGroup container ,int position, Object obj){
        container.removeView((ConstraintLayout)obj);
    }
}
