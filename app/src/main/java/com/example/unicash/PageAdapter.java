package com.example.unicash;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class PageAdapter extends FragmentPagerAdapter {

    private final List<Fragment> fragmnetList = new ArrayList<>();
    private final List<String> stringList = new ArrayList<>();

    public PageAdapter(FragmentManager fm){
        super(fm);

    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return stringList.get(position);
    }

    @Override
    public Fragment getItem(int i) {
                return fragmnetList.get(i);
    }

    @Override
    public int getCount() {
        return stringList.size();
    }

    public  void AddFragmnet(Fragment fragment,String string){
        fragmnetList.add(fragment);
        stringList.add(string);

    }
}
