package com.example.unicash;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TabItem;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //remove shadow from action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setElevation(0);

        //tablayout
        TabLayout tabLayout = findViewById(R.id.tabLayout);
        TabItem tabExp=findViewById(R.id.expenses);
        TabItem tabRem=findViewById(R.id.reminders);
        TabItem tabAnalytics=findViewById(R.id.Analytics);
        ViewPager viewPager = findViewById(R.id.viewPager);
        PageAdapter adapter = new PageAdapter(getSupportFragmentManager());

        //fragments
        adapter.AddFragmnet(new ExpFragment(),"Expenses");
        adapter.AddFragmnet(new RemFragment(),"Reminders");
        adapter.AddFragmnet(new AnalytFragment(),"Analytics");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);


    }

 //prevents going back to login page
    @Override
    public void onBackPressed() {

    }

}
