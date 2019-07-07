package com.example.unicash.onboarding;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.unicash.MainActivity;
import com.example.unicash.R;

public class Onboarding extends AppCompatActivity {

    private ViewPager mviewPager;
    private LinearLayout mlinearLayout;
    private SliderAdapter sliderAdapter;
    private TextView[] mDots;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);

        mlinearLayout=findViewById(R.id.linear);
        mviewPager=findViewById(R.id.viewp);
        Button mButton= findViewById(R.id.next);

        sliderAdapter= new SliderAdapter(this);

        mviewPager.setAdapter(sliderAdapter);

        addDotsIndicator(0);

      mviewPager.addOnPageChangeListener(listener);
    }
    public void addDotsIndicator(int position){
        mDots = new TextView[4];
        mlinearLayout.removeAllViews();

        for(int i=0; i<mDots.length; i++){
            mDots[i]= new TextView(this);
            mDots[i].setText(Html.fromHtml("&#8226;"));
            mDots[i].setTextColor(getResources().getColor(R.color.colorPrimaryDark));

            mlinearLayout.addView(mDots[i]);
        }
        if(mDots.length >0){
            mDots[position].setTextColor(getResources().getColor(R.color.colorPrimary));
        }
    }

    ViewPager.OnPageChangeListener listener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int i, float v, int i1) {

        }

        @Override
        public void onPageSelected(int i) {
            addDotsIndicator(i);
        }

        @Override
        public void onPageScrollStateChanged(int i) {

        }
    };

    public void skip(View view) {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}
