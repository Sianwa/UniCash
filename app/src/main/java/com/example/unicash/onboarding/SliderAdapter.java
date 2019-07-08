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
            "No more paper hustle,book keeping is now digital",
            "See where all your money is going and choose the right way forward",
            "Work together with your employees to track all expenses",
            "Get started with UniCash and manage your money well"

    };
    public String[] slide_buttons={
            "NEXT",
            "NEXT",
            "NEXT",
            "GET STARTED"
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
        Button button=view.findViewById(R.id.next);


        imageView.setImageResource(slide_images[position]);
        textView1.setText(slide_headings[position]);
        textView2.setText(slide_descriptions[position]);
        button.setText(slide_buttons[position]);

        container.addView(view);

        return view;
    }
    public void destroyItem(ViewGroup container ,int position, Object obj){
        container.removeView((ConstraintLayout)obj);
    }
}
